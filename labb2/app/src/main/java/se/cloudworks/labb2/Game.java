package se.cloudworks.labb2;


import android.util.Log;
import java.lang.String;

public class Game {
    public void checkLetter(char ch, String word){

        int count = 0;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ch) {
                count++;
            }
        }
            Log.d("walla", "Här är boktaven" + count);
        /*
        word.chars().filter(ch -> ch == 'e').count();
        if(word.contains(ch)){
            Log.d("walla", "Här är boktaven" + word.indexOf(ch));
        }
        */


        /*
        if(word.contains(ch)){

            Log.d("walla", "Rätt");
        }
        */


    }
}
