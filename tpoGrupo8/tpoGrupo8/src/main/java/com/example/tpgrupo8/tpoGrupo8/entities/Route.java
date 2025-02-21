package com.example.tpgrupo8.tpoGrupo8.entities;

import com.example.tpgrupo8.tpoGrupo8.entities.City;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class Route {
    @Id @GeneratedValue
    private Long id;

    @Getter
    @TargetNode
    @Relationship(type = "DESTINATION")
    private City destination;

    @Getter
    private double cost;

    public Route(City destination, double cost) {
        this.destination = destination;
        this.cost = cost;
    }

}
