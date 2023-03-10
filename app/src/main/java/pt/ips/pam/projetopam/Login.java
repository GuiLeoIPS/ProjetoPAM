package pt.ips.pam.projetopam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

public class Login extends AppCompatActivity {

    private MyDatabaseHelper db;

    private static final String VALOR_USERNAME = "USERNAME";
    private static final String VALOR_PASSWORD = "PASSWORD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Base de dados
        db = new MyDatabaseHelper(Login.this);
        readValues();

        //Icon
        Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.libraryiconpng, this.getTheme());
        assert draw != null; //Verifica se o mesmo não está vazio

        Bitmap bitmap = ((BitmapDrawable) draw).getBitmap();
        Drawable icon = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 40, true));

        getSupportActionBar().setHomeAsUpIndicator(icon);        //colocar o novo icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //Enable para mostrar o novo icon
        //-------------

        //Botão Login
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pesquisarUser() == 1) {
                    SaveValues();

                    EditText editNome = (EditText) findViewById(R.id.editUsername);
                    EditText editPass = (EditText) findViewById(R.id.editPassword);
                    String nome = editNome.getText().toString();
                    String pass = editPass.getText().toString();
                    List<User> list = db.getAllUsers();

                    if(searchIfIsAdmin(list, nome, pass) == 1) {
                        //Toast.makeText(Login.this, "É admin", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        int idUser = findIdUser(list, nome, pass);
                        //Toast.makeText(Login.this, ""+idUser, Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Login.this, HomeClienteActivity.class);
                        intent2.putExtra("id", String.valueOf(idUser));
                        startActivity(intent2);
                    }

                };
            }
        });

        //Botão de Register
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(Login.this, RegisterActivity.class);
                    startActivity(intent);
            }
        });
    }

    //Salvar valores em SharedPreferences
    private void SaveValues() {
        EditText editName = findViewById(R.id.editUsername);
        EditText editPass = findViewById(R.id.editPassword);

        String name = editName.getText().toString();
        String password = editPass.getText().toString();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString(VALOR_USERNAME, name);
        edit.putString(VALOR_PASSWORD, password);

        edit.commit();

    }

    //Ler valores
    private void readValues() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String nameSaved = sharedPref.getString(VALOR_USERNAME, "");
        String passSaved = sharedPref.getString(VALOR_PASSWORD, "");

        EditText editName = findViewById(R.id.editUsername);
        EditText editPass = findViewById(R.id.editPassword);

        editName.setText(nameSaved);
        editPass.setText(passSaved);

    }

    private int searchUserByName(List<User> list, String username, String password) {
        int indiceDelete = -1;
        for (User usr : list) {
            if (usr.getUsername().equals(username) && usr.getPassword().equals(password))
                indiceDelete = list.indexOf(usr);
        }
        return indiceDelete;
    }

    private int searchIfIsAdmin(List<User> list, String username, String password) {
        int indice = 0;
        int id = -1;
        for (User usr : list) {

            if (usr.getUsername().equals(username) && usr.getPassword().equals(password)) {
                indice = usr.isAdmin();
                //Toast.makeText(this, "" + usr.isAdmin(), Toast.LENGTH_SHORT).show();
                id = usr.getIdUser();
            }
            //indiceDelete = list.indexOf(usr);
        }

        return indice;
    }
    private int findIdUser(List<User> list, String username, String password) {
        int indice = 0;
        int id = -1;
        for (User usr : list) {
            if (usr.getUsername().equals(username) && usr.getPassword().equals(password)) {
                id = usr.getIdUser();
                //Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
                return id;
            }
        }
        return id;
    }

    private int pesquisarUser() {

        EditText editNome = (EditText) findViewById(R.id.editUsername);
        EditText editPass = (EditText) findViewById(R.id.editPassword);
        String nome = editNome.getText().toString();
        String pass = editPass.getText().toString();

        // get all users
        List<User> list = db.getAllUsers();

        int indiceDelete = searchUserByName(list, nome, pass);

        if (indiceDelete != -1) {
            Toast.makeText(this, getResources().getText(R.string.foundUser), Toast.LENGTH_SHORT).show();
            return 1;
        } else {
            Toast.makeText(this, getResources().getText(R.string.notFoundUser), Toast.LENGTH_SHORT).show();
            return 0;
        }

    }

    @Override
    public void onBackPressed() {
    }

}