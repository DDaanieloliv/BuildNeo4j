package com.ddaaniel.BuildNeo4j.controller;

import com.ddaaniel.BuildNeo4j.controller.service.neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class neo4jController {

    @Autowired
    private neo4jService neo4jService;

    @PostMapping("/addActor")
    public String addActorToMovie(@RequestParam String actorName,
                                  @RequestParam String movieTitle,
                                  @RequestParam String role) {
        neo4jService.addActorToMovie(actorName, movieTitle, role);
        return "Ator adicionado ao filme com sucesso!";
    }

    @PostMapping("/addDirector")
    public String addDirectorToMovie(@RequestParam String directorName,
                                     @RequestParam String movieTitle) {
        neo4jService.addDirectorToMovie(directorName, movieTitle);
        return "Diretor adicionado ao filme com sucesso!";
    }

    @GetMapping("/actors")
    public List<Map<String, Object>> getActorsInMovies() {
        return neo4jService.getActorsInMovies();  // Certifique-se de ter este método no service
    }

}
