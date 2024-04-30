package de.htwsaar.sarkhovska.palindrom;

public class PalindromIterative implements Palindrom {

    @Override
    public boolean isPalindrom(String word) {
        for (int i = 0; i < word.length() / 2; ++i) {
            if (word.charAt(i) != word.charAt(word.length()-1-i)) {
                return false;
            }
        }
        return true;
    }

}
