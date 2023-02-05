package pt.ips.pam.projetopam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class BooksClienteActivity extends AppCompatActivity {

    MyDatabaseHelper myDB;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    BooksClienteAdapter booksAdapter;

    private String idUser;
    private MyDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_cliente);

        db = new MyDatabaseHelper(BooksClienteActivity.this);


        RecyclerView recyclerview;
        FloatingActionButton add_button;

        getAndSetIntentData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.books);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        Intent intent = new Intent(BooksClienteActivity.this, HomeClienteActivity.class);
                        intent.putExtra("id", String.valueOf(idUser));
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        Intent intent2 = new Intent(BooksClienteActivity.this, UsersClienteActivity.class);
                        intent2.putExtra("id", String.valueOf(idUser));
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.books:
                        return true;
                }
                return false;
            }
        });

        recyclerview = findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(BooksClienteActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        storeDataInArrays();

        booksAdapter = new BooksClienteAdapter(BooksClienteActivity.this, this, book_id, book_title, book_author, book_pages);
        recyclerview.setAdapter(booksAdapter);
//        GridLayoutManager manager = new GridLayoutManager(BooksClienteActivity.this, 2, GridLayoutManager.VERTICAL, false);
//        recyclerview.setLayoutManager(manager);
        LinearLayoutManager manager = new LinearLayoutManager(BooksClienteActivity.this);
        recyclerview.setLayoutManager(manager);
    }


    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, R.string.emptyData, Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
        }

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")){

            //Toast.makeText(this, "I have the id " + getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();
            idUser = getIntent().getStringExtra("id");
            User user = db.getUserById(Integer.parseInt(idUser));
        }else{
            Toast.makeText(this, R.string.emptyData, Toast.LENGTH_SHORT).show();
        }
    }
}