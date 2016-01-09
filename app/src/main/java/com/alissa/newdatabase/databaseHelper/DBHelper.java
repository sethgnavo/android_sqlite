package com.alissa.newdatabase.databaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Seth-Pharès Gnavo on September 27, 2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "ParticipantDB";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL statement to create participant table
        String CREATE_PARTICIPANT_TABLE = "CREATE TABLE participant(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "nom TEXT," + "prenom TEXT)";

        //create participant table
        db.execSQL(CREATE_PARTICIPANT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older participant table if existed
        db.execSQL("DROP TABLE IF EXISTS participant");
        // create fresh participant table
        this.onCreate(db);

    }
}
