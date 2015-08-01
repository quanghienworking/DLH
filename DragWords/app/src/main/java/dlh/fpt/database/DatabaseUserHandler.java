package dlh.fpt.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import dlh.fpt.entities.User;
import dlh.fpt.entities.Word;


/**
 * Created by Daniel on 8/1/2015.
 */
public class DatabaseUserHandler extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "DLH";
    //table user
    private static int DATABASE_VERSION = 1;
    public static String TABLE_USER = "User";
    public static String USER_ID = "userID";
    public static String USER_NAME = "name";
    //table word
    public static String TABLE_WORDS = "Words";
    public static String WORD_ID = "wordID";
    public static String WORD_WORD = "word";
    public static String WORD_USERID = "userID";
    Context context;

    public DatabaseUserHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableUser = "create table " + TABLE_USER + " ( " + USER_ID +" Integer primary key, " + USER_NAME + " text "+ ")";
        String tableWord = "create table " + TABLE_WORDS + " ( " +
                WORD_ID +" Integer primary key, " +
                WORD_WORD + " text, "+ WORD_USERID + " Integer " + ")";
        db.execSQL(tableUser);
        db.execSQL(tableWord);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlUser = "drop table if exists " + TABLE_USER;
        String sqlWord = "drop table if exists " + TABLE_WORDS;
        db.execSQL(sqlUser);
        db.execSQL(sqlWord);
        onCreate(db);
    }

    public boolean addUser(User user) {
        boolean check = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, user.getUserID());
        contentValues.put(USER_NAME, user.getName());
        if(db.insert(TABLE_USER, null, contentValues) != -1) {
            Toast.makeText(context, "Add sucess", Toast.LENGTH_LONG).show();
            check = true;
        }else{
            Toast.makeText(context, "Add failed", Toast.LENGTH_LONG).show();
        }
        db.close();
        return check;
    }

    public boolean checkUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_USER  + " where " + USER_NAME + " = '" + username + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() != 0) {
            return true;
        }else
            return false;
    }

    //table Word
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

    public boolean checkExistWork(String word) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_WORDS  + " where " + WORD_WORD + " = '" + word + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() != 0) {
            return true;
        }else
            return false;
    }
}
