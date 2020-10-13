package se.cloudworks.labb4;

public class Movie {

    String _title;
    String _imdbid;
    String _year;

    public Movie(String title, String imdbid, String year){
        _title = title;
        _imdbid = imdbid;
        _year = year;
    }

    public String get_title(){
        return _title;
    }

    public String get_imdbid(){
        return _imdbid;
    }

    public String get_year(){
        return _year;
    }

    public String toString(){

        return _title + " " + _year;
    }

}
