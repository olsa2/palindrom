package de.htwsaar.sarkhovska.palindrom;

public class RecursiveExamples {
    static public int factorialRecursive(int n) {
        if (n==0) {
            return 1;
        }
        return n*factorialRecursive(n-1);
    }

    static public int sumRecursive(int n) {
        if (n==0) {
            return 0;
        }
        return n + sumRecursive(n-1);
    }

    static public int fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
