package se.cloudworks.labb4;

import java.util.ArrayList;

public interface Storage {

    //interface for storage, with the methods needed for this to work

    void add(Movie m);

    void close();

    void delete(String imdbid);

    ArrayList<Movie> getAll();

}
