/*
* Jett Moy - jlmoy
* Andrew Exton - aexton
* Project 5
* Part 1 & 2
*/

import java.util.LinkedList;
import java.util.Scanner;

public class DiGraphTest {
    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices: ");
        DiGraph graph = new DiGraph(sc.nextInt());

        printMenu();

        int from, to;

        switch(sc.next()) {
            case "a":
                from = sc.nextInt();
                to = sc.nextInt();
                graph.addEdge(from, to);
                System.out.println(String.format("Added Edge:\n- From: %d\n- To: %d", from, to));
                break;
            case "d":
                from = sc.nextInt();
                to = sc.nextInt();
                graph.deleteEdge(from, to);
                System.out.println(String.format("Deleted Edge:\n- From: %d\n- To: %d", from, to));
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
            default:
                System.out.println("Invalid option.");
        }
    }

    private void printMenu() {
        System.out.println("Choose one of the following operations: ");
        System.out.println("a - add edge");
        System.out.println("d - delete edge");
        System.out.println("e - edge count");
        System.out.println("v - vertex count");
        System.out.println("p - print graph");
        System.out.println("q - Quit\n");
    }

}
