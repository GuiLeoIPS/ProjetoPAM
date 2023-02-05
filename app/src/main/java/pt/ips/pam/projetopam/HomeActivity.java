package pt.ips.pam.projetopam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Icon
        Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.libraryiconpng, this.getTheme());
        assert draw != null; //Verifica se o mesmo não está vazio

        Bitmap bitmap = ((BitmapDrawable) draw).getBitmap();
        Drawable icon = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 40, true));

        getSupportActionBar().setHomeAsUpIndicator(icon);        //colocar o novo icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //Enable para mostrar o novo icon
        //-------------

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_home);

        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch(item.getItemId()){
                    case R.id.home:
                        return true;

                    case R.id.user:
                        startActivity(new Intent(getApplicationContext(), UsersActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.books:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }



                return false;
            }
        });
    }
}