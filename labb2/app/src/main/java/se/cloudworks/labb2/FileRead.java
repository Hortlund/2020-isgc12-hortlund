package se.cloudworks.labb2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileRead implements WordHandle {
    @Override
    public String getWord(MainActivity view) throws IOException {
        List wordList = new ArrayList();
        String line;

        InputStream is = view.getResources().openRawResource(R.raw.wordlist);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        while ((line = br.readLine()) != null)   {
            wordList.add(line);

        }
        is.close();
        return randomizeWord(wordList);
    }

    @Override
    public String randomizeWord(List myList) {
        Random random = new Random();
        return (String) myList.get(random.nextInt(myList.size()));
    }
}
