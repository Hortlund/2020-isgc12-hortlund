package se.cloudworks.labb4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    static final String TABLE_NAME = "Movie";
    private static final String DATABASE_NAME = "MovieDB";
    static final String FIELD1 = "Title";
    static final String FIELD2 = "Imdbid";
    static final String FIELD3 = "Year";
    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
            + " (" + FIELD1 + " TEXT, " + FIELD2 + " TEXT, " + FIELD3
            + " TEXT);";

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        onCreate(sqLiteDatabase);
    }
}
