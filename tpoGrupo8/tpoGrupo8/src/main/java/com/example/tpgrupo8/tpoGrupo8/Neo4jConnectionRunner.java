package com.example.tpgrupo8.tpoGrupo8;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Driver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jConnectionRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687",
                AuthTokens.basic("neo4j", "neo4j2025"))) {
            driver.verifyConnectivity();
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.err.println("Failed to connect to Neo4j: " + e.getMessage());
        }
    }
}