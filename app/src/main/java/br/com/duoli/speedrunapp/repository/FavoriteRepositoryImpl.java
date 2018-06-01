package br.com.duoli.speedrunapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.com.duoli.speedrunapp.data.SpeedRunContract.GameEntry;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;

public class FavoriteRepositoryImpl implements FavoriteRepository {

    private Context mContext;

    public FavoriteRepositoryImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean favoriteLeaderboard(Leaderboard leaderboard) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GameEntry.COLUMN_GAME_ID, leaderboard.getGame().getId());
        contentValues.put(GameEntry.COLUMN_GAME_NAME, leaderboard.getGame().getNames().getInternational());
        contentValues.put(GameEntry.COLUMN_GAME_COVER_PATH, leaderboard.getGame().getAssets().getCoverLarge().getUri());
        contentValues.put(GameEntry.COLUMN_CATEGORY_ID, leaderboard.getCategory().getId());
        contentValues.put(GameEntry.COLUMN_CATEGORY_NAME, leaderboard.getCategory().getName());
        contentValues.put(GameEntry.COLUMN_FIRST_PLACE_ID, leaderboard.getRuns().get(0).getRun().getId());
        contentValues.put(GameEntry.COLUMN_FIRST_PLACE_ASSET_PATH, leaderboard.getGame().getAssets().getTrophyFirst().getUri());

        Uri insert = mContext.getContentResolver().insert(GameEntry.CONTENT_URI, contentValues);

        return insert != null;
    }

    @Override
    public int removeFavoriteLeaderboard(String gameId, String categoryId) {
        String selection = GameEntry.COLUMN_GAME_ID + " = ? AND " + GameEntry.COLUMN_CATEGORY_ID + " = ?";
        String[] selectionArgs = {gameId, categoryId};

        int count = mContext.getContentResolver().delete(GameEntry.CONTENT_URI, selection, selectionArgs);
        return count;

    }

    @Override
    public boolean isfavorited(String gameId, String categoryId) {
        String selection = GameEntry.COLUMN_GAME_ID + " = ? AND " + GameEntry.COLUMN_CATEGORY_ID + " = ?";
        String[] selectionArgs = {gameId, categoryId};

        Cursor query = mContext.getContentResolver().query(GameEntry.CONTENT_URI,
                null,
                selection,
                selectionArgs,
                null);

        if (query != null) {
            boolean hasValue = query.getCount() >= 1;
            query.close();
            return hasValue;
        }
        return false;
    }
}
