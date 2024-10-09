package com.ddaaniel.BuildNeo4j.controller.service;

import com.ddaaniel.BuildNeo4j.domain.repository.neo4jRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class neo4jService {

    @Autowired
    private neo4jRepository neo4jRepository;

    public void addActorToMovie(String actorName, String movieTitle, String role){
        neo4jRepository.addActorToMovie(actorName, movieTitle, role);
    }


    public void addDirectorToMovie(String directorName, String movieTitle){
        neo4jRepository.addDirectorToMovie(directorName, movieTitle);
    }
}
