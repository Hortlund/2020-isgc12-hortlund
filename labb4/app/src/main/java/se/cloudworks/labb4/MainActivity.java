package se.cloudworks.labb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

//Implements functions form callback interface to receive response from volley
public class MainActivity extends AppCompatActivity implements Callback {

    // holds the search string and storage class object
    private String movieTitle;
    private Storage db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new SqliteHandler(this);

    }


    public void onClick(View view){
        //gets edittextfield from view
        EditText search = findViewById(R.id.movieSearch);
        // Creates a new apicall object and sends this view as context
        ApiCall apiCall = new ApiCall(this);
        //Stringify
        movieTitle = search.getText().toString();
        //Call dorequest function with search string and flag one to represent search for movies with title.
        apiCall.doRequest(movieTitle,1);
    }

    public void showSavedMovies(View view){
        //New intent object with action to saved movies activity
        Intent intent = new Intent("se.cloudworks.ShowSavedMoviesActivity");
        //goes to that activity
        this.startActivity(intent);
    }

    //on resume we just resume the activity, not much here to do, maybe needs to create new sqlitehandler, dont know since it never got out of stack
    public void onResume() {
        super.onResume();

    }

    //on pause or destory we close the db connection, we want to hold it open as long as we need, since calling open and close on db is resource heavy
    public void onDestroy() {
        super.onDestroy();
        db.close();

    }

    public void onPause() {
        super.onPause();
        db.close();
    }

    @Override
    //function from interface callback, runs if we search for movies
    public void VolleyResponseMovie(ArrayList<Movie> movieSearch) {
        //gets listview from layout
        ListView lw = findViewById(R.id.listview);
        //sets custom listview adapter and sends arraylist from apicall and a flag that represent different ways the adapter class will represent the data, also sends
        //the storage object with it
        lw.setAdapter(new AdapterClass(movieSearch,null,this, 1, db));
    }

    @Override
    //function from interface callback, runs if we search for actors
    public void VolleyResponseActor(ArrayList<Actor> movieSearch) {
        //gets listview from layout
        ListView lw = findViewById(R.id.listview);
        //sets custom listview adapter and sends arraylist from apicall and a flag that represent different ways the adapter class will represent the data, also sends
        //the storage object with it
        lw.setAdapter(new AdapterClass(null,movieSearch,this, 3, db));
    }


}