package se.cloudworks.labb4;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.telecom.Call;
import android.util.Log;

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
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApiCall {
    private Context _ctx;
    private ArrayList<Movie> movieSearch;

    public ApiCall(Context ctx) {
        _ctx = ctx;
    }

    public void finish(ArrayList<Movie> movieSearch){
    }

    public void doRequest(String movieTitle) {

        String url = "https://www.myapimovies.com/api/v1/movie/search?title=" + movieTitle + "&token=YOUR_API_KEY";
        Log.d("walla", url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        movieSearch = new ArrayList<>();
                        Log.d("walla", "respone: " + response.toString());
                        try {
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject wObject = data.getJSONObject(i);
                                String title = wObject.get("title").toString();
                                //String title = response.get("title").toString();
                                String imdbid = wObject.get("imdbId").toString();
                                //Log.d("walla", title);
                                //Log.d("walla", imdbid);
                                Movie movie = new Movie(title, imdbid);
                                movieSearch.add(movie);
                                //Log.d("walla", "lenght" + movieSearch.size());
                            }
                            ((Callback)_ctx).VolleyResponse(movieSearch);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Eservice", error.toString());

                    }
                });
        //Log.d("walla", "post execute" + movieSearch.size());
        VolleySingleton.getInstance(_ctx).addToRequestQueue(jsonObjectRequest);
    }
}
