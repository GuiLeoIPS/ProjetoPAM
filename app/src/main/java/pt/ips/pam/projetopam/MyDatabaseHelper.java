package pt.ips.pam.projetopam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "biblioteca";

    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String COLUMN_IDUSER = "idUser";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_ADMIN = "admin";

    private static final String TABLE_BOOK = "livros";
    // User Table Columns names
    private static final String COLUMN_IDBOOK= "idBook";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRICAO = "descricao"; //Colocar em inglês
    private static final String COLUMN_NUMBERPAGES = "numberPages";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE user ( " +
                "idUser INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, "+
                "email TEXT, "+
                "password TEXT, "+
                "number TEXT, "+
                "admin Integer)";

        // create USER table
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");

        this.onCreate(db);
    }

    public void addUser(User user){

        Log.d("addUser", user.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());            // get username
        values.put(COLUMN_PASSWORD, user.getPassword());            // get pass

        db.insert(TABLE_USER, null, values); // key/value -> keys = column names/ values = column values
        db.close();
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<User>();

        String query = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = null;
        if (cursor.moveToFirst()) {
            do {
                user = new User();
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(3));
                users.add(user);
            } while (cursor.moveToNext());
        }

        Log.d("getAllUsers()", users.toString());
        return users;
    }



}
