import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * 12/03/2017
 * CSC201 Assignment 12.15
 *
 * Write a program to create a file named Exercise12_15.txt if it does not exist.
 * Write 100 integers created randomly into the file using text I/O.
 * Integers are separated by spaces in the file. Read the data back from the file
 * and display the data in increasing order.
 *
 * @author Laura Witherspoon
 */

public class Main {
    public static void main(String[] args) throws IOException {

        File file =  new File("Exercise12_15.txt");

        // Exit if file already exists
        if (file.exists()) {
            System.out.println("File already exists.");
            System.exit(0);
        }

        // Create file and write 100 random numbers in it
        try (
            PrintWriter output = new PrintWriter(file);
        ) {
            for (int i = 0; i < 100; i++) {
                output.print(generateRandomInt() + " ");
            }
        }

        // Display sorted content, otherwise display error
        // sortedContent() throws FileNotFoundException so need to add catch to handle it
        try {
            for (int j : sortedContent(file)) {
                System.out.println(j);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Generates a random integer
     * @return Random integer between 0 and 100
     */
    private static int generateRandomInt() {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt((100 - 1) + 1) + 1;
    }

    /**
     * Reads integers from provided file, sorts and returns it as array list
     * @param file a File object
     * @return Array list of integers in sorted ascending order
     * @throws FileNotFoundException Throws error if file does not exist
     */
    private static ArrayList<Integer> sortedContent(File file) throws FileNotFoundException {
        ArrayList<Integer> fileContents = new ArrayList<>();

        // Throw error if file does not exist
        if (!file.exists())
            throw new FileNotFoundException("File does not exist.");

        try (
            Scanner input = new Scanner(file);
        ) {
            while (input.hasNext()) {
                fileContents.add(input.nextInt());
            }

            Collections.sort(fileContents);
        }

        return fileContents;
    }
}
