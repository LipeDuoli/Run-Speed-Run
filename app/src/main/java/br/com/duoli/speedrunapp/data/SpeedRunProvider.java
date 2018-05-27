package br.com.duoli.speedrunapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class SpeedRunProvider extends ContentProvider {

    private static final String TAG = SpeedRunProvider.class.getSimpleName();

    public static final int CODE_GAME = 100;
    public static final int CODE_GAME_WITH_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private SpeedRunDbHelper mOpenHelper;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = SpeedRunContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, SpeedRunContract.PATH_GAME, CODE_GAME);
        matcher.addURI(authority, SpeedRunContract.PATH_GAME + "/#", CODE_GAME_WITH_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new SpeedRunDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        Cursor cursor;

        switch (sUriMatcher.match(uri)) {
            case CODE_GAME:
                cursor = db.query(SpeedRunContract.GameEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case CODE_GAME_WITH_ID:
                selection = SpeedRunContract.GameEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(SpeedRunContract.GameEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        long id;

        switch (sUriMatcher.match(uri)) {
            case CODE_GAME:
                id = db.insert(SpeedRunContract.GameEntry.TABLE_NAME, null, values);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (id == -1) {
            Log.e(TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        int rowsDeleted = 0;

        switch (sUriMatcher.match(uri)) {
            case CODE_GAME:
                rowsDeleted = db.delete(SpeedRunContract.GameEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case CODE_GAME_WITH_ID:
                selection = SpeedRunContract.GameEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = db.delete(SpeedRunContract.GameEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        int rowsUpdated = 0;

        switch (sUriMatcher.match(uri)) {
            case CODE_GAME:
                rowsUpdated = db.update(SpeedRunContract.GameEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CODE_GAME_WITH_ID:
                selection = SpeedRunContract.GameEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsUpdated = db.update(SpeedRunContract.GameEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case CODE_GAME:
                return SpeedRunContract.GameEntry.CONTENT_LIST_TYPE;
            case CODE_GAME_WITH_ID:
                return SpeedRunContract.GameEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }
}
