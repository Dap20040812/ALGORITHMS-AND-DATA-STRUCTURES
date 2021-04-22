package edu.sabana.aads.hackerrank.graph;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class GraphList {

    List<GraphNode> nodes;

    public GraphList() {
        this.nodes = new ArrayList<>();
    }

    public GraphList addNode(GraphNode node) {
        this.nodes.add(node);
        return this;
    }

    public boolean existsPathDFS(GraphNode start, GraphNode end) {

        boolean existsPath = false;
        boolean existsNodes = exists(start) && exists(end);

        //1. If start and end are the same
        if (existsNodes && start == end) {
            existsPath = true;
        } else if (existsNodes) {

            for (int i = 0; !existsPath && i < start.children.size(); i++) {
                GraphNode node = start.children.get(i);
                if (!node.visited) {
                    node.visited = true;
                    System.out.println(String.format("%s == %s", node.data, end.data));
                    if (node == end) {
                        existsPath = true;
                    } else {
                        existsPath = existsPathDFS(node, end);
                    }
                }
            }
        }

        return existsPath;
    }

    public boolean existsPathBFS(GraphNode start, GraphNode end) {

        if (start == end)
            return true;

        Queue<GraphNode> queue = new LinkedList<>();

        for (GraphNode node : start.children)
            queue.add(node);
        start.visited = true;
        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();
            current.visited = true;
            System.out.println(String.format("%s == %s", current.data, end.data));
            if (current == end) {
                return true;
            } else {
                for (GraphNode node : current.children.stream().filter(n->!n.visited).collect(Collectors.toList()))
                    queue.add(node);
            }

        }

        return false;
    }


    public boolean exists(GraphNode node) {
        return true;
    }

    public static void main(String[] args) {
        GraphNode node0 = new GraphNode(0);
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        GraphNode node5 = new GraphNode(5);

        node0.children.add(node1);
        node1.children.add(node2);
        node1.children.add(node3);
        node2.children.add(node4);
        node3.children.add(node5);
        node3.children.add(node1);
        node4.children.add(node1);
        node5.children.add(node4);

        GraphList graph = new GraphList();
        graph
                .addNode(node0)
                .addNode(node1)
                .addNode(node2)
                .addNode(node3)
                .addNode(node4)
                .addNode(node5);

        System.out.println(String.format("Exists path beteween %s and %s = %s", node0, node5, graph.existsPathBFS(node0, node5)));
    }
}

// projects: a, b, c, d, e, f
// dependencies: (a,d) (f,b) (b,d) (f,a) (d,c)

// output: f, e, a, b, d, c




