package com.example.finalprojectcst2335;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoritesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    public FavoritesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FAVORITES_TABLE = "CREATE TABLE " +
                FavoritesContract.FavoritesEntry.TABLE_NAME + " (" +
                FavoritesContract.FavoritesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FavoritesContract.FavoritesEntry.COLUMN_HEADLINE + " TEXT NOT NULL, " +
                FavoritesContract.FavoritesEntry.COLUMN_URL + " TEXT NOT NULL" +
                ");";

        db.execSQL(SQL_CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This method is not used in this example
    }

    public long insertFavoriteArticle(String headline, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FavoritesContract.FavoritesEntry.COLUMN_HEADLINE, headline);
        values.put(FavoritesContract.FavoritesEntry.COLUMN_URL, url);
        return db.insert(FavoritesContract.FavoritesEntry.TABLE_NAME, null, values);
    }

    public int deleteFavoriteArticle(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = FavoritesContract.FavoritesEntry._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        return db.delete(FavoritesContract.FavoritesEntry.TABLE_NAME, selection, selectionArgs);
    }

    public int deleteAllFavoriteArticles() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(FavoritesContract.FavoritesEntry.TABLE_NAME, null, null);
    }

    public List<Article> getAllFavoriteArticles() {
        List<Article> favoritesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                FavoritesContract.FavoritesEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex(FavoritesContract.FavoritesEntry._ID));
                String headline = cursor.getString(cursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_HEADLINE));
                String url = cursor.getString(cursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_URL));
                Article article = new Article(id, headline, url);
                favoritesList.add(article);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return favoritesList;
    }
}
