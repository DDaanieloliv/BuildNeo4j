package com.ddaaniel.BuildNeo4j.domain.repository;

import com.ddaaniel.BuildNeo4j.config.Connection;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class neo4jRepository {

    @Autowired
    private Connection neo4jConnection;


    public void addActorToMovie (String actorName , String movieTitle, String role) {
        try (Session session = neo4jConnection.getSession()){
            Transaction tx = session.beginTransaction();
            String query =  "MATCH (a:Person {name: $actorName}), (m:Movie {title: $moveTitle}) " +
                        "CREATE (a)-[:ACTED-IN {role: $role}]->(m)";
            tx.run(query,
                    org.neo4j.driver.Values.parameters(
                       "actorName", actorName,
                            "movieTitle", movieTitle,
                            "role", role
                    ));
                    tx.commit();

        }
    }


    public void addDirectorToMovie(String directorName, String movieTitle) {
        try(Session session = neo4jConnection.getSession()){
            Transaction tx = session.beginTransaction();
            String query = "MATCH (d:Person {name: $directorName}). (m.Movie {title: $movieTitle}) " +
                        "CREATE (d)-[:DIRECTED]->(m)";
            tx.run(query,
                    org.neo4j.driver.Values.parameters(
                            "directorName", directorName,
                            "movieTitle", movieTitle
                    ));
                    tx.commit();
        }
    }
}
