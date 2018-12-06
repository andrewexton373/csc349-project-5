/*
* Jett Moy - jlmoy
* Andrew Exton - aexton
* Project 5
* Part 1 & 2 & 3 & 4
*/


import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
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

    private class VertexInfo {
        private int length; // distance/length of path
        private int pred; // predecessor/parent of vertex

        public VertexInfo(int length, int pred) {
            this.length = length;
            this.pred = pred;
        }
    }

    private ArrayList<VertexInfo> BFS(int s) {
        ArrayList<VertexInfo> bfs = new ArrayList<VertexInfo>(N);
        for (int vNum = 1; vNum <= N; vNum++) {
            bfs.add(new VertexInfo(Integer.MAX_VALUE, -1));
        }
        bfs.set(s, new VertexInfo(0, -1));

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            VertexInfo currentInfo = bfs.get(current);

            LinkedList<Integer> neighbors = graph.get(current);
            for (Integer neighbor : neighbors) {
                VertexInfo neighborInfo = bfs.get(neighbor);

                if (currentInfo.length + 1 < neighborInfo.length) {
                    queue.add(neighbor);
                    bfs.set(neighbor, new VertexInfo(currentInfo.length + 1, current));
                }
            }
        }

        return bfs;
    }

    // returns true if there is a path
    // from "from" vertex to "to" vertex,
    // false otherwise
    public boolean isTherePath(int from, int to) {
        ArrayList<VertexInfo> bfs = BFS(from - 1);
        VertexInfo result = bfs.get(to - 1);
        if (result.length < Integer.MAX_VALUE)
            return true;
        else
            return false;
    }

    // returns the length of the shortest path
    // to the "to" vertex from the "from" vertex
    public int lengthOfPath(int from, int to) {
        ArrayList<VertexInfo> bfs = BFS(from - 1);
        VertexInfo result = bfs.get(to - 1);
        return result.length;
    }

    // arranges the output of the shortest path
    // from "from" vertex to "to" vertex
    // IF it is reachable -> print natural numbering of path
    // ELSE print "There is no path"
    public void printPath(int from, int to) {
        ArrayList<VertexInfo> results = BFS(from-1);
        VertexInfo toInfo = results.get(to-1);

        Stack<Integer> path = new Stack<Integer>();
        path.push(to);

        int current = toInfo.pred;
        while (current != -1) {
            VertexInfo parentInfo = results.get(current);
            path.push(current + 1);
            current = parentInfo.pred;
        }

        while (!path.isEmpty()) {
            System.out.printf("%d ", path.pop());
        }
        System.out.printf("\n");
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

        if (graph.size() == 0 || graph == null) return new ArrayList<Integer>();

        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < N; i++) {
            if (indegrees.get(i).intValue() == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer vertex = queue.remove();
            result.add(vertex);

            LinkedList<Integer> vertexes = graph.get(vertex);
            for (Integer edge : vertexes) {
                indegrees.set(edge, indegrees.get(edge) - (Integer)1);
                if (indegrees.get(edge) == (Integer)0) {
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

    // this method prints the breadth-first-tree
    // for a given source vertex s
    public void printTree(int s) {
        System.out.printf("\nbreadth-first-tree (source: %d) \n", s);
        TreeNode rootNode = buildTree(s - 1);
        printTree_AUX(rootNode, 0);
        System.out.println();
    }

    private void printPaddingTabs(int level) {
        for (int lvl = 0; lvl < level; lvl++)
            System.out.print("    ");
    }

    // Auxilary recursive method for printTree()
    private void printTree_AUX(TreeNode node, int level) {

        printPaddingTabs(level);

        // Print vertex number
        System.out.printf("%d\n", (int) node.vertex_num + 1);

        // Print children nodes
        for (TreeNode child : node.vertex_children) {
            printTree_AUX(child, level + 1);
        }
    }

    // returns the root of the breadth-first-tree for the given source-vertex.
    private TreeNode buildTree(int s) {

        TreeNode[] nodes = new TreeNode[N];
        for (int vertNum = 0; vertNum < N; vertNum++) {
            TreeNode node = new TreeNode();
            node.vertex_children = new LinkedList<TreeNode>();
            node.vertex_num = vertNum;
            nodes[vertNum] = node;
        }

        ArrayList<VertexInfo> bfs = BFS(s);
        for (VertexInfo info : bfs) {
            Integer nodeNumber = bfs.indexOf(info);
            Integer parent = info.pred;

            if (parent != -1) { // if has a parent node
                TreeNode parentNode = nodes[parent];
                parentNode.vertex_children.add(nodes[nodeNumber]);
            }
        }

        return nodes[s];
    }

    private class TreeNode {
        int vertex_num;
        LinkedList<TreeNode> vertex_children;

        public String toString() {
            String result = "NODE: vert " + ((int) vertex_num + 1) + " | ";
            for (TreeNode child: vertex_children) result += child.vertex_num + 1;
            result += "\n";
            return result;
        }
    }

}
