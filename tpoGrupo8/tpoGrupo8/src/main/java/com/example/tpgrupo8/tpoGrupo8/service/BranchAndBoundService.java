// src/main/java/com/example/tpgrupo8/tpoGrupo8/service/BranchAndBoundService.java
package com.example.tpgrupo8.tpoGrupo8.service;

import com.example.tpgrupo8.tpoGrupo8.entities.City;
import com.example.tpgrupo8.tpoGrupo8.entities.Route;
import com.example.tpgrupo8.tpoGrupo8.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

@Service
public class BranchAndBoundService {

    @Autowired
    private CityRepository cityRepository;

    public Mono<Boolean> hasPathBranchAndBound(String startName, String endName) {
        return cityRepository.findByName(startName)
                .flatMap(start -> cityRepository.findByName(endName)
                        .map(end -> branchAndBound(start, end)))
                .switchIfEmpty(Mono.just(false));  // Return false if no cities are found
    }

    private boolean branchAndBound(City start, City end) {
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> Double.compare(a.cost, b.cost));
        Set<City> visited = new HashSet<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.city.equals(end)) {
                return true;
            }
            if (!visited.contains(current.city)) {
                visited.add(current.city);
                for (Route route : current.city.getRoutes()) {
                    if (!visited.contains(route.getDestination())) {
                        queue.add(new Node(route.getDestination(), current.cost + route.getCost()));
                    }
                }
            }
        }
        return false;
    }

    private static class Node {
        City city;
        double cost;

        Node(City city, double cost) {
            this.city = city;
            this.cost = cost;
        }
    }
}