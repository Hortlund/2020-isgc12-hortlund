package se.cloudworks.labb4;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ApiCall {
    private Context _ctx;
    private ArrayList<Movie> movieSearch;
    private ArrayList<Actor> actorsSearch;
    private String url;

    public ApiCall(Context ctx) {
        _ctx = ctx;
    }

    private void doCall(int flag, String url){
        if(flag == 3){
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            String character, name;
                            actorsSearch = new ArrayList<>();
                            try {
                                JSONArray data = response.getJSONArray("data");
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject wObject = data.getJSONObject(i);
                                    if (wObject.has("character")) {
                                        character = wObject.get("character").toString();
                                    } else {
                                        character = "No title";
                                    }
                                    JSONObject wer = wObject.getJSONObject("name");
                                    if(wer.has("name")){
                                        name = wer.get("name").toString();
                                    }else{
                                        name = "unkown";
                                    }

                                    Actor actor = new Actor(character,name);
                                    Log.d("walla", actor.toString());
                                    actorsSearch.add(actor);
                                }
                                ((Callback) _ctx).VolleyResponseActor(actorsSearch);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(_ctx, error.toString() + "\nThe movie is probably empty on Imdb, try another", Toast.LENGTH_LONG).show();
                            Log.d("walla", error.toString());
                        }
                    });
            VolleySingleton.getInstance(_ctx).addToRequestQueue(jsonObjectRequest);
        }else{
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            String title, imdbid, year;
                            movieSearch = new ArrayList<>();
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
                                    Movie movie = new Movie(title, imdbid, year);
                                    movieSearch.add(movie);
                                }
                                ((Callback) _ctx).VolleyResponseMovie(movieSearch);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(_ctx, error.toString() + "\nThe title didnt exist, please try another", Toast.LENGTH_LONG).show();
                            Log.d("walla", error.toString());
                        }
                    });
            VolleySingleton.getInstance(_ctx).addToRequestQueue(jsonObjectRequest);
        }

    }

    public void doRequest(String search, int flag) {
        try {
            String safeSearch = URLEncoder.encode(search, "utf-8");

            if (flag == 1) {
                url = "https://www.myapimovies.com/api/v1/movie/search?title=" + safeSearch + "&token=YOUR_API_KEY";
                doCall(flag, url);
            } else if (flag == 2) {
                url = "https://www.myapimovies.com/api/v1/movie/" + safeSearch + "/similar-movies?&token=YOUR_API_KEY";
                doCall(flag, url);
            } else if (flag == 3) {
                url = "https://www.myapimovies.com/api/v1/movie/" + safeSearch + "/actors?&token=YOUR_API_KEY";
                doCall(flag, url);

            }

            Log.d("walla", url);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
