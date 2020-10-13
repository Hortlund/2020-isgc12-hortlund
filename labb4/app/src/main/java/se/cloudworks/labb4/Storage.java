package se.cloudworks.labb4;

public interface Storage {

    void open();

    void close();

    void add(Movie m);

    Movie findMovie(String imdbid);

    Movie delete(String imdbid);

}
