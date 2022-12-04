import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;








public class Test {

    Test.Node[] City;// node array to keep the cities and their properties.
    EdgeWeightedGraph g; // graph to calculate shortest path.



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
        String Name;
        int xC;
        int yC;
        int index;

        public Node(int index, int xC, int yC, String name) {
            Name = name;
            this.xC = xC;
            this.yC = yC;
            this.index = index;

        }

        @Override

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
            City = new Test.Node[myReader.nextInt()];
            g = new EdgeWeightedGraph(myReader.nextInt());
            String data = myReader.nextLine();
            int counter = 0;
            while (true) {
                data = myReader.nextLine();
                if (data == null) continue;

                String[] arr = data.split(" ");

                City[counter] = new Test.Node(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), arr[3]);
                counter++;


                if (counter == City.length) break;

            }
            while (myReader.hasNext()) {

                data = myReader.nextLine();
                String[] arr = data.split(" ");
                int first = Integer.parseInt(arr[0]);
                int second = Integer.parseInt(arr[1]);

                g.addEdge(new Edge(first, second,
                        Euclidean(City[first].xC, City[first].yC, City[second].xC, City[second].yC)));


            }


            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void Sp(int source, int target) {
        //--------------------------------------------------------
        // Summary: Creates Sp object and calls shortest path class to get shortest path.
        // prints results according to retrieved data from SP.
        // name is given.
        // Precondition: the shortest path is unknown
        // Postcondition:  the shortest path is known and results printed according to purpose.
        //--------------------------------------------------------

        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(g, source);

        int counter = -1;
        for (Edge e : sp.pathTo(target)
        ) {
            counter++;
        }
        print(counter + 2 + " cities to be visited:");
        print(City[source].Name);
        for (Edge e : sp.pathTo(target)
        ) {
            print(City[e.other()].Name);
        }
        print("Distance: " + (int) sp.distTo(target) + " km");

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


    public void test1(String s) {
        //--------------------------------------------------------
        // Summary: calculates sp in a 1000 cities 2000 edge graph
        // name is given.
        // Precondition: the time and used memory is unknown.
        // Postcondition:  the time and used memory is known.
        //--------------------------------------------------------
        Reader(s);
        Sp(0, 888);
    }

    public void test2(String s) {
        //--------------------------------------------------------
        // Summary: calculates sp in a 10000 cities 20000 edge graph
        // name is given.
        // Precondition: the time and used memory is unknown.
        // Postcondition:  the time and used memory is known.
        //--------------------------------------------------------
        Reader(s);
        Sp(0, 9990);
    }

    public void test3(String s) {
        //--------------------------------------------------------
        // Summary: calculates sp in a 100000 cities 200000 edge graph
        // name is given.
        // Precondition: the time and used memory is unknown.
        // Postcondition:  the time and used memory is known.
        //--------------------------------------------------------
        Reader(s);
        Sp(0, 88888);
    }

    public void test4(String s) {
        //--------------------------------------------------------
        // Summary: calculates sp in a 200000 cities 400000 edge graph
        // name is given.
        // Precondition: the time and used memory is unknown.
        // Postcondition:  the time and used memory is known.
        //--------------------------------------------------------
        Reader(s);
        Sp(0, 199800);
    }
    public void test5(String s) {
        //--------------------------------------------------------
        // Summary: calculates sp in a 200000 cities 400000 edge graph
        // name is given.
        // Precondition: the time and used memory is unknown.
        // Postcondition:  the time and used memory is known.
        //--------------------------------------------------------
        Reader(s);
        Sp(1, 80);
    }
    private static final long MEGABYTE = 1024L * 1024L; // to convert byte to mb

    public static long bytesToMegabytes(long bytes) {
        //--------------------------------------------------------
        // Summary: converts bytes to megabyte.
        // name is given.
        //--------------------------------------------------------
        return bytes / MEGABYTE;
    }

    public static void main(String[] args) {

        //--------------------------------------------------------
        // Summary: calculates time and memory usage for the test1 method
        //--------------------------------------------------------



        print("Test1");
        long startTime = System.currentTimeMillis();
        Test m = new Test();
        m.test1("C:\\Users\\m_722\\IdeaProjects\\hmw4-part2\\src\\test1.txt");
        print("");
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                + bytesToMegabytes(memory));
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime);

        print("");
        print("");
        print("");




        print("Test5");
        startTime = System.currentTimeMillis();
        Test m4 = new Test();
        m4.test5("C:\\Users\\m_722\\IdeaProjects\\hmw4-part2\\src\\turkeymap.txt");
        print("");
        runtime = Runtime.getRuntime();
        runtime.gc();
        memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                + bytesToMegabytes(memory));
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime);










        //--------------------------------------------------------
        // Summary: calculates time and memory usage for the test2 method
        //--------------------------------------------------------
        print("Test2");

        startTime = System.currentTimeMillis();
        Test m1 = new Test();
        m1.test2("C:\\Users\\m_722\\IdeaProjects\\hmw4-part2\\src\\test2.txt");
        print("");
        runtime = Runtime.getRuntime();
        runtime.gc();
        memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                + bytesToMegabytes(memory));
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime);
        print("");
        print("");
        print("");

        //--------------------------------------------------------
        // Summary: calculates time and memory usage for the test3 method
        //--------------------------------------------------------
        print("Test3");

        startTime = System.currentTimeMillis();
        Test m2 = new Test();
        m2.test3("C:\\Users\\m_722\\IdeaProjects\\hmw4-part2\\src\\test3.txt");
        print("");
        runtime = Runtime.getRuntime();
        runtime.gc();
        memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                + bytesToMegabytes(memory));
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime);
        print("");
        print("");
        print("");

        //--------------------------------------------------------
        // Summary: calculates time and memory usage for the test4 method
        //--------------------------------------------------------


        print("Test4");
        startTime = System.currentTimeMillis();
        Test m3 = new Test();
        m3.test4("C:\\Users\\m_722\\IdeaProjects\\hmw4-part2\\src\\test4.txt");
        print("");
        runtime = Runtime.getRuntime();
        runtime.gc();
        memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                + bytesToMegabytes(memory));
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime);








    }


}
