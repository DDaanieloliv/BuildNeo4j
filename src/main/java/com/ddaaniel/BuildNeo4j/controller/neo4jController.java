package com.ddaaniel.BuildNeo4j.controller;

import com.ddaaniel.BuildNeo4j.controller.service.neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class neo4jController {

    @Autowired
    private neo4jService neo4jService;

    @PostMapping("/addActor")
    public String addActorToMovie(@RequestParam String actotName,
                                  @RequestParam String movieTitle,
                                  @RequestParam String role) {
        neo4jService.addActorToMovie(actotName, movieTitle, role);
        return "Ator adicionado ao filme com sucesso!";
    }

    @PostMapping("/addDirector")
    public String addDirectorToMovie(@RequestParam String directorName,
                                     @RequestParam String movieTitle) {
        neo4jService.addDirectorToMovie(directorName, movieTitle);
        return "Diretor adicionado ao filme com sucesso!";
    }
}
