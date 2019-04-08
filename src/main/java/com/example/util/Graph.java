package com.example.util;


import java.util.List;

import com.example.domain.PlanetNames;
import com.example.domain.Routes;

public class Graph {
    private final List<PlanetNames> vertexes;
    private final List<Routes> edges;

    public Graph(List<PlanetNames> vertexes, List<Routes> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<PlanetNames> getVertexes() {
        return vertexes;
    }

    public List<Routes> getEdges() {
        return edges;
    }

}