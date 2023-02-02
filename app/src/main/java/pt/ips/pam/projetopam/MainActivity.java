package pt.ips.pam.projetopam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper db;

    private static final String VALOR_USERNAME = "USERNAME";
    private static final String VALOR_PASSWORD = "PASSWORD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDatabaseHelper(MainActivity.this);

        PopulateUsers();

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pesquisarUser() == 1) {
                    Intent intent = new Intent(MainActivity.this, BooksActivity.class);
                    startActivity(intent);
                };

            }
        });

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);

            }
        });
    }

    private int searchUserByName(List<User> list, String username, String password) {
        int indiceDelete = -1;
        for (User usr : list) {
            if (usr.getUsername().equals(username) && usr.getPassword().equals(password))
                indiceDelete = list.indexOf(usr);
        }
        return indiceDelete;
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
            Toast.makeText(this, "Found", Toast.LENGTH_SHORT).show();
            return 1;
        } else {
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
            return 0;
        }

    }

    private void PopulateUsers() {
        User primeiroUser = new User("Guilherme", "gui@gmail.com", "adm321", "987654231", true);
        User segundoUser = new User("Leonardo", "leo@gmail.com", "adm123", "912345687", true);
        User terceiroUser = new User("Miguel", "mig@gmail.com", "cliente", "965432178", false);
        db.addUser(primeiroUser);
        db.addUser(segundoUser);
        db.addUser(terceiroUser);
    }

}