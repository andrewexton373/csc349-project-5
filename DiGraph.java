/*
* Jett Moy - jlmoy
* Andrew Exton - aexton
* Project 5
* Part 1 & 2
*/

import java.util.LinkedList;
import java.util.ArrayList;

public class DiGraph {
    private ArrayList<LinkedList<Integer>> graph;
    private int N;

    public DiGraph(int N) {
        this.N = N;
        this.graph = new ArrayList<LinkedList<Integer>>(N);
        for (int v = 0; v < N; v++) {
            this.graph.add(v, new LinkedList<Integer>());
        }
    }

    public void addEdge(int from, int to) {
        // check if already exists
        LinkedList<Integer> fromVertex = graph.get(from-1);
        if (!fromVertex.contains((Integer)(to-1)))
            fromVertex.add(to-1);    // convert from natural to Java indexing
    }

    public void deleteEdge(int from, int to) {
        // check if exists
        LinkedList<Integer> fromVertex = graph.get(from-1);
        fromVertex.removeFirstOccurrence((Integer)to-1);
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

    private ArrayList<Integer> indegrees() {
        ArrayList<Integer> Indegrees = new ArrayList<Integer>(N);
        int indegrees = 0;

        for (int current = 0; current < vertexCount(); current++) {
            for (int other = 0; other < vertexCount(); other++) {
                if (current != other) {
                    LinkedList<Integer> vertex = graph.get(other);
                    if (vertex.contains((Integer)current)) indegrees++;
                }
            }
            Indegrees.add(current, indegrees);
        }

        return Indegrees;
    }

    public ArrayList<Integer> topSort() {
        // If graph is cyclic throw IllegalArgumentException
        LinkedList<Integer> queue = new LinkedList<Integer>();
        ArrayList<Integer> indegrees = indegrees();

        if (graph.size() == 0 || graph == null) return new ArrayList<Integer>();

        do {
            int idx = indegrees.indexOf((Integer) 0);
            if (idx != -1) {
                queue.addFirst(idx + 1);
                graph.remove((Integer) idx);
                indegrees = indegrees();
            } else {
                break;
            }
        } while (true);

        return new ArrayList<Integer>(queue);
    }




    public void print() {
        int vertexes = vertexCount();
        for (int i = 0; i < vertexes; i++) {
            System.out.print((i + 1) + " is connected to : ");
            LinkedList<Integer> vertex = graph.get(i);
            int adjacent = vertex.size();
            if (adjacent == 0) {
                System.out.println();
                continue;
            }
            int v;
            for (v = 0; v < adjacent - 1; v++) {
                Integer V = vertex.get(v);
                System.out.print((V.intValue() + 1) + ", ");
            }
            Integer V = vertex.get(v);
            System.out.println(V.intValue() + 1);
        }
    }

}
