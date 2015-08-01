package dlh.fpt.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import dlh.fpt.entities.User;


/**
 * Created by Daniel on 8/1/2015.
 */
public class DatabaseUserHandler extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "DLH";
    private static int DATABASE_VERSION = 1;
    public static String TABLE_USER = "User";
    public static String USER_ID = "userID";
    public static String USER_NAME = "name";
    Context context;

    public DatabaseUserHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "create table " + TABLE_USER + " ( " + USER_ID +" Integer primary key, " + USER_NAME + " text "+ ")";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists " + TABLE_USER;
        db.execSQL(sql);
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
}
