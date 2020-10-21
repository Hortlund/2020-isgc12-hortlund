package se.cloudworks.labb4;

public class Movie {

    //not much to say here, simple blueprint for movie object, with sets the values in the constructor and have get methods, and overides the standard toString
    //method when displaying them in the listview

    private String title;
    private String imdbid;
    private String year;

    public Movie(String title, String imdbid, String year){
        this.title = title;
        this.imdbid = imdbid;
        this.year = year;
    }

    public String get_title(){
        return this.title;
    }

    public String get_imdbid(){
        return this.imdbid;
    }

    public String get_year(){
        return this.year;
    }

    public String toString(){

        return this.title + " " + this.year;
    }

}
