package se.cloudworks.labb4;

import java.util.ArrayList;

public interface Callback {
    //Functions that need to be present to be able to receive response from volley.
    void VolleyResponseMovie(ArrayList<Movie> movieSearch);
    void VolleyResponseActor(ArrayList<Actor> movieSearch);

}
