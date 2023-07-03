package com.andrusca.ecommerce.config;

import com.andrusca.ecommerce.entity.Country;
import com.andrusca.ecommerce.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        HttpMethod[] theUnsuportedActions = {HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};
        //disable for put, post, delete
        config.getExposureConfiguration()
                .forDomainType(Country.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsuportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsuportedActions));

        exposeIds(config);  
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> enitityClasses = new ArrayList<>();

        for (EntityType tempEntityType : entities) {
            enitityClasses.add(tempEntityType.getJavaType());
        }
        Class[] domainTypes = enitityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }


}
