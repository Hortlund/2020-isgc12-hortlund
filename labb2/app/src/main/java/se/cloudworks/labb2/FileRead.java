package se.cloudworks.labb2;

import android.view.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileRead {

    public String getWord(MainActivity view) throws IOException {
            List myList = new ArrayList();
            InputStream is = view.getResources().openRawResource(R.raw.wordlist);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String strLine;
            while ((strLine = br.readLine()) != null)   {
                myList.add(strLine);

            }
            is.close();

            Random random = new Random();
            return (String) myList.get(random.nextInt(myList.size()));

    }
}
