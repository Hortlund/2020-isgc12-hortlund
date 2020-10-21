package se.cloudworks.labb4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Some code came from https://github.com/karlstad-business-school/ISGC12-code-examples as part of lectures and/or repositories with example code
//This have been modified but som original elements may still be there.

public class DBHelper extends SQLiteOpenHelper {

    //sets some values for the db, such as version, name, tables and fields, also the whole create command
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
    //runs when we need to create a db
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    //creates a new db if version number changes for example
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
