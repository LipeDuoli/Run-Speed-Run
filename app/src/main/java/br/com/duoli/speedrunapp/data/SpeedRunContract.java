package br.com.duoli.speedrunapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class SpeedRunContract {

    public static final String CONTENT_AUTHORITY = "br.com.duoli.speedrunapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_GAME = "game";

    public static final class GameEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_GAME);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GAME;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GAME;

        public static final String TABLE_NAME = "games";

        public static final String COLUMN_GAME_ID = "game_id";
        public static final String COLUMN_GAME_NAME = "game_name";
        public static final String COLUMN_GAME_COVER_PATH = "game_cover";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_CATEGORY_NAME = "category_name";
        public static final String COLUMN_FIRST_PLACE_ID = "first_place_id";
        public static final String COLUMN_FIRST_PLACE_ASSET_PATH = "first_place_asset";

    }
}
