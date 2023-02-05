package pt.ips.pam.projetopam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UsersClienteActivity extends AppCompatActivity {

    private EditText editUsername, editEmail, editPass, editPhone;

    private String idUser;
    private MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_cliente);

        db = new MyDatabaseHelper(UsersClienteActivity.this);

        editUsername = findViewById(R.id.editUser);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPasss);
        editPhone = findViewById(R.id.editPhone);

        getAndSetIntentData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);

        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch(item.getItemId()){
                    case R.id.home:
                        Intent intent = new Intent(UsersClienteActivity.this, HomeClienteActivity.class);
                        intent.putExtra("id", String.valueOf(idUser));
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        return true;

                    case R.id.books:

                        Intent intent2 = new Intent(UsersClienteActivity.this, BooksClienteActivity.class);
                        intent2.putExtra("id", String.valueOf(idUser));
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        Button btnEdit = (Button) findViewById(R.id.btnChange);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UsersClienteActivity.this, UserClienteUpdateActivity.class);
                intent.putExtra("id", String.valueOf(idUser));
                startActivity(intent);


            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")){

            //Toast.makeText(this, "I have the id " + getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();
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