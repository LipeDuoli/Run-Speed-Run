package br.com.duoli.speedrunapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.duoli.speedrunapp.data.SpeedRunContract.GameEntry;

public class SpeedRunDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "speedrun.db";
    private static final int DATABASE_VERSION = 1;

    public SpeedRunDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_GAME_TABLE = "" +
                "CREATE TABLE " + GameEntry.TABLE_NAME + " (" +
                GameEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                GameEntry.COLUMN_GAME_ID + " TEXT NOT NULL, " +
                GameEntry.COLUMN_GAME_NAME + " TEXT NOT NULL, " +
                GameEntry.COLUMN_GAME_COVER_PATH + " TEXT NOT NULL, " +
                GameEntry.COLUMN_CATEGORY_ID + " TEXT NOT NULL, " +
                GameEntry.COLUMN_CATEGORY_NAME + " TEXT NOT NULL, " +
                GameEntry.COLUMN_FIRST_PLACE_ID + " TEXT NOT NULL, " +
                GameEntry.COLUMN_FIRST_PLACE_ASSET_PATH + " TEXT NOT NULL);";

        db.execSQL(CREATE_GAME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GameEntry.TABLE_NAME);
        onCreate(db);
    }
}
