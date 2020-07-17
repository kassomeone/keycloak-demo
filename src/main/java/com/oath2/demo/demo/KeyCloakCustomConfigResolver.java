package com.oath2.demo.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade.Request;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.representations.adapters.config.AdapterConfig;

public class KeyCloakCustomConfigResolver extends KeycloakSpringBootConfigResolver {

    private final Map<String, KeycloakDeployment> cache = new ConcurrentHashMap<String, KeycloakDeployment>();
    
    @Override
    public KeycloakDeployment resolve(Request request) {


        String h = request.getHeader("Authorization");

        AdapterConfig config = new AdapterConfig();
        config.setRealm("moonstarschool");
        config.setResource("moonstarschool-app");
        config.setAuthServerUrl("http://localhost:8080/auth");
        config.setPublicClient(true);

        return KeycloakDeploymentBuilder.build(config);

    }
}