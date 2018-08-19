package krunal.com.example.acer.wordsapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by acer on 08-02-2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASEVERSION = 1;

    public DbHelper(Context context){
        super(context,WordContract.DATABASE_NAME,null,DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + WordContract.TABLE_NAME + "("
                + WordContract.ID + " INTEGER PRIMARY KEY, "
                + WordContract.WORDS + " TEXT " + ")";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
