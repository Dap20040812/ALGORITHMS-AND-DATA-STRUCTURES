package edu.sabana.aads.hackerrank.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    int data;
    boolean visited;
    List<GraphNode> children;

    public GraphNode(int data) {
        this.data = data;
        this.visited = false;
        this.children = new ArrayList<>();
    }

}
