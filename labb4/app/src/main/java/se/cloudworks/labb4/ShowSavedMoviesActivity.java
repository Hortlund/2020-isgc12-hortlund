package se.cloudworks.labb4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowSavedMoviesActivity extends AppCompatActivity {

    private Storage movies;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_movies);
        //Creates new object of sqlitehandler with Storage interface
        movies = new SqliteHandler(this);
        //Get listview from layout
        ListView lw = findViewById(R.id.savedMovies);
        //Creates custom adapter and sends list received from sqlitehandler function along with flag 2
        lw.setAdapter(new AdapterClass(movies.getAll(),null,this, 2, movies));
    }

    // does the same as oncreate, as we do not now if the activity is popped out of stack
    public void onResume() {
        super.onResume();
        ListView lw = findViewById(R.id.savedMovies);
        AdapterClass adapter = new AdapterClass(movies.getAll(),null,this, 2, movies);
        lw.setAdapter(adapter);

    }
    public void onDestroy() {
        super.onDestroy();
        movies.close();

    }

    public void onPause() {
        super.onPause();
        movies.close();
    }

    // When user clicks the back to search button from the layout
    public void onClick(View view){
        Intent intent = new Intent("se.cloudworks.MainActivityLabb4");
        this.startActivity(intent);
    }

}
