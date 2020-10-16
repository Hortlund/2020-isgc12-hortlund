package se.cloudworks.labb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Callback {

    private String movieTitle;
    private ArrayList<Movie> walla;
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view){
        EditText search = findViewById(R.id.movieSearch);
        ApiCall apiCall = new ApiCall(this);
        movieTitle = search.getText().toString();
        apiCall.doRequest(movieTitle,1);
    }

    public void showSavedMovies(View view){
        Intent intent = new Intent("se.cloudworks.ShowSavedMoviesActivity");
        this.startActivity(intent);
    }

    public void onResume() {
        super.onResume();
    }

    @Override
    public ArrayList<Movie> VolleyResponseMovie(ArrayList<Movie> movieSearch) {
        ListView lw = findViewById(R.id.listview);
        lw.setAdapter(new AdapterClass(movieSearch,null,this, 1));
        return null;
    }

    @Override
    public ArrayList<Actor> VolleyResponseActor(ArrayList<Actor> movieSearch) {
        ListView lw = findViewById(R.id.listview);
        lw.setAdapter(new AdapterClass(null,movieSearch,this, 3));
        return null;
    }
}