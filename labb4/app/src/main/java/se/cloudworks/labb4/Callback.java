package se.cloudworks.labb4;

import java.util.ArrayList;

public interface Callback {

    ArrayList<Movie> VolleyResponseMovie(ArrayList<Movie> movieSearch);
    ArrayList<Actor> VolleyResponseActor(ArrayList<Actor> movieSearch);

}
