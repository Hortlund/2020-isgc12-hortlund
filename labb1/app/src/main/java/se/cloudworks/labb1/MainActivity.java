package se.cloudworks.labb1;

import androidx.appcompat.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toastMe(View view) {
        Toast.makeText(this, "Hello World!", Toast.LENGTH_LONG).show();



    }

}