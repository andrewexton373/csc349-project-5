/*
* Jett Moy - jlmoy
* Andrew Exton - aexton
* Project 5
* Part 1 & 2
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class DiGraphTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        DiGraph graph = new DiGraph(sc.nextInt());

        boolean done = false;

        printMenu();

        do {

            int from, to;
            System.out.print("Enter operation: ");

            if (sc.hasNext()) {
                switch (sc.next()) {
                    case "a":
                        System.out.print("Add edge: ");
                        from = sc.nextInt();
                        to = sc.nextInt();
                        graph.addEdge(from, to);
                        System.out.println(String.format("\nAdded Edge:\n- From: %d\n- To: %d\n", from, to));
                        break;
                    case "d":
                        System.out.print("Delete edge: ");
                        from = sc.nextInt();
                        to = sc.nextInt();
                        graph.deleteEdge(from, to);
                        System.out.println(String.format("\nDeleted Edge:\n- From: %d\n- To: %d\n", from, to));
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
                        graph.topSort().toArray(arr);
                        int i;
                        System.out.println("Topological Sort: ");
                        for (i = 0; i < arr.length - 1; i++) {
                            System.out.println((arr[i] + 1) + ", ");
                        }
                        System.out.println(arr[i] + 1);
                        break;
                    case "q":
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }

        } while (!done);

    }

    private static void printMenu() {
        System.out.println("Choose one of the following operations: ");
        System.out.println("a - add edge");
        System.out.println("d - delete edge");
        System.out.println("e - edge count");
        System.out.println("v - vertex count");
        System.out.println("p - print graph");
        System.out.println("t - topological sort");
        System.out.println("q - Quit\n");
    }

}
