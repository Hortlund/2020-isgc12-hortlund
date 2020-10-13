package se.cloudworks.labb4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowSavedMoviesActivity extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_movies);
        Storage movies = new SqliteHandler(this);
        ListView lw = findViewById(R.id.savedMovies);
        Log.d("walla", "lenght" + movies.getAll().size());
        //connect the adapter and list view
        lw.setAdapter(new AdapterClass(movies.getAll(),this, 2));
    }

    public void onResume() {
        super.onResume();
        Storage movies = new SqliteHandler(this);
        ListView lw = findViewById(R.id.savedMovies);
        Log.d("walla", "lenght" + movies.getAll().size());
        //connect the adapter and list view
        lw.setAdapter(new AdapterClass(movies.getAll(),this, 2));
    }

    public void onClick(View view){
        Intent intent = new Intent("se.cloudworks.MainActivityLabb4");
        this.startActivity(intent);
    }

}
