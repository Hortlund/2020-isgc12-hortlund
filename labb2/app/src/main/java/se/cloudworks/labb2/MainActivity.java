package se.cloudworks.labb2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Game game = new Game();

    /*Game setup seems to run twice since the oncreate and onresume both runs, therefore the dubble log of the word.The stack doesnt clear.
    Also tried to seperate the game logic in an own class, this meant to pass the view from MainActivity to the Game class, I read that this
    might cause leaks and is not optimal, but tried it and it worked from what i can see fine.
    Please give feedback if this is totally the wrong way to go.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game.setup(this);

    }
    public void onResume(){
        super.onResume();
        game.setup(this);
    }

    public void guess(View view){
        game.checkGuess(this);
    }
}