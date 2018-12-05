/*
* Jett Moy - jlmoy
* Andrew Exton - aexton
* Project 5
* Part 1 & 2
*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;

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

    public class VertexInfo {
        private int length; // distance/length of path
        private int pred; // predecessor/parent of vertex

        public VertexInfo(int length, int pred) {
            this.length = length;
            this.pred = pred;
        }

        // TODO
        public ArrayList<VertexInfo> BFS(int s) {
            Queue queue = new LinkedList<T>(); // not sure what type yet

            boolean visited[] = new boolean[N];

            ArrayList<VertexInfo> bfs = new ArrayList<VertexInfo>(N);
            for (VertexInfo v : bfs) {
                v = new graph.VertexInfo(Integer.MAX_VALUE, -1);
            }
            // LinkedList<Integer> queue = new LinkedList<Integer>();
            // visited[s] = true;
            // queue.add(s);
            //
            // while (!queue.isEmpty) {
            //     Integer current = queue.remove();
            //     bfs.add(new VertexInfo(0, );
            //     LinkedList<Integer> neighbors = graph.get(current);
            //     for (Integer neighbor : neighbors) {
            //         bfs.add()
            //         bfs.get(neighbor);
            //     }
            // }


            return null;
        }

        // TODO
        // returns true if there is a path
        // from "from" vertex to "to" vertex,
        // false otherwise
        public boolean isTherePath(int from, int to) {
            return false;
        }

        // TODO
        // returns the shortest path
        // to the "to" vertex from the "from" vertex
        public int lengthOfPath(int from, int to) {
            return -1;
        }

        // TODO
        // arranges the output of the shortest path
        // from "from" vertex to "to" vertex
        // IF it is reachable -> print natural numbering of path
        // ELSE print "There is no path"
        public void printPath(int from, int to) {
            ArrayList<VertexInfo> results = BFS(from);
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
        int N = graph.size();
        int[] Indegrees = new int[graph.size()];
        ArrayList<Integer> f = new ArrayList<Integer>(graph.size());

        for (int v = 0; v < N; v++) {
            LinkedList<Integer> vertex = graph.get(v);
            for (Integer i : vertex)
                Indegrees[i.intValue()]++;
        }

        for (int i = 0; i < N; i++)
            f.add(i, Indegrees[i]);

        return f;
    }

    public ArrayList<Integer> topSort() throws IllegalArgumentException {
        // If graph is cyclic throw IllegalArgumentException
        int N = vertexCount();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        ArrayList<Integer> indegrees = indegrees();
        // for (int value : indegrees) {
        //     System.out.print(value + " ");
        // }
        // System.out.println();

        if (graph.size() == 0 || graph == null) return new ArrayList<Integer>();

        ArrayList<Integer> result = new ArrayList<Integer>();
        // ArrayList<Integer> copy = new ArrayList<Integer>(graph);

        for (int i = 0; i < N; i++) {
            if (indegrees.get(i).intValue() == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            // System.out.println("QUEUE: " + queue.toString());
            Integer vertex = queue.remove();
            result.add(vertex);
            // System.out.println("vertex: " + vertex);

            LinkedList<Integer> vertexes = graph.get(vertex);
            // System.out.println("CHECKING: " + vertexes.toString());
            for (Integer edge : vertexes) {
                // System.out.println("indegrees: " + indegrees.toString());
                indegrees.set(edge, indegrees.get(edge) - (Integer)1);
                // System.out.println("SUBTRACTING INDEGREE FROM: " + edge);
                if (indegrees.get(edge) == (Integer)0) {
                    // System.out.println("Adding to queue: " + edge);
                    queue.add(edge);
                }
            }

        }

        if (result.size() != N)
            throw new IllegalArgumentException();

        return new ArrayList<Integer>(result);
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

    // this method prints the breadth-first-tree for a given source vertex s
    public void printTree(int s) {

    }

    // need to figure out params...
    private void printTree_AUX() {

    }

    // returns the root of the breadth-first-tree for the given source-vertex.
    private TreeNode buildTree(int s) {
        return null;
    }

    private class TreeNode {
        int vertex_num;
        LinkedList<TreeNode> vertex_children;
    }

}
