package de.htwsaar.sarkhovska.palindrom;

import java.io.*;
import java.util.List;
import java.util.Arrays;

public class PalindromTestDrive {
    // static instance of PalindromIterative accessible global
    static Palindrom paliIterative = new PalindromIterative();

    // static instance of PalindromRecursive accessible global
    static Palindrom paliRecursive = new PalindromRecursive();

    //static StringBuffer to store comma separated lengths of examined words
    static StringBuffer bufferLen = new StringBuffer();

    //static StringBuffer to store runtime measurement for the iterative implementation
    static StringBuffer bufferIterative = new StringBuffer();

    //static StringBuffer to store runtime measurement for the recursive implementation
    static StringBuffer bufferRecursive = new StringBuffer();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No arguments provided");
            printUsage();
            System.exit(1);
        }

        ClassLoader loader = ClassLoader.getSystemClassLoader();

        List<String> argList = Arrays.asList(args);

        if (argList.get(0).equals("-f")) { // -f flag was specified at the first place in the arguments, read words from the resource file words.txt
            File file = new File(loader.getResource("words.txt").getFile());
            try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
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

        } else { //words are provided inline in the command string
            for (String word : argList) {
                doMeasurement(word);
            }
        }

        // save measurements to the resource file measurement.txt
        File file = new File(loader.getResource("measurement.txt").getFile());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.append(bufferLen.toString());
            bw.append("\n");
            bw.append(bufferIterative.toString());
            bw.append("\n");
            bw.append(bufferRecursive.toString());
            System.out.println("\nMeasurement written to the " + file.getPath() );

        } catch (IOException e1) {
            e1.printStackTrace();
            System.exit(5);
        }
    }

    static void printUsage() {
        System.out.println("Usage: PalindromTestDrive <word1, word2,...,word N>/-f");
    }

    static void doMeasurement(String word) {
        if (!bufferLen.isEmpty()) {
            bufferLen.append(",");
        }
        bufferLen.append(word.length());

        long start = System.nanoTime();
        boolean bPalindrom = paliIterative.isPalindrom(word);
        long end = System.nanoTime();

        if (!bufferIterative.isEmpty()) {
            bufferIterative.append(",");
        }
        bufferIterative.append(end - start);

        start = System.nanoTime(); //get the start time
        paliRecursive.isPalindrom(word); //run the method to be measured
        end = System.nanoTime(); //get the end time

        if (!bufferRecursive.isEmpty()) {
            bufferRecursive.append(",");
        }
        bufferRecursive.append(end - start); //write out the runtime interval

        System.out.println(word + " = " + bPalindrom);
    }
}
