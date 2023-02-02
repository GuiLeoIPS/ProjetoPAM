package pt.ips.pam.projetopam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "biblioteca";

    private static final String TABLE_USER = "users";
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


    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
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
}
