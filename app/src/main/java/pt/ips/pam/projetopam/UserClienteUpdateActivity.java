package pt.ips.pam.projetopam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UserClienteUpdateActivity extends AppCompatActivity {

    private EditText editUsername, editEmail, editPass, editPhone;

    private String name, password, email, number;

    private String idUser;
    private MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cliente_update);

        db = new MyDatabaseHelper(UserClienteUpdateActivity.this);
        editUsername = findViewById(R.id.editGt);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPasss);
        editPhone = findViewById(R.id.editPhone);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(editUsername.getText().toString());
        }

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = editUsername.getText().toString().trim();
                password = editPass.getText().toString().trim();
                email = editEmail.getText().toString().trim();
                number = editPhone.getText().toString().trim();

                if(editUsername.getText().toString().isEmpty() || editPass.getText().toString().isEmpty() || editEmail.getText().toString().isEmpty() || editPhone.getText().toString().isEmpty()) {
                    Toast.makeText(UserClienteUpdateActivity.this, R.string.emptyStrings, Toast.LENGTH_SHORT).show();
                } else {
                    db.updateUserData(idUser, name, password, email, number);
                    Intent intent = new Intent(UserClienteUpdateActivity.this, Login.class);
                    startActivity(intent);
                }



            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")){

            idUser = getIntent().getStringExtra("id");
            User user = db.getUserById(Integer.parseInt(idUser));
            editUsername.setText(user.getUsername());
            editEmail.setText(user.getEmail());
            editPass.setText(user.getPassword());
            editPhone.setText(user.getNumber());
        }else{
            Toast.makeText(this, R.string.emptyData, Toast.LENGTH_SHORT).show();
        }
    }
}