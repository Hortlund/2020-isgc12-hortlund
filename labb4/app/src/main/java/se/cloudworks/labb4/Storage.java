package se.cloudworks.labb4;

import java.util.ArrayList;

public interface Storage {

    void open();

    void close();

    void add(Movie m);

    Movie findMovie(String imdbid);

    Movie delete(String imdbid);

    ArrayList<Movie> getAll();

}
