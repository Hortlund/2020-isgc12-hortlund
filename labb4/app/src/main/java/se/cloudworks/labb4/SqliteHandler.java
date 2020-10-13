package se.cloudworks.labb4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SqliteHandler implements  Storage {

    private int pos = 0;
    private final String TAG ="Skier";
    private SQLiteDatabase database;
    private String[] allColumns = { DBHelper.FIELD1, DBHelper.FIELD2,
            DBHelper.FIELD3 };
    private List<Movie> all = new ArrayList<>();

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
        Log.d(TAG, "writing in DB: " + insertId);
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
        // Make sure to close the cursor
        cursor.close();
        // all.add(new
        // Skier().setPnr("121212-1212").setName("Pallen").setClub("?sarna"));
        // all.add(new
        // Skier().setPnr("232323-2323").setName("Knas").setClub("FBK"));
    }

    private Movie cursorToMovie(Cursor cursor) {
        Movie tmp = new Movie(cursor.getString(0),cursor.getString(1),cursor.getString(2));
        return tmp;
    }


    @Override
    public void open() {
        pos = 0;
    }

    @Override
    public void close() {
        Log.d(TAG,"Closing and writing DB");
        Iterator<Movie> i = all.iterator();
        while (i.hasNext()) {
            addMovieToDB(i.next());
        }
    }

    @Override
    public void add(Movie m) {
        all.add(m);
        pos = all.size() - 1;
        System.out.println("adding " + all.size());
    }

    public Movie findMovie(String imdbid) {
        Iterator<Movie> i = all.iterator();
        Movie tmp = null;
        while (i.hasNext()) {
            tmp = i.next();
            if (tmp.get_imdbid().equals(imdbid)) {
                System.out.println("found match");
                return tmp;
            }
        }
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Movie delete(String imdbid) {
        Movie t = findMovie(imdbid);
        all.remove(t);
        System.out.println("removing " + all.size());
        pos = 0;
        // TODO Auto-generated method stub
        if (all.size() > 0) {
            return all.get(pos);
        } else {
            return null;
        }
    }
}
