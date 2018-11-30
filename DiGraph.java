/*
* Jett Moy - jlmoy
* Andrew Exton - aexton
* Project 5
* Part 1 & 2
*/

public class DiGraph {
    private LinkedList<Integer>[] graph;

    public DiGraph(int N) {
        this.graph = new LinkedList<Integer>[N];
    }

    public void addEdge(int from, int to) {
        // check if already exists
        if (!graph[from-1].contains((Integer)(to-1))
            graph[from-1].add(to-1);    // convert from natural to Java indexing
    }

    public void deleteEdge(int from, int to) {
        // check if exists
        if (graph[from-1].contains((Integer)(to-1))
            graph[from-1].remove(to-1); // convert from natural to Java indexing
    }

    public int edgeCount() {
        int edges = 0;
        for (int i = 0; i < graph.length; i++)
            edges += graph[i].size();
        return edges;
    }

    public int vertexCount() {
        return graph.length;
    }

    public void print() {
        int vertexes = vertexCount();
        for (int i = 0; i < vertexes; i++) {
            System.out.print((i + 1) + " is connected to : ");
            int adjacent = graph[i].size();
            for (int v = 0; v < adjacent - 1; v++) {
                System.out.print((graph[i].get(v) + 1) + ", ");
            }
            System.out.println(graph[i].get(v) + 1);
        }
    }

}
