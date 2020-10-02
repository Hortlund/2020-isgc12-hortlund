package se.cloudworks.labb3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String artistName;
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        EditText artistSearch = findViewById(R.id.artistSearch);
        ApiCall apiCall = new ApiCall(getApplicationContext());
        artistName = artistSearch.getText().toString();
        if(artistName.matches("")){

        }else{
            apiCall.execute(artistName.replace(" ", "%20"));
        }

    }

    public void onResume() {
        super.onResume();
        if (getIntent().getStringArrayListExtra("list") != null) {
            TextView searchedArtist = findViewById(R.id.textView2);
            searchedArtist.setText("Artist similar to: " + getIntent().getStringExtra("name") );
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getIntent().getStringArrayListExtra("list"));
            ListView lw = findViewById(R.id.listview1);
            lw.setAdapter(adapter);
        }


    }

}