package dlh.fpt.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dlh.fpt.entities.Word;

/**
 * Created by Daniel on 8/1/2015.
 */
public class DatabaseWordHandler extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "DLH";
    private static int DATABASE_VERSION = 1;
    public static String TABLE_WORDS = "WORDS";
    public static String WORD_ID = "wordID";
    public static String WORD_WORD = "word";
    public static String WORD_USERID = "userID";
    Context context;

    public DatabaseWordHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "create table " + TABLE_WORDS + " ( " +
                WORD_ID +" Integer primary key, " +
                WORD_WORD + " text, "+ WORD_USERID + " Integer " + ")";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists " + TABLE_WORDS;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean addWord(Word word) {
        boolean check = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(WORD_ID, word.getWordID());
        value.put(WORD_WORD, word.getWord());
        value.put(WORD_USERID, word.getUserID());
        if (db.insert(TABLE_WORDS, null, value) != -1) {
            check = true;
        }
        db.close();
        return check;
    }
}
