package pt.ips.pam.projetopam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

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

    //----------------------livros---------------------------------

    private static final String TABLE_NAME = "Books";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES = "book_pages";




    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
                " (" + COLUMN_IDUSER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_NUMBER + " INTEGER, " +
                COLUMN_ADMIN + " INTEGER);";

        // create USER table
        db.execSQL(CREATE_USER_TABLE);

        User primeiroUser = new User("Guilherme", "gui@gmail.com", "adm321", "987654231", 1);
        User segundoUser = new User("Leonardo", "leo@gmail.com", "adm123", "912345687", 1);
        User terceiroUser = new User("Miguel", "mig@gmail.com", "cliente", "965432178", 0);
        addUser(primeiroUser);
        addUser(segundoUser);
        addUser(terceiroUser);

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PAGES + " INTEGER);";
        db.execSQL(query);

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
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_NUMBER, user.getNumber());
        values.put(COLUMN_ADMIN, user.isAdmin());

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
                user.setIdUser(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(3));
                user.setAdmin(cursor.getInt(5));
                users.add(user);
            } while (cursor.moveToNext());
        }

        Log.d("getAllUsers()", users.toString());
        return users;
    }


    public User getUserById(int id) {


        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_IDUSER + "='"+ id+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = null;
        if (cursor.moveToFirst()) {
            do {
                user = new User();
                user.setIdUser(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setNumber(cursor.getString(4));
                user.setAdmin(cursor.getInt(5));
                
            } while (cursor.moveToNext());
        }

        Log.d("getUserById()", user.toString());
        return user;
    }

    public void addBook (String title, String author, int pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, R.string.operationFailed, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, R.string.addBookSucceed, Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    void updateData(String row_id, String title, String author, String pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, R.string.operationFailed, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, R.string.updateBookSucceed, Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String rows_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"id=?", new String[]{rows_id});
        if(result == -1){
            Toast.makeText(context, R.string.operationFailed, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, R.string.deleteBookSucceed, Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllDataUsers(){
        String query = "SELECT * FROM user";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    void updateUserData(String row_id, String name, String password, String email, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, name);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_NUMBER, number);

        long result = db.update(TABLE_USER, cv, COLUMN_IDUSER+"=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, R.string.operationFailed, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, R.string.updateBookSucceed, Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRowUser(String rows_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_USER,COLUMN_IDUSER+"=?", new String[]{rows_id});
        if(result == -1){
            Toast.makeText(context, R.string.operationFailed, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, R.string.deleteBookSucceed, Toast.LENGTH_SHORT).show();
        }
    }


    public void funcaoNova() {
        String nova;
    }

}
