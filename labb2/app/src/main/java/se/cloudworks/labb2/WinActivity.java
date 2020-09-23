package se.cloudworks.labb2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinActivity extends AppCompatActivity {

    private Intent intent;
    private TextView word,guesses;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win);
        setUp();
    }

    public void onResume() {
        super.onResume();
        setUp();
    }

    public void setUp(){
        intent = getIntent();
        word=findViewById(R.id.textView3);
        guesses=findViewById(R.id.textView4);
        word.setText("The word was: ");
        guesses.setText("Your guesses: ");
        word.append(intent.getStringExtra("word"));
        guesses.append(intent.getStringExtra("guesses"));

    }

    public void playAgain(View view){
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
