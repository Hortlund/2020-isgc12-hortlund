package se.cloudworks.labb4;

public class Movie {

    String _title;
    String _imdbid;

    public Movie(String title, String imdbid){
        _title = title;
        _imdbid = imdbid;
    }

    public String get_title(){
        return _title;
    }

    public String toString(){

        return _title + _imdbid;
    }

}
