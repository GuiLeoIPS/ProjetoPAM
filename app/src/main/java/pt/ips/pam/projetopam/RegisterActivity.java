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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
                username = findViewById(R.id.editName);
                email = findViewById(R.id.editRegisterEmail);
                password = findViewById(R.id.editPass);
                phoneNumber = findViewById(R.id.editTelemovel);
                User user = new User(username.getText().toString(), email.getText().toString(), password.getText().toString(), phoneNumber.getText().toString(), false);
                MyDatabaseHelper db = new MyDatabaseHelper(RegisterActivity.this);
                db.addUser(user);
                finish();
            }
        });

    }
}