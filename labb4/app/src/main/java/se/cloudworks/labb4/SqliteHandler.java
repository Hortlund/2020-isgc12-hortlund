package se.cloudworks.labb4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

//Some code came from https://github.com/karlstad-business-school/ISGC12-code-examples as part of lectures and/or repositories with example code
//This have been modified but som original elements may still be there.

public class SqliteHandler implements  Storage {

    //creates objects and variables
    private SQLiteDatabase database;
    private String[] allColumns = { DBHelper.FIELD1, DBHelper.FIELD2,
            DBHelper.FIELD3 };
    private ArrayList<Movie> all = new ArrayList<>();

    //in the constructor we create dbhelper get a database and gets all movies currently in the database
    public SqliteHandler(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        getAllMovies();

    }
    // add the movie to db, get the different attributes and puts them in respective fields. the runs the insert function to write it to db
    private void addMovieToDB(Movie m) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.FIELD1, m.get_title());
        values.put(DBHelper.FIELD2, m.get_imdbid());
        values.put(DBHelper.FIELD3, m.get_year());
        database.insert(DBHelper.TABLE_NAME, null, values);
    }

    //Gets all movies from the db
    private void getAllMovies() {
        //creates a cursor to enable read and write to the result form the db query
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns,
                null, null, null, null, null);

        //moves to the first result from the query
        cursor.moveToFirst();
        //keeps going until last result, and gets values from each field form each move and adds that to an arraylist
        while (!cursor.isAfterLast()) {
            Movie m = cursorToMovie(cursor);
            all.add(m);
            cursor.moveToNext();
        }
        //releases all resources from cursor
        cursor.close();
    }
    //uses by getAllMovies, gets the values from each field, creates and movie object and returns it.
    private Movie cursorToMovie(Cursor cursor) {
        Movie tmp = new Movie(cursor.getString(0),cursor.getString(1),cursor.getString(2));
        return tmp;
    }

    //public function to get all movies from arraylist
    public ArrayList<Movie> getAll(){
        return all;
    }

    @Override
    //public function to add movie to db
    public void add(Movie m) {
        addMovieToDB(m);
    }

    @Override
    //closes db connection
    public void close() {
        database.close();
    }

    @Override
    //Gets the imdbid that unique to every movie and deletes that where it matches the where clause, if 2 of the same movies is saved, both will be deleted
    public void delete(String imdbid) {
        database.delete(DBHelper.TABLE_NAME,DBHelper.FIELD2 + "=\""+ imdbid + "\";", null);
    }


}
