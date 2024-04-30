package de.htwsaar.sarkhovska.palindrom;

import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class PalindromTestDrive {
    static Palindrom paliIterative = new PalindromIterative();
    static Palindrom paliRecursive = new PalindromRecursive();
    static StringBuffer bufferIterative = new StringBuffer();
    static StringBuffer bufferRecursive = new StringBuffer();

    final static String MEASUREMENT_FILE = "C:\\Users\\osarkhov\\eclipse-workspace-process.easy_rest\\Palindrom\\resources\\measurement.txt";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No arguments provided");
            printUsage();
            System.exit(1);
        }

        if (args[0].equals("-f") && args.length == 1) {
            System.err.println("Please provide the file name");
            printUsage();
            System.exit(2);
        }

        List<String> argList = Arrays.asList(args);

        if (argList.get(0).equals("-f")) {
            try (BufferedReader bf = new BufferedReader(new FileReader(argList.get(1)))) {
                String word = null;
                while ((word = bf.readLine()) != null) {
                    doMeasurement(word);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(3);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(4);
            }

        } else {
            for (String word : argList) {
                doMeasurement(word);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MEASUREMENT_FILE))) {
            bw.append(bufferIterative.toString());
            bw.append("\n");
            bw.append(bufferRecursive.toString());
            System.out.println("\nMeasurement written to the " + MEASUREMENT_FILE );

        } catch (IOException e1) {
            e1.printStackTrace();
            System.exit(5);
        }
    }

    static void printUsage() {
        System.out.println("Usage: PalindromTestDrive <word1, word2,...,word N>/-f '<file with string>'");
    }

    static void doMeasurement(String word) {
        long start = System.nanoTime();
        boolean bPalindrom = paliIterative.isPalindrom(word);
        long end = System.nanoTime();

        if (!bufferIterative.isEmpty()) {
            bufferIterative.append(",");
        }
        bufferIterative.append(word.length());
        bufferIterative.append(",");
        bufferIterative.append(end - start);

        start = System.nanoTime();
        paliRecursive.isPalindrom(word);
        end = System.nanoTime();

        if (!bufferRecursive.isEmpty()) {
            bufferRecursive.append(",");
        }
        bufferRecursive.append(word.length());
        bufferRecursive.append(",");
        bufferRecursive.append(end - start);

        System.out.println(word + " = " + bPalindrom);
    }
}
