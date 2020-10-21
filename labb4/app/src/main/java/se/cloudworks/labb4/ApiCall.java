package se.cloudworks.labb4;

import android.content.Context;
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

//Some code came from https://github.com/karlstad-business-school/ISGC12-code-examples as part of lectures and/or repositories with example code
//Also from https://stackoverflow.com/questions/32947363/volley-request-callback-passing-the-wrong-callback-response for the callback idea
//This have been modified but som original elements may still be there.

public class ApiCall {

    //creates variables and objects needed
    private Context context;
    private ArrayList<Movie> movieSearch;
    private ArrayList<Actor> actorsSearch;
    private String url;
    private static final String key = "YOUR_API_KEY";

    //constructor thats sets the context
    public ApiCall(Context context) {
        this.context = context;
    }

    //gets called internally from the class with the url and another flag
    private void doCall(int flag, String url){
        //if we search for actors
        if(flag == 3){
            //create a new jsonObjectrequest object, with get method, the url and a response listener
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        //this code actually runs after a volley response has med made, so first we add the request to queue and get response, then this code runs
                        public void onResponse(JSONObject response) {
                            //Strings to hold the values
                            String character, name;
                            //creates a new list for every search we do
                            actorsSearch = new ArrayList<>();
                            try {
                                //From the response, go into the array data
                                JSONArray data = response.getJSONArray("data");
                                //for every item in data, get each object and check if its the name and value we want to have.
                                for (int i = 0; i < data.length(); i++) {
                                    //create object from index i
                                    JSONObject o = data.getJSONObject(i);
                                    //check if it has name character, set the string value to character or default value
                                    if (o.has("character")) {
                                        character = o.get("character").toString();
                                    } else {
                                        character = "No character";
                                    }
                                    //since this is a nested object, we need to create a new object from the name value, then check the same as above
                                    JSONObject o2 = o.getJSONObject("name");
                                    if(o2.has("name")){
                                        name = o2.get("name").toString();
                                    }else{
                                        name = "unkown";
                                    }
                                    //create a new actor object and add that to arraylist for actors
                                    Actor actor = new Actor(character,name);
                                    actorsSearch.add(actor);
                                }
                                //call the function from mainactivity casting it as a Callback(interface)
                                ((Callback) context).VolleyResponseActor(actorsSearch);

                            //catch json errors
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //if some error happens, such as empty response from api, just toast it and let them try another, happen quite often
                            Toast.makeText(context,"The movie is probably empty on Imdb, try another", Toast.LENGTH_LONG).show();
                        }
                    });
            //Call our volley, gets or instantiate the class and add the jsonrequest to queue
            VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        }else{
            //if we search for movie or similar movie, we do this, basically the same as above, but searches for different values
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            String title, imdbid, year;
                            movieSearch = new ArrayList<>();
                            try {
                                JSONArray data = response.getJSONArray("data");
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject o = data.getJSONObject(i);
                                    if (o.has("title")) {
                                        title = o.get("title").toString();
                                    } else {
                                        title = "No title";
                                    }

                                    if (o.has("imdbId")) {
                                        imdbid = o.get("imdbId").toString();
                                    } else {
                                        imdbid = null;
                                    }
                                    //If we search for similar movies, year is not shown because of the API.
                                    if (o.has("year")) {
                                        year = o.get("year").toString();
                                    } else {
                                        year = "";
                                    }
                                    Movie movie = new Movie(title, imdbid, year);
                                    movieSearch.add(movie);
                                }
                                ((Callback) context).VolleyResponseMovie(movieSearch);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context,"The title didnt exist, please try another", Toast.LENGTH_LONG).show();
                        }
                    });
            VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        }

    }

    //The function that runs when we search for a movie, since we can search for movie, similar and actor, i split up the code with if statments, that generates
    //a different url depending on what flag was passed.
    public void doRequest(String search, int flag) {
        try {
            //endoces the search string to fit in a url even with spaces and åäö
            String safeSearch = URLEncoder.encode(search, "utf-8");
            //create the url and call doCall function with it
            if (flag == 1) {
                url = "https://www.myapimovies.com/api/v1/movie/search?title=" + safeSearch + "&token=" + key;
                doCall(flag, url);
            } else if (flag == 2) {
                url = "https://www.myapimovies.com/api/v1/movie/" + safeSearch + "/similar-movies?&token=" + key;
                doCall(flag, url);
            } else if (flag == 3) {
                url = "https://www.myapimovies.com/api/v1/movie/" + safeSearch + "/actors?&token=" + key;
                doCall(flag, url);

            }
        //catch errors in the encoding process of the url
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
