/*
* Jett Moy - jlmoy
* Andrew Exton - aexton
* Project 5
* Part 1 & 2 & 3 & 4
*/


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class DiGraphTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        DiGraph graph = new DiGraph(sc.nextInt());
        System.out.println();

        boolean done = false;

        printMenu();
        sc.nextLine();

        do {

            int from, to, source;
            System.out.print("Enter operation: ");

            if (sc.hasNext()) {
                String input = sc.nextLine();

                if (input.split(" ").length > 1) {
                    System.out.println("Invalid option.");
                    continue;
                }

                System.out.println();

                switch (input) {
                    case "a":
                        System.out.print("Add edge: ");
                        from = sc.nextInt();
                        to = sc.nextInt();
                        sc.nextLine();
                        System.out.println();
                        graph.addEdge(from, to);
                        System.out.println(String.format("\nAdded Edge: (%d, %d)\n", from, to));
                        break;
                    case "d":
                        System.out.print("Delete edge: ");
                        from = sc.nextInt();
                        to = sc.nextInt();
                        sc.nextLine();
                        System.out.println();
                        graph.deleteEdge(from, to);
                        System.out.println(String.format("\nDeleted Edge: (%d, %d)\n", from, to));
                        break;
                    case "e":
                        System.out.println("Number of edges is: " + graph.edgeCount());
                        break;
                    case "v":
                        System.out.println("Number of vertexes is: " + graph.vertexCount());
                        break;
                    case "p":
                        graph.print();
                        break;
                    case "t":
                        Integer[] arr = new Integer[graph.vertexCount()];
                        try {
                            graph.topSort().toArray(arr);
                            int i;
                            System.out.print("Topological Sort: ");
                            for (i = 0; i < arr.length - 1; i++) {
                                System.out.print((arr[i] + 1) + ", ");
                            }
                            System.out.println(arr[i] + 1);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Graph is cyclic.");
                        }
                        break;

                    case "i":
                        System.out.print("Enter vertices: ");
                        from = sc.nextInt();
                        to = sc.nextInt();
                        sc.nextLine();
                        System.out.println();
                        boolean isPath = graph.isTherePath(from, to);
                        System.out.println("Has path: " + isPath);
                        break;
                    case "l":
                        System.out.print("Enter vertices: ");
                        from = sc.nextInt();
                        to = sc.nextInt();
                        sc.nextLine();
                        System.out.println();
                        System.out.println("Path length: " + graph.lengthOfPath(from, to));
                        break;
                    case "s":
                        System.out.print("Enter vertices: ");
                        from = sc.nextInt();
                        to = sc.nextInt();
                        sc.nextLine();
                        System.out.println();
                        graph.printPath(from, to);
                        break;
                    case "b":
                        System.out.print("Source vertex: ");
                        source = sc.nextInt();
                        sc.nextLine();
                        System.out.println();
                        graph.printTree(source);
                        break;
                    case "q":
                        done = true;
                        System.out.println("Good Bye.");
                        break;
                    default:
                        sc.nextLine();
                        System.out.println();
                        System.out.println("Invalid option.");
                }
            }

        } while (!done);

        sc.close();

    }

    private static void printMenu() {
        System.out.println("Choose one of the following operations: ");
        System.out.println("a - add edge");
        System.out.println("d - delete edge");
        System.out.println("e - edge count");
        System.out.println("v - vertex count");
        System.out.println("p - print graph");
        System.out.println("t - topological sort");
        System.out.println("i - is there a path");
        System.out.println("l - length of the path");
        System.out.println("s - shortest path");
        System.out.println("b - print breadth-first-tree");
        System.out.println("q - Quit\n");
    }

}
