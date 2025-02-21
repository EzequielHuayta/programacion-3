package com.example.tpgrupo8.tpoGrupo8.service;

import com.example.tpgrupo8.tpoGrupo8.entities.City;
import com.example.tpgrupo8.tpoGrupo8.entities.Route;
import com.example.tpgrupo8.tpoGrupo8.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class GraphService {

    @Autowired
    CityRepository cityRepository;
    private static final Logger logger = LoggerFactory.getLogger(GraphService.class);


    /** 🔹 Backtracking: Verifica si hay un camino entre dos ciudades */
    public Mono<Boolean> hasPathBacktracking(String startName, String endName) {
        return cityRepository.findByName(startName)
                .flatMap(start -> cityRepository.findByName(endName)
                        .map(end -> backtracking(start, end, new HashSet<>())))
                .switchIfEmpty(Mono.just(false));  // Return false if no cities are found
    }

    private boolean backtracking(City current, City target, Set<City> visited) {
        logger.info("Visiting city: {}", current.getName());
        if (current.equals(target)) {
            logger.info("Target city {} found!", target.getName());
            return true;
        }
        visited.add(current);

        List<Route> routes = new ArrayList<>(current.getRoutes());
        logger.info("City {} has {} routes", current.getName(), routes.size());

        for (var route : routes) {
            City destination = route.getDestination();
            logger.info("Current city: {}, Route to: {}", current.getName(), destination.getName());
            if (!visited.contains(destination)) {
                if (backtracking(destination, target, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    /** 🔹 DFS: Recorre el grafo en profundidad */
    public Mono<List<String>> depthFirstSearch(String startName) {
        return cityRepository.findByName(startName)
                .flatMap(start -> {
                    List<String> result = new ArrayList<>();
                    dfs(start, new HashSet<>(), result);
                    return Mono.just(result);  // Return the DFS result as Mono<List<String>>
                })
                .switchIfEmpty(Mono.just(new ArrayList<>()));  // Return an empty list if no city is found
    }

    private void dfs(City current, Set<City> visited, List<String> result) {
        if (visited.contains(current)) return;
        visited.add(current);
        result.add(current.getName());

        for (var route : current.getRoutes()) {
            dfs(route.getDestination(), visited, result);
        }
    }

    /** 🔹 BFS: Recorre el grafo en anchura */
    public Mono<List<String>> breadthFirstSearch(String startName) {
        return cityRepository.findByName(startName)
                .flatMap(start -> {
                    List<String> result = new ArrayList<>();
                    Queue<City> queue = new LinkedList<>();
                    Set<City> visited = new HashSet<>();

                    queue.add(start);
                    visited.add(start);

                    while (!queue.isEmpty()) {
                        City city = queue.poll();
                        result.add(city.getName());

                        for (var route : city.getRoutes()) {
                            if (!visited.contains(route.getDestination())) {
                                queue.add(route.getDestination());
                                visited.add(route.getDestination());
                            }
                        }
                    }
                    return Mono.just(result);  // Return the BFS result as Mono<List<String>>
                })
                .switchIfEmpty(Mono.just(new ArrayList<>()));  // Return an empty list if no city is found
    }
}
