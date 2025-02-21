package com.example.tpgrupo8.tpoGrupo8.entities;

import com.example.tpgrupo8.tpoGrupo8.entities.Route;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Node("City")
public class City {
    @Id
    private String name;

    @Relationship(type = "CONNECTED_TO", direction = Relationship.Direction.OUTGOING)
    private Set<Route> routes = new HashSet<>();

    public City() {}

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void addRoute(City destination, double cost) {
        this.routes.add(new Route(destination, cost));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}