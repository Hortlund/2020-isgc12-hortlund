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

    //Name of the function is the same as referenced in the activity.xml file
    //context is this view, and Length_Long is a duration of 3.5 seconds as a constant
    public void toastMe(View view) {
        Toast.makeText(this, "Hello World!", Toast.LENGTH_LONG).show();



    }

}