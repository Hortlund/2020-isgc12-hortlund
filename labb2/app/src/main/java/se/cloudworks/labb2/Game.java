package se.cloudworks.labb2;


import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class Game {

    private TextView guesses, wordTextfield;
    private EditText charGuess;
    private ImageView image;
    private String guessedChars, guessString, theWord;
    private int guess;
    char guessChar;
    private Intent intent;
    StringBuilder theWordString = new StringBuilder();
    FileRead read = new FileRead();

    public void setup(MainActivity view){
        charGuess = view.findViewById(R.id.charGuess);
        guesses = view.findViewById(R.id.guesses);
        wordTextfield = view.findViewById(R.id.textView);
        image = view.findViewById(R.id.imageView);
        image.setImageResource(R.drawable.start);
        guessedChars = "";
        guessString = "";
        theWordString.setLength(0);
        guess = 0;
        guesses.setText("Guesses: ");

        try {
            theWord = read.getWord(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("rättning", "Ord: " + theWord);
        for(int i = 0; i < theWord.length(); i++){
            theWordString.append("_");
        }
        wordTextfield.setText(theWordString);
    }

    public void checkGuess(MainActivity view){
        if(!charGuess.getText().toString().matches("")){
            guessString = charGuess.getText().toString().toLowerCase();
            if(!guessedChars.contains(guessString)){
                guessChar = guessString.charAt(0);
                if(!theWord.contains(guessString)){
                    guessedChars += guessChar;
                    guesses.setText("Guesses: " + guessedChars);
                    guess++;
                }else{
                    for (int i = 0; i < theWord.length(); i++) {
                        if (theWord.charAt(i) == guessChar) {
                            theWordString.setCharAt(i, guessChar);
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

            wordTextfield.setText(theWordString);


    }
        if(theWord.matches(theWordString.toString())){
            intent = new Intent(view,WinActivity.class);
            intent.putExtra("word", theWord);
            intent.putExtra("guesses", guessedChars);
            view.startActivity(intent);
        }
        if(guessedChars.length()>=7){
            intent = new Intent(view,LoseActivity.class);
            intent.putExtra("word", theWord);
            intent.putExtra("guesses", guessedChars);
            view.startActivity(intent);
        }
        charGuess.setText("");

    }
}
