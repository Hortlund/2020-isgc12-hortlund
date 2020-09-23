package se.cloudworks.labb2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoseActivity extends AppCompatActivity {

    private Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lose);
    }

    public void playAgain(View view){
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
