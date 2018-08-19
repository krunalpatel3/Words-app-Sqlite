package krunal.com.example.acer.wordsapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import krunal.com.example.acer.wordsapp.Database.DbHelper;
import krunal.com.example.acer.wordsapp.Database.WordContract;

/**
 * Created by acer on 10-02-2018.
 */

public class DatabaseOpration {

    private static final String TAB = DatabaseOpration.class.getSimpleName();

    private static DbHelper mDbhelper;
    private static AppExecutor mAppExecutor;
    private List<Word> wordslist;

    static long rowsDeleted;

    DatabaseOpration(Context context) {
        this.mDbhelper = new DbHelper(context);
        this.mAppExecutor = new AppExecutor();
    }

    public void insert(String word) {
        mAppExecutor.diskIO().execute(() -> {
            SQLiteDatabase db = mDbhelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(WordContract.WORDS, word);

            long count = db.insert(WordContract.TABLE_NAME, null, contentValues);
            Log.i(TAB, String.valueOf(count));
            db.close();
        });

    }

    public List<Word> getAllList() {

        wordslist = new ArrayList<>();
        String[] projection = {WordContract.ID, WordContract.WORDS};
        SQLiteDatabase db = mDbhelper.getReadableDatabase();

        Cursor cursor = db.query(WordContract.TABLE_NAME, projection, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int wordcolumnindex = cursor.getColumnIndex(WordContract.WORDS);
            int wordcolumnid = cursor.getColumnIndex(WordContract.ID);
            do {
                int id = cursor.getInt(wordcolumnid);
                String finalword = cursor.getString(wordcolumnindex);
                wordslist.add(new Word(id, finalword));
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            Log.i(TAB, String.valueOf(wordslist));
        }

        return wordslist;
    }

    public static long delete(int id) {

        mAppExecutor.diskIO().execute(() ->{
            SQLiteDatabase db = mDbhelper.getWritableDatabase();

            String selection = WordContract.ID + "=?";

            String[] selectionArgs = {String.valueOf(id)};
            rowsDeleted = db.delete(WordContract.TABLE_NAME, selection, selectionArgs);
            db.close();
        });


        return rowsDeleted;
    }

    public static Word Query(int id){
        SQLiteDatabase db = mDbhelper.getReadableDatabase();

        Word word = new Word();

        String[] projection = {WordContract.ID, WordContract.WORDS};
        String selection = WordContract.ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(WordContract.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int wordcolumnindex = cursor.getColumnIndex(WordContract.WORDS);
            int wordcolumnid = cursor.getColumnIndex(WordContract.ID);
            do {
                int getid = cursor.getInt(wordcolumnid);
                String getword = cursor.getString(wordcolumnindex);
                word = new Word(getid,getword);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();

        }

        return word;

    }

    public static int update(int id, String word) {
        int mNumberOfRowsUpdated = -1;
        try {
            SQLiteDatabase db = mDbhelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(WordContract.WORDS, word);

            String selection = WordContract.ID + "=?";
            String[] selectionArgs = {String.valueOf(id)};

            mNumberOfRowsUpdated = db.update(WordContract.TABLE_NAME, //table to change
                    values, // new values to insert
                    selection, // selection criteria for row (in this case, the _id column)
                    selectionArgs); //selection args; the actual value of the id

        } catch (Exception e) {
            //Log.d (TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
        return mNumberOfRowsUpdated;
    }

}
