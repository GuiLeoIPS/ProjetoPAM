package pt.ips.pam.projetopam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Intent intent = new Intent(AddActivity.this, MainActivity.class);




        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

                if(title_input.getText().toString().isEmpty() || author_input.getText().toString().isEmpty() || pages_input.getText().toString().isEmpty()) {
                    Toast.makeText(AddActivity.this, R.string.emptyStrings, Toast.LENGTH_SHORT).show();
                } else {
                    myDB.addBook(title_input.getText().toString().trim(),
                            author_input.getText().toString().trim(),
                            Integer.valueOf(pages_input.getText().toString().trim()));
                    finish();
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}