package com.example.tpgrupo8.tpoGrupo8.controller;

import com.example.tpgrupo8.tpoGrupo8.entities.City;
import com.example.tpgrupo8.tpoGrupo8.entities.Route;
import com.example.tpgrupo8.tpoGrupo8.repository.CityRepository;
import com.example.tpgrupo8.tpoGrupo8.service.BranchAndBoundService;
import com.example.tpgrupo8.tpoGrupo8.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    GraphService graphService;
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private BranchAndBoundService branchAndBoundService;


    /** 🔹 Backtracking: Devuelve si existe un camino entre dos ciudades */
    @GetMapping("/dfs")
    public Mono<List<String>> depthFirstSearch(@RequestParam String start) {
        return graphService.depthFirstSearch(start);
    }

    /** 🔹 BFS: Devuelve la lista de ciudades visitadas en orden */
    @GetMapping("/bfs")
    public Mono<List<String>> breadthFirstSearch(@RequestParam String start) {
        return graphService.breadthFirstSearch(start);
    }

    /** 🔹 Backtracking: Verifica si hay un camino entre dos ciudades */
    @GetMapping("/backtracking")
    public Mono<Boolean> hasPath(@RequestParam String start, @RequestParam String end) {
        return graphService.hasPathBacktracking(start, end);
    }

    @PutMapping("/city")
    public Mono<City> createOrUpdateCity(@RequestBody City city) {
        return updateCityWithRoutes(city);  // Guarda la ciudad, crea o actualiza según sea necesario
    }

    /** 🔹 GET: Obtener todas las ciudades en formato reactivo */
    @GetMapping(value = { "", "/" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<City> getAllCities() {
        return cityRepository.findAll();  // Ahora devuelve un Flux<City>
    }


    @GetMapping("/branch-and-bound")
    public Mono<Boolean> hasPathBranchAndBound(@RequestParam String start, @RequestParam String end) {
        return branchAndBoundService.hasPathBranchAndBound(start, end);
    }

    private Mono<City> updateCityWithRoutes(City city) {
        return cityRepository.save(city)
                .flatMap(savedCity -> {
                    List<Mono<City>> routeUpdates = new ArrayList<>();
                    for (Route route : savedCity.getRoutes()) {
                        City destination = route.getDestination();
                        routeUpdates.add(cityRepository.save(destination));
                    }
                    return Mono.when(routeUpdates).thenReturn(savedCity);
                });
    }
}
