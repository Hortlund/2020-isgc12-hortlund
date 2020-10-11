package se.cloudworks.labb4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    private String movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view){
        EditText search = findViewById(R.id.movieSearch);
        ApiCall apiCall = new ApiCall(getApplicationContext());
        movieTitle = search.getText().toString();
        apiCall.execute(movieTitle);


    }

    public void onResume() {
        super.onResume();
    }
}