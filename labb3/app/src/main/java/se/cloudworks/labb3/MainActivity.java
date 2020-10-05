package se.cloudworks.labb3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Declaring variable & data structures
    private String artistName;
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        //get text from search field.
        EditText artistSearch = findViewById(R.id.artistSearch);
        //New instance of apicall class and create it with application context
        ApiCall apiCall = new ApiCall(getApplicationContext());
        //Stringify the text
        artistName = artistSearch.getText().toString();
        //Execute the asynchronous task
        apiCall.execute(artistName);
    }

    public void onResume() {
        //List view and array adapter code was from lectures and course github https://github.com/karlstad-business-school/ISGC12-code-examples but have been modifed
        super.onResume();
        //If we return with a list of artist go go forth
        if (getIntent().getStringArrayListExtra("list") != null) {
            //Get text field reference
            TextView searchedArtist = findViewById(R.id.textView2);
            //Set the text to the artist we searched for.
            searchedArtist.setText("Artist similar to: " + getIntent().getStringExtra("name") );
            //A new array adapter so we can use the array list as a data source and display it in the UI, simple_list_item_1 is a android native reference for a list ui element.
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getIntent().getStringArrayListExtra("list"));
            //Get reference of listview
            ListView lw = findViewById(R.id.listview1);
            //connect the adapter and list view
            lw.setAdapter(adapter);
        }


    }

}