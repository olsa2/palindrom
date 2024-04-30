package de.htwsaar.sarkhovska.palindrom;

import java.io.*;
import java.util.List;
import java.util.Arrays;

public class PalindromTestDrive {
    static Palindrom paliIterative = new PalindromIterative();
    static Palindrom paliRecursive = new PalindromRecursive();
    static StringBuffer bufferLen = new StringBuffer();
    static StringBuffer bufferIterative = new StringBuffer();
    static StringBuffer bufferRecursive = new StringBuffer();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No arguments provided");
            printUsage();
            System.exit(1);
        }

        ClassLoader loader = ClassLoader.getSystemClassLoader();

        List<String> argList = Arrays.asList(args);

        if (argList.get(0).equals("-f")) {
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

        } else {
            for (String word : argList) {
                doMeasurement(word);
            }
        }

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
        //bufferIterative.append(word.length());
        //bufferIterative.append(",");
        bufferIterative.append(end - start);

        start = System.nanoTime();
        paliRecursive.isPalindrom(word);
        end = System.nanoTime();

        if (!bufferRecursive.isEmpty()) {
            bufferRecursive.append(",");
        }
        //bufferRecursive.append(word.length());
        //bufferRecursive.append(",");
        bufferRecursive.append(end - start);

        System.out.println(word + " = " + bPalindrom);
    }
}
