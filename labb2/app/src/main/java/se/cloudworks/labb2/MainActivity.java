package se.cloudworks.labb2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    FileRead read = new FileRead();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            read.readWordlist(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}