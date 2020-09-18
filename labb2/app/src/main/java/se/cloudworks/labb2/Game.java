package se.cloudworks.labb2;


import java.lang.String;

public class Game {
    public int checkLetter(MainActivity view, char ch, String word){

        int count = 0;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ch) {
                count++;
            }
        }

        return count;
    }
}
