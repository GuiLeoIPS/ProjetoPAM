package pt.ips.pam.projetopam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {

    MyDatabaseHelper myDB;
    ArrayList<String> user_id, user_name, user_password, user_email, user_number;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        //Icon
        Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.libraryiconpng, this.getTheme());
        assert draw != null; //Verifica se o mesmo não está vazio

        Bitmap bitmap = ((BitmapDrawable) draw).getBitmap();
        Drawable icon = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 40, true));

        getSupportActionBar().setHomeAsUpIndicator(icon);        //colocar o novo icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //Enable para mostrar o novo icon
        //-------------

        RecyclerView recyclerview;


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_users);

        bottomNavigationView.setSelectedItemId(R.id.user);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch(item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.user:
                        return true;

                    case R.id.books:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }



                return false;
            }
        });
        recyclerview = findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(UsersActivity.this);
        user_id = new ArrayList<>();
        user_name = new ArrayList<>();
        user_password = new ArrayList<>();
        user_email = new ArrayList<>();
        user_number = new ArrayList<>();

        storeDataInArrays();

        userAdapter = new UserAdapter(UsersActivity.this, this, user_id, user_name, user_password, user_email, user_number);
        recyclerview.setAdapter(userAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(UsersActivity.this);
        recyclerview.setLayoutManager(manager);
        // recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllDataUsers();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, R.string.emptyData, Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_password.add(cursor.getString(2));
                user_email.add(cursor.getString(3));
                user_number.add(cursor.getString(4));
            }
        }

    }
}