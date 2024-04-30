package de.htwsaar.sarkhovska.palindrom;

public class PalindromRecursive implements Palindrom {

    @Override
    public boolean isPalindrom(String word) {
        int len = word.length();
        if (len<2) {
            return true;
        }
        if (word.charAt(0)!=word.charAt(len-1)) {
            return false;
        }
        return isPalindrom(word.substring(1,len-1));
    }

}
