package com.ddaaniel.BuildNeo4j.domain.repository;

import com.ddaaniel.BuildNeo4j.config.Connection;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class neo4jRepository {

    @Autowired
    private Connection neo4jConnection;


    public void addActorToMovie (String actorName , String movieTitle, String role) {
        try (Session session = neo4jConnection.getSession()){
            Transaction tx = session.beginTransaction();


            // Query para garantir que o nó do ator seja criado se não existir
            String actorQuery = "MERGE (a:Person {name: $actorName})";
            tx.run(actorQuery, org.neo4j.driver.Values.parameters("actorName", actorName));

            // Query para garantir que o nó do filme seja criado se não existir
            String movieQuery = "MERGE (m:Movie {title: $movieTitle})";
            tx.run(movieQuery, org.neo4j.driver.Values.parameters("movieTitle", movieTitle));


            String query =  "MATCH (a:Person {name: $actorName}), (m:Movie {title: $movieTitle}) " +
                    "CREATE (a)-[:ACTED_IN {role: $role}]->(m)";
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

            // Query para garantir que o nó do diretor seja criado se não existir
            String directorQuery = "MERGE (d:Person {name: $directorName})";
            tx.run(directorQuery, org.neo4j.driver.Values.parameters("directorName", directorName));

            // Query para garantir que o nó do filme seja criado se não existir
            String movieQuery = "MERGE (m:Movie {title: $movieTitle})";
            tx.run(movieQuery, org.neo4j.driver.Values.parameters("movieTitle", movieTitle));


            String query = "MATCH (d:Person {name: $directorName}), (m:Movie {title: $movieTitle}) " +
                        "CREATE (d)-[:DIRECTED]->(m)";
            tx.run(query,
                    org.neo4j.driver.Values.parameters(
                            "directorName", directorName,
                            "movieTitle", movieTitle
                    ));
                    tx.commit();
        }
    }


    public List<Map<String, Object>> getActorsInMovies() {
        try (Session session = neo4jConnection.getSession()) {
            String query = "MATCH (a:Person)-[r:ACTED_IN]->(m:Movie) " +
                    "RETURN a.name AS actorName, m.title AS movieTitle, r.role AS role";

            return session.readTransaction(tx -> {
                Result result = tx.run(query);
                return result.list(record -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("actorName", record.get("actorName").asString());
                    map.put("movieTitle", record.get("movieTitle").asString());
                    map.put("role", record.get("role").asString());
                    return map;
                });
            });
        }
    }
}
