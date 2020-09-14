package se.cloudworks.labb2;

import android.view.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRead {

    public void readWordlist(MainActivity view) throws IOException {
            List myList = new ArrayList();
            InputStream is = view.getResources().openRawResource(R.raw.wordlist);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String strLine;
            while ((strLine = br.readLine()) != null)   {
                myList.add(strLine);

            }
            System.out.println (myList);
            is.close();

    }
}
