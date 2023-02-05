package pt.ips.pam.projetopam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeClienteActivity extends AppCompatActivity {

    String id;
    TextView txtid;

    private MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cliente);

        //Icon
        Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.libraryiconpng, this.getTheme());
        assert draw != null; //Verifica se o mesmo não está vazio

        Bitmap bitmap = ((BitmapDrawable) draw).getBitmap();
        Drawable icon = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 40, true));

        getSupportActionBar().setHomeAsUpIndicator(icon);        //colocar o novo icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //Enable para mostrar o novo icon
        //-------------


        txtid = findViewById(R.id.txtId);
        db = new MyDatabaseHelper(HomeClienteActivity.this);

        getAndSetIntentData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);

        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch(item.getItemId()){
                    case R.id.home:
                        return true;

                    case R.id.profile:


                        Intent intent = new Intent(HomeClienteActivity.this, UsersClienteActivity.class);
                        intent.putExtra("id", String.valueOf(id));
                        startActivity(intent);
                        overridePendingTransition(0,0);

                        return true;

                    case R.id.books:

                        Intent intent2 = new Intent(HomeClienteActivity.this, BooksClienteActivity.class);
                        intent2.putExtra("id", String.valueOf(id));
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")){

            //Toast.makeText(this, "I have the id " + getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();
            id = getIntent().getStringExtra("id");
            User user = db.getUserById(Integer.parseInt(id));
            //txtid.setText(id);
        }else{
            Toast.makeText(this, R.string.emptyData, Toast.LENGTH_SHORT).show();
        }
    }
}