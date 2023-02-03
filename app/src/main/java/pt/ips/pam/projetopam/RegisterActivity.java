package pt.ips.pam.projetopam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    private EditText username, email, password, phoneNumber;

    private void startVariables() {
        username = findViewById(R.id.editName);
        email = findViewById(R.id.editRegisterEmail);
        password = findViewById(R.id.editPass);
        phoneNumber = findViewById(R.id.editTelemovel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        startVariables();
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    finish();
            }
        });

        ImageButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = username.getText().toString();
                    String mail = email.getText().toString();
                    String pass = password.getText().toString();
                    String number = phoneNumber.getText().toString();

                    MyDatabaseHelper db = new MyDatabaseHelper(RegisterActivity.this);

                    User user = new User(name, mail, pass, number, false);

                    if(username.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty()) {
                        Toast.makeText(RegisterActivity.this, R.string.emptyStrings, Toast.LENGTH_SHORT).show();
                    } else {
                        db.addUser(user);
                        Toast.makeText(RegisterActivity.this, R.string.createdUser, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                } catch(Exception ex) {
                    Toast.makeText(RegisterActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}