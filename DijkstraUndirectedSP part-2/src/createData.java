import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class createData {
    public static void print(Object o) {
        System.out.println(o);

    }

    Random rand = new Random();
    static int count = -1;

    public String dataTest() {
        count++;
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'I', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


        String first = "";
        String second = "";
        int x = rand.nextInt(10000);
        int y = rand.nextInt(10000);
        for (int i = 0; i < 30; i++) {
            first += alphabet[rand.nextInt(26)];
            second += alphabet[rand.nextInt(26)];


        }

        return count + " " + x + " " + y + " " + first + " " + second + "\n";


    }

    public String edgeTest() {

        int first = rand.nextInt(199999);
        int second = rand.nextInt(199999);
        return first + " " + second + "\n";


    }


    public void writeFile() {
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\m_722\\IdeaProjects\\hmw4-part2\\src\\test4.txt");
            for (int i = 0; i < 200000; i++) {
                myWriter.write(dataTest());

            }
            for (int i = 0; i < 400000; i++) {
                myWriter.write(edgeTest());

            }


            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        createData c = new createData();
        c.writeFile();

    }
}
