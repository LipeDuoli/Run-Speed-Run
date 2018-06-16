package br.com.duoli.speedrunapp.repository;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import br.com.duoli.speedrunapp.data.SpeedRunContract.GameEntry;
import br.com.duoli.speedrunapp.model.FavoriteGame;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import io.reactivex.Single;

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

        boolean hasValue = false;
        if (query != null) {
            hasValue = query.getCount() >= 1;
            query.close();
        }
        return hasValue;
    }

    @Override
    public Single<List<FavoriteGame>> loadFavoriteGames() {
        return Single.fromCallable(new Callable<List<FavoriteGame>>() {
            @Override
            public List<FavoriteGame> call() throws Exception {
                Cursor query = mContext.getContentResolver().query(GameEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        GameEntry.COLUMN_GAME_NAME);

                List<FavoriteGame> games = new ArrayList<>();
                if (query != null) {
                    while (query.moveToNext()) {
                        int id = query.getInt(query.getColumnIndex(GameEntry._ID));
                        String gameId = query.getString(query.getColumnIndex(GameEntry.COLUMN_GAME_ID));
                        String gameName = query.getString(query.getColumnIndex(GameEntry.COLUMN_GAME_NAME));
                        String gameCover = query.getString(query.getColumnIndex(GameEntry.COLUMN_GAME_COVER_PATH));
                        String categoryId = query.getString(query.getColumnIndex(GameEntry.COLUMN_CATEGORY_ID));
                        String categoryName = query.getString(query.getColumnIndex(GameEntry.COLUMN_CATEGORY_NAME));
                        String firstPlaceId = query.getString(query.getColumnIndex(GameEntry.COLUMN_FIRST_PLACE_ID));
                        String firstPlaceAsset = query.getString(query.getColumnIndex(GameEntry.COLUMN_FIRST_PLACE_ASSET_PATH));

                        FavoriteGame favoriteGame = new FavoriteGame(id,
                                gameId,
                                gameName,
                                gameCover,
                                categoryId,
                                categoryName,
                                firstPlaceId,
                                firstPlaceAsset);

                        games.add(favoriteGame);
                    }
                    query.close();
                }
                return games;
            }
        });
    }

    @Override
    public void updateFirstPlace(int id, String newFirstPlaceId) {
        Uri uri = ContentUris.withAppendedId(GameEntry.CONTENT_URI, id);

        ContentValues contentValues = new ContentValues();
        contentValues.put(GameEntry.COLUMN_FIRST_PLACE_ID, newFirstPlaceId);

        mContext.getContentResolver().update(uri, contentValues, null, null);
    }
}
