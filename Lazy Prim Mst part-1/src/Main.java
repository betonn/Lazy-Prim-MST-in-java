import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    String[] CityList; // to keep track of the cities and their properties.
    EdgeWeightedGraph graph; // graph to calculate mst

    public void Reader(String path) {
        //--------------------------------------------------------
        // Summary: Reads file, creates an array to keep the cities,creates graph.
        // name is given.
        // Precondition: the file is not been read and the graph is not exist yet.
        // Postcondition:  the file is read and parsed to serve the purpose of the program and a graph created.
        //--------------------------------------------------------
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            String input = myReader.nextLine();

            String[] arr = input.split(" ");
            CityList = new String[arr.length];

            graph = new EdgeWeightedGraph(arr.length);
            for (int i = 0; i < arr.length; i++) {
                CityList[i] = arr[i]; // keep the name of the cities.
            }


            int counter = 0;
            while (myReader.hasNextLine()) {

                String input2 = myReader.nextLine();
                String[] arr2 = input2.split(" ");
                int first = FindCity(arr2[0]); // search for index of the cities
                int second = FindCity(arr2[1]);
                Edge e = new Edge(first, second, Integer.parseInt(arr2[2])); // edge that represent cities and distances.
                graph.addEdge(e); //adding edge to the graph.

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        Mst();

    }

    private class Node implements Comparable<Node> {
        //-----------------------------------------------------
        // Title: Node class
        // Author:Abdusselam koç
        // ID: 4931214741
        // section: 2
        // Assignment: 4
        // Description: This class helps to calculate the lexicographical order between edges in case of need.
        //-----------------------------------------------------

        String first; //edge either
        String second; // edge other
        int weight;

        // prints the edge
        @Override
        public String toString() {
            return first + " " + second + " " + weight;
        }

        // constructor to create the edge.
        public Node(String first, String second, int weight) {
            this.weight = weight;
            this.first = first;
            this.second = second;

        }

        // compare method that compares two edges according to their lexicographical order.
        @Override
        public int compareTo(Node o) {
            return this.second.compareTo(o.second);
        }
    }

    private void Mst() {
        //--------------------------------------------------------
        // Summary: creates prim mst object and calculates the MST for the graph and returns the result of the MST.
        // name is given.
        // Precondition: The MST of the graph is unknown.
        // Postcondition:  The MST of the graph is known.
        //--------------------------------------------------------

        LazyPrimMST mst = new LazyPrimMST(graph); // prim object
        ArrayList<Edge> list = mst.edges(); // mst edges
        Node[] arr = new Node[list.size()]; // node array to add the mst for the lexicographical order.


        for (int i = 0; i < list.size(); i++) {
            arr[i] = new Node(CityList[list.get(i).either()], CityList[list.get(i).other()], (int) list.get(i).weight());
            //Creating Nodes to check whether the lexicographical order is correct or not. if not correct the lexicographical order.
        }

        arr = bubbleSort(arr); // checks for the lexicographical order and corrects it if needed.
        System.out.println((int) mst.weight());// prints MST weight.
        for (int i = 0; i < arr.length; i++) {

            System.out.println(arr[i]); // prints cities of the mst according to their lexicographical order.
        }


    }

    private Node[] bubbleSort(Node[] arr) {
        //--------------------------------------------------------
        // Summary: classical bubble sorting algorithm designed to check for lexicographical order
        // and sort the array according to lexicographical order.
        // name is given.
        // Precondition: The lexicographical order is established yet.
        // Postcondition:  The lexicographical order is established.
        //--------------------------------------------------------

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].first.equals(arr[j + 1].first)) {
                    if (arr[j].weight == arr[j + 1].weight) {
                        if (arr[j].compareTo(arr[j + 1]) > 0) {
                            // swap arr[j+1] and arr[j]
                            Node temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }

                    }

                }
            }


        }

        return arr;

    }


    private int FindCity(String s) {
        //--------------------------------------------------------
        // Summary:finds the city index in the city array and returns it index to create edges.
        // name is given.
        // Precondition: the city index unknown.
        // Postcondition:   the city index known.
        //--------------------------------------------------------
        for (int i = 0; i < CityList.length; i++) { // foor loop to check for all cities.
            if (CityList[i].equals(s)) {
                return i;
            }

        }

        return -1;
    }


    public static void main(String[] args) {

        Main m = new Main();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        m.Reader(input.trim());
    }


}
