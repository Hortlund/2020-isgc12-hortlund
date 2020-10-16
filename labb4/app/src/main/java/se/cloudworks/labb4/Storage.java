package se.cloudworks.labb4;

import java.util.ArrayList;

public interface Storage {

    void add(Movie m);

    void delete(String imdbid);

    ArrayList<Movie> getAll();

}
