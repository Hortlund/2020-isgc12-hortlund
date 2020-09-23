package se.cloudworks.labb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView guesses, we;
    private EditText wordGuess;
    private ImageView image;
    private String guessedChars, guessString, chars;
    private int guess;
    char guessChar;
    private Intent intent;
    StringBuilder myName = new StringBuilder();
    FileRead read = new FileRead();
    Game game = new Game();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

    }
    public void onResume() {
        super.onResume();
        setup();
    }

    public void setup(){
        wordGuess = findViewById(R.id.wordGuess);
        guesses = findViewById(R.id.guesses);
        we = findViewById(R.id.textView);
        image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.start);
        guessedChars = "";
        guessString = "";
        myName.setLength(0);
        guess = 0;
        guesses.setText("Guesses: ");

        try {
            chars = read.getWord(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("rättning", "Ord: " + chars);
        for(int i = 0; i < chars.length(); i++){
            myName.append("_");
        }
        we.setText(myName);
    }

    public void guess(View view){

        if(!wordGuess.getText().toString().matches("")){
            guessString = wordGuess.getText().toString();
            if(!guessedChars.contains(guessString)){
                 guessChar = guessString.charAt(0);
                 if(!chars.contains(guessString)){
                     guessedChars += guessChar;
                     guesses.setText("Guesses: " + guessedChars);
                     guess++;
                 }else{
                     for (int i = 0; i < chars.length(); i++) {
                         if (chars.charAt(i) == guessChar) {
                             myName.setCharAt(i, guessChar);
                         }
                     }
                 }

            }

                switch(guess){
                    case 1:
                        image.setImageResource(R.drawable.guess1);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.guess2);
                        break;
                    case 3:
                        image.setImageResource(R.drawable.guess3);
                        break;
                    case 4:
                        image.setImageResource(R.drawable.guess4);
                        break;
                    case 5:
                        image.setImageResource(R.drawable.guess5);
                        break;
                    case 6:
                        image.setImageResource(R.drawable.guess6);
                        break;

                }

            we.setText(myName);

        }
        if(chars.matches(myName.toString())){
            intent = new Intent(this,WinActivity.class);
            intent.putExtra("word", chars);
            intent.putExtra("guesses", guessedChars);
            startActivity(intent);
        }
        if(guessedChars.length()>=7){
            intent = new Intent(this,LoseActivity.class);
            intent.putExtra("word", chars);
            intent.putExtra("guesses", guessedChars);
            startActivity(intent);
        }
        wordGuess.setText("");


    }
}