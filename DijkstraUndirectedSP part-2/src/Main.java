import java.io.File;
import java.io.FileNotFoundException;
import java.math.*;
import java.util.Scanner;



public class Main {


    Node[] City; // node array to keep the cities and their properties.
    EdgeWeightedGraph g; // graph to calculate shortest path.

    //easy print method to print anything.
    public static void print(Object o) {
        System.out.println(o);

    }

    private class Node {
        //-----------------------------------------------------
        // Title: Node class
        // Author:Abdusselam koç
        // ID: 4931214741
        // Section: 1
        // Assignment: 4
        // Description: This class helps to keep the track of the cities and their properties such as x,y,name and index.
        //-----------------------------------------------------

        String Name; // name of the cities
        int xC; // x cordinate
        int yC; // y cordinate
        int index;

        //constructor to createData node object
        public Node(int index, int xC, int yC, String name) {
            Name = name;
            this.xC = xC;
            this.yC = yC;
            this.index = index;

        }

        @Override
        // to string method to print Node objects
        public String toString() {
            return "Node{" +
                    "Name='" + Name + '\'' +
                    ", xC=" + xC +
                    ", yC=" + yC +
                    ", index=" + index +
                    '}';
        }
    }


    public void Reader(String s) {
        //--------------------------------------------------------
        // Summary: Reads file, creates an array to keep the cities,creates graph.
        // name is given.
        // Precondition: the file is not been read and the graph is not exist yet.
        // Postcondition:  the file is read and parsed to serve the purpose of the program and a graph created.
        //--------------------------------------------------------

        try {
            File myObj = new File(s);
            Scanner myReader = new Scanner(myObj);
            City = new Node[myReader.nextInt()]; //  Node array to keep the cities and their properties.
            g = new EdgeWeightedGraph(myReader.nextInt()); // graph object to keep edges
            String data = myReader.nextLine();
            int counter = 0;
            while (true) {
                data = myReader.nextLine();
                if (data == null) continue;

                String[] arr = data.split(" ");

                City[counter] = new Node(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), arr[3]);
                //node object creation
                counter++;


                if (counter == City.length) break;

            }
            while (myReader.hasNext()) {

                data = myReader.nextLine();
                String[] arr = data.split(" ");
                int first = Integer.parseInt(arr[0]); //edge either
                int second = Integer.parseInt(arr[1]); // edge other

                g.addEdge(new Edge(first, second,
                        Euclidean(City[first].xC, City[first].yC, City[second].xC, City[second].yC)));
                // edge object been created and added to the graph.

            }


            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void Sp(String sour, String tar) {

        //--------------------------------------------------------
        // Summary: Creates Sp object and calls shortest path class to get shortest path.
        // prints results according to retrieved data from SP.
        // name is given.
        // Precondition: the shortest path is unknown
        // Postcondition:  the shortest path is known and results printed according to purpose.
        //--------------------------------------------------------


        int source = findCity(sour); // index of the source city
        int target = findCity(tar); // index of the target city


        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(g, source); // shortest path object

        int counter = -1; // counter to keep track of the visited cities.
        for (Edge e : sp.pathTo(target) // loop to calculate the number of the visited cities
        ) {
            counter++;
        }
        print(counter + 2 + " cities to be visited:"); //visited cities print
        print(City[source].Name); // prints source city
        for (Edge e : sp.pathTo(target)
        ) {
            print(City[e.other()].Name); // prints other cities.
        }
        print("Distance: " + (int) sp.distTo(target) + " km"); //prints distance of the path in km.

    }

    public int findCity(String source) {

        //--------------------------------------------------------
        // Summary: Finds needed Cities and returns their index from the City array.
        // name is given.
        // Precondition: The city index is unknown
        // Postcondition:  the city index is known and the name of the is can be printed now.
        //--------------------------------------------------------


        for (int i = 0; i < City.length; i++) {
            if (City[i].Name.equals(source.trim())) return i; //checks for evert city im the array then returns
            // the index of the required city.
        }
        return 0;
    }

    public static int Euclidean(int x1, int y1, int x2, int y2) {

        //--------------------------------------------------------
        // Summary: calculates the Euclidean path
        // name is given.
        // Precondition: the length between two cities is unknown
        // Postcondition:  the length between cities is known.
        //--------------------------------------------------------


        return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)); //𝑑 = √(𝑥2 − 𝑥1)2 + (𝑦2 − 𝑦1)2
    }


    public static void main(String[] args) {
        Main m = new Main();
        Scanner sc = new Scanner(System.in);
        m.Reader(sc.next());
        m.Sp(sc.next(), sc.next());
    }

}
