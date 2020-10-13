package se.cloudworks.labb4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        //apiCall.execute(movieTitle);
        apiCall.doRequest(movieTitle);
        //Log.d("walla", "klar!!" + walla.toString());


    }

    public void onResume() {
        super.onResume();
    }

    @Override
    public ArrayList<Movie> VolleyResponse(ArrayList<Movie> movieSearch) {
        for(int i = 0; i < movieSearch.size(); i++){
            Log.d("walla", "klar!!" + movieSearch.get(i).get_title() + " " + movieSearch.get(i).get_imdbid());
        }
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieSearch);
        //Get reference of listview
        ListView lw = findViewById(R.id.listview);
        //connect the adapter and list view
        lw.setAdapter(new AdapterClass(movieSearch,this));

        return null;
    }
}