package phdhtl.k63cntt1.nguyen.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.adapter.CustomChapterAdapter;
import phdhtl.k63cntt1.nguyen.detailsActivity.ChapterDetailsActivity;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Chapter;
import phdhtl.k63cntt1.nguyen.model.Publisher;

public class ChapterActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvchapter;
    ArrayList<Chapter> arrChapter;
    CustomChapterAdapter adapter;
    Intent intent;
    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        dbh = new DBHelper(this);
        intent = getIntent();
        initView();

        showList();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_insert_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_insert){
            Intent myIntent = new Intent(getApplicationContext(), ChapterDetailsActivity.class);
            String ma = intent.getStringExtra("matruyen");
            myIntent.putExtra("matruyen", ma);
            myIntent.putExtra("EventCode", 1);
            startActivityForResult(myIntent, 99);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showList(){
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from chapters", null);
        arrChapter.clear();
//        cs.moveToNext();
        try{
            while(cs.moveToNext()){
                Chapter newchap = new Chapter(cs.getString(0), cs.getInt(1) ,cs.getString(2), cs.getString(3));
                arrChapter.add(newchap);
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            Log.d("publisher db error", e.getMessage());
        }
    }

    public void initView(){
        toolbar = findViewById(R.id.toolbarchapter);
        lvchapter = findViewById(R.id.lvchapter);
        arrChapter = new ArrayList<>();
        adapter = new CustomChapterAdapter(ChapterActivity.this, R.layout.custom_chapter_row, arrChapter);
        lvchapter.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 99 && resultCode == 33){
            showList();
        }else if(requestCode == 99 && resultCode == 34){
            showList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}