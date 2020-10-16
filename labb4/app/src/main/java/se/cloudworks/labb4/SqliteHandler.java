package se.cloudworks.labb4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

public class SqliteHandler implements  Storage {

    private SQLiteDatabase database;
    private String[] allColumns = { DBHelper.FIELD1, DBHelper.FIELD2,
            DBHelper.FIELD3 };
    private ArrayList<Movie> all = new ArrayList<>();

    public SqliteHandler(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        getAllMovies();

    }

    private void addMovieToDB(Movie m) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.FIELD1, m.get_title());
        values.put(DBHelper.FIELD2, m.get_imdbid());
        values.put(DBHelper.FIELD3, m.get_year());
        long insertId = database.insert(DBHelper.TABLE_NAME, null, values);
        Log.d("walla", "writing in DB: " + insertId);
        database.close();
    }

    private void getAllMovies() {
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Movie tmp = cursorToMovie(cursor);
            all.add(tmp);
            cursor.moveToNext();
        }
        cursor.close();
    }

    private Movie cursorToMovie(Cursor cursor) {
        Movie tmp = new Movie(cursor.getString(0),cursor.getString(1),cursor.getString(2));
        return tmp;
    }

    public ArrayList<Movie> getAll(){
        return all;
    }

    @Override
    public void add(Movie m) {
        addMovieToDB(m);
    }

    @Override
    public void delete(String imdbid) {
        database.delete(DBHelper.TABLE_NAME,DBHelper.FIELD2 + "=\""+ imdbid + "\";", null);
        database.close();
    }
}
