package com.ddaaniel.BuildNeo4j.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Connection {

    private final Driver driver;

    public Connection(
            @Value("${neo4j.uri}") String uri,
            @Value("${neo4j.username}") String username,
            @Value("${neo4j.password}") String password
    ) {
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    public Session getSession(){
        return driver.session();
    }

    public void close() {
        driver.close();
    }
}
