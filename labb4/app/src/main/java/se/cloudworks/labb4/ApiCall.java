package se.cloudworks.labb4;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.telecom.Call;
import android.util.Log;
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
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ApiCall {
    private Context _ctx;
    private ArrayList<Movie> movieSearch;
    private String url;
    private int flag;

    public ApiCall(Context ctx) {
        _ctx = ctx;
    }

    public void doRequest(String search, int flag) {
        try {
            String safeSearch = URLEncoder.encode(search, "utf-8");

            if (flag == 1) {
                url = "https://www.myapimovies.com/api/v1/movie/search?title=" + safeSearch + "&token=YOUR_API_KEY";
            } else if (flag == 2) {
                url = "https://www.myapimovies.com/api/v1/movie/" + safeSearch + "/similar-movies?&token=YOUR_API_KEY";
            } else if (flag == 3) {
                url = "https://www.myapimovies.com/api/v1/movie/" + safeSearch + "/actors?&token=YOUR_API_KEY";
            }

            Log.d("walla", url);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            String title, imdbid, year;
                            movieSearch = new ArrayList<>();
                            //Log.d("walla", "respone: " + response.toString());
                            try {
                                JSONArray data = response.getJSONArray("data");
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject wObject = data.getJSONObject(i);
                                    if (wObject.has("title")) {
                                        title = wObject.get("title").toString();
                                    } else {
                                        title = "No title";
                                    }

                                    if (wObject.has("imdbId")) {
                                        imdbid = wObject.get("imdbId").toString();
                                    } else {
                                        imdbid = null;
                                    }

                                    if (wObject.has("year")) {
                                        year = wObject.get("year").toString();
                                    } else {
                                        year = "unknown";
                                    }

                                    //String title = response.get("title").toString();


                                    //Log.d("walla", title);
                                    //Log.d("walla", imdbid);
                                    Movie movie = new Movie(title, imdbid, year);
                                    movieSearch.add(movie);
                                    //Log.d("walla", "lenght" + movieSearch.size());
                                }
                                ((Callback) _ctx).VolleyResponse(movieSearch);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("walla", error.toString());
                        }
                    });
            //Log.d("walla", "post execute" + movieSearch.size());
            VolleySingleton.getInstance(_ctx).addToRequestQueue(jsonObjectRequest);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
