package se.cloudworks.labb2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Implements Wordhandle just to show proof of knowledge.
public class FileRead implements WordHandle {
    @Override
    public String getWord(MainActivity view) throws IOException {
        //New list to hold the strings
        List wordList = new ArrayList();
        //current line in the buffer reader
        String line;

        //new inputstream and buffer reader, gets the wordlist from raw resources
        InputStream is = view.getResources().openRawResource(R.raw.wordlist);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //As long as the the line is not empty continue to add words to the list
        while ((line = br.readLine()) != null)   {
            wordList.add(line);

        }
        //close the file
        is.close();
        //Returns a random word to the game class
        return randomizeWord(wordList);
    }

    @Override
    public String randomizeWord(List myList) {
        //just picks a random word from the list and returns it
        Random random = new Random();
        return (String) myList.get(random.nextInt(myList.size()));
    }
}
