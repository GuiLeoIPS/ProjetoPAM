package pt.ips.pam.projetopam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserUpdateActivity extends AppCompatActivity {

    EditText name_input, password_input, email_input, number_input;
    Button update_button;

    String id, name, password, email, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);


        name_input = findViewById(R.id.name_input);
        password_input = findViewById(R.id.password_input);
        email_input = findViewById(R.id.email_input);
        number_input = findViewById(R.id.number_input);
        update_button = findViewById(R.id.update_button);

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UserUpdateActivity.this);
                name = name_input.getText().toString().trim();
                password = password_input.getText().toString().trim();
                email = email_input.getText().toString().trim();
                number = number_input.getText().toString().trim();
                myDB.updateUserData(id, name, password, email, number);

            }
        });





    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("password") && getIntent().hasExtra("email") && getIntent().hasExtra("number")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            password = getIntent().getStringExtra("password");
            email = getIntent().getStringExtra("email");
            number = getIntent().getStringExtra("number");

            //Setting Intent Data
            name_input.setText(name);
            password_input.setText(password);
            email_input.setText(email);
            number_input.setText(number);
            Log.d("stev", name+" "+password+" "+email+" "+number);
        }else{
            Toast.makeText(this, R.string.emptyData, Toast.LENGTH_SHORT).show();
        }
    }
}