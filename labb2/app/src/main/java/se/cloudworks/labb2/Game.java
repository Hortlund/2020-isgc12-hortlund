package se.cloudworks.labb2;


import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class Game {

    //Declaring widgets and other types and objects needed later.
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
        //Getting the different fields form the view
        charGuess = view.findViewById(R.id.charGuess);
        guesses = view.findViewById(R.id.guesses);
        wordTextfield = view.findViewById(R.id.textView);
        image = view.findViewById(R.id.imageView);
        image.setImageResource(R.drawable.start);
        //Setting strings and other text to default start value
        guessedChars = "";
        guessString = "";
        theWordString.setLength(0);
        guess = 0;
        guesses.setText("Guesses: ");

        //Try to get the word from file
        try {
            theWord = read.getWord(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log for getting the word
        Log.d("rättning", "Ord: " + theWord);
        //For the length of the word, print the equal amount of _
        for(int i = 0; i < theWord.length(); i++){
            theWordString.append("_");
        }
        //Set that text to textfield
        wordTextfield.setText(theWordString);
    }

    public void checkGuess(MainActivity view){
        //If the guess edittextfield is empty, then do nothing, otherwise go in to the statement.
        if(!charGuess.getText().toString().matches("")){
            //Get the text that was added from the edittextfield
            guessString = charGuess.getText().toString().toLowerCase();
            //If the charahcter/"string" was already entered then do nothing, otherwise go in to the statement.
            if(!guessedChars.contains(guessString)){
                //Turn the string to a char to work with the stringbuilder function charAt()
                guessChar = guessString.charAt(0);
                //if the guess is not in the word, then mark is as a wrong guess.
                if(!theWord.contains(guessString)){
                    //Add the guess to the other guesses string.
                    guessedChars += guessChar;
                    //Show the guesses
                    guesses.setText("Guesses: " + guessedChars);
                    //increment guess counter
                    guess++;
                }else{
                    //Otherwise if the guess is correct, for the length of the word, insert the char at the index of where tha chars match.
                    for (int i = 0; i < theWord.length(); i++) {
                        if (theWord.charAt(i) == guessChar) {
                            theWordString.setCharAt(i, guessChar);
                        }
                    }
                }

            }

            //Just a switch statement to show relevant image to the guess
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
            //Update the texfield after a guess
            wordTextfield.setText(theWordString);


    }
        //If the word matches whats in the textfield then you have won
        if(theWord.matches(theWordString.toString())){
            //intent to go to winning activity
            intent = new Intent(view,WinActivity.class);
            //Sends the word and the guesses to show in the end.
            intent.putExtra("word", theWord);
            intent.putExtra("guesses", guessedChars);
            view.startActivity(intent);
        }
        //Bummer. Same as above but for losers
        if(guessedChars.length()>=7){
            intent = new Intent(view,LoseActivity.class);
            intent.putExtra("word", theWord);
            intent.putExtra("guesses", guessedChars);
            view.startActivity(intent);
        }
        //clear the edittextfield for guess
        charGuess.setText("");

    }
}
