package se.cloudworks.labb4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowSavedMoviesActivity extends AppCompatActivity {

    private Storage db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_movies);
        //Creates new object of sqlitehandler with Storage interface
        db = new SqliteHandler(this);
        //Get listview from layout
        ListView lw = findViewById(R.id.savedMovies);
        //Creates custom adapter and sends list received from sqlitehandler function along with flag 2
        lw.setAdapter(new AdapterClass(db.getAll(),null,this, 2, db));
    }

    // does the same as oncreate, as we do not now if the activity is popped out of stack, dont know if new sqlitehandler need to be created.
    public void onResume() {
        super.onResume();
        ListView lw = findViewById(R.id.savedMovies);
        AdapterClass adapter = new AdapterClass(db.getAll(),null,this, 2, db);
        lw.setAdapter(adapter);

    }
    //pause and destory we close db connection
    public void onDestroy() {
        super.onDestroy();
        db.close();

    }

    public void onPause() {
        super.onPause();
        db.close();
    }

    // When user clicks the back to search button from the layout
    public void onClick(View view){
        Intent intent = new Intent("se.cloudworks.MainActivityLabb4");
        this.startActivity(intent);
    }

}
