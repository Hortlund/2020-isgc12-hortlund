package se.cloudworks.labb4;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

public class ApiCall extends AsyncTask<String, Void, ArrayList>{

    private Context _ctx;
    private ArrayList<String> movieSearch = new ArrayList<>();

    public ApiCall(Context ctx){
        _ctx = ctx;
    }
    @Override
    protected ArrayList<String> doInBackground(String... movies) {

        RequestQueue queue;
        // Instantiate the cache
        Cache cache = new DiskBasedCache(_ctx.getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        queue = new RequestQueue(cache, network);

        // Start the queue
        queue.start();



        movieSearch = new ArrayList<>();
        return movieSearch;
    }

    @Override
    protected void onPostExecute(ArrayList movies) {
        super.onPostExecute(movies);
        Intent intent = new Intent("se.cloudworks.MainActivity");
    }
}
