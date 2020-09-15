package se.cloudworks.labb2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView word;
    private EditText wordGuess;
    private String chars;
    private
    FileRead read = new FileRead();
    Game game = new Game();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        word = findViewById(R.id.word);
        wordGuess = findViewById(R.id.wordGuess);
        try {
            chars = read.getWord(this);
            Log.d("walla", "Ord:" + chars);
            for(int i = 0; i < chars.length(); i++){
                word.append("_ ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void guess(View view){

        String guessString = wordGuess.getText().toString();
        char guessChar = guessString.charAt(0);
        game.checkLetter(guessChar, chars);

    }
}