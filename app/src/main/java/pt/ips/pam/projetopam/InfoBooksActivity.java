package pt.ips.pam.projetopam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class InfoBooksActivity extends AppCompatActivity {

    private EditText editTitle, editAuthor, editPages;

    private String id, title, author, pages;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_books);

        //Icon
        Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.libraryiconpng, this.getTheme());
        assert draw != null; //Verifica se o mesmo não está vazio

        Bitmap bitmap = ((BitmapDrawable) draw).getBitmap();
        Drawable icon = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 40, true));

        getSupportActionBar().setHomeAsUpIndicator(icon);        //colocar o novo icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //Enable para mostrar o novo icon
        //-------------

        editTitle = findViewById(R.id.editTitleBook);
        editAuthor = findViewById(R.id.editAuthorBook);
        editPages = findViewById(R.id.editPagesBook);

        getAndSetIntentData();

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //Setting Intent Data
            editTitle.setText(title);
            editAuthor.setText(author);
            editPages.setText(pages);
        }else{
            Toast.makeText(this, R.string.emptyData, Toast.LENGTH_SHORT).show();
        }
    }
}