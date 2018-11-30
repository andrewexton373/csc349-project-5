/*
* Jett Moy - jlmoy
* Andrew Exton - aexton
* Project 5
* Part 1 & 2
*/

import java.util.LinkedList;
import java.util.ArrayList;

public class DiGraph {
    private ArrayList<LinkedList> graph;

    public DiGraph(int N) {
        this.graph = new ArrayList<LinkedList>(N);
    }

    public void addEdge(int from, int to) {
        // check if already exists
        LinkedList fromVertex = graph.get(from-1);
        if (!fromVertex.contains((Integer)(to-1)))
            fromVertex.add(to-1);    // convert from natural to Java indexing
    }

    public void deleteEdge(int from, int to) {
        // check if exists
        LinkedList fromVertex = graph.get(from-1);
        if (fromVertex.contains((Integer)(to-1)))
            fromVertex.remove(to-1); // convert from natural to Java indexing
    }

    public int edgeCount() {
        int edges = 0;
        for (int i = 0; i < graph.size(); i++)
            edges += graph.get(i).size();
        return edges;
    }

    public int vertexCount() {
        return graph.size();
    }

    public void print() {
        int vertexes = vertexCount();
        for (int i = 0; i < vertexes; i++) {
            System.out.print((i + 1) + " is connected to : ");
            LinkedList vertex = graph.get(i);
            int adjacent = vertex.size();
            for (int v = 0; v < adjacent; v++) {
                System.out.print((vertex.get(v) + 1) + ", ");
            }
            System.out.println(vertex.get(v) + 1);
        }
    }

}
