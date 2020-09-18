package se.cloudworks.labb2;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextView word, guesses, we;
    private EditText wordGuess;
    private ImageView image;
    private String guessedChars, guessString;
    private String chars, charss;
    private int guess;
    char guessChar;
    private List words = new ArrayList<String>();
    private int numberOfGuesses  = 0;
    StringBuilder myName = new StringBuilder();
    FileRead read = new FileRead();
    Game game = new Game();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

    }

    public void setup(){
        word = findViewById(R.id.word);
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
        Log.d("walla", "Ord:" + chars);
        for(int i = 0; i < chars.length(); i++){
            myName.append("_");
        }
        we.setText(myName);
    }

    /*

                if(game.checkLetter(this, guessChar, chars) == 0){
                    guessedChars += guessChar;
                    guesses.setText("Guesses: " + guessedChars);
                    image.setImageResource(R.drawable.guess7);
                }else{


                }
     */

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
            Toast.makeText(this, "Du Vann!!", Toast.LENGTH_LONG).show();
            setup();
        }
        if(guessedChars.length()>=7){
            Toast.makeText(this, "Du förlora!", Toast.LENGTH_LONG).show();
            setup();
        }
        wordGuess.setText("");


    }
}