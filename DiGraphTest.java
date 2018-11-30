/*
* Jett Moy - jlmoy
* Andrew Exton - aexton
* Project 5
* Part 1 & 2
*/

public class DiGraphTest {
    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices: ");
        DiGraph graph = new DiGraph(sc.nextInt());

        printMenu();

        switch(sc.next()) {
            case "a":
                int from, to;
                from = sc.nextInt();
                to = sc.nextInt();
                addEdge(from, to);
                System.out.println(String.format("Edge:\n- From: %d\n- To: %d", from, to));
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
