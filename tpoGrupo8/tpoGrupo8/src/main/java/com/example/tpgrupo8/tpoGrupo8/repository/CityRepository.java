package com.example.tpgrupo8.tpoGrupo8.repository;

import com.example.tpgrupo8.tpoGrupo8.entities.City;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface CityRepository extends ReactiveCrudRepository<City, String> {
    Flux<City> findAll();
    Mono<City> findById(String id);
    Mono<City> findByName(String name);

}
