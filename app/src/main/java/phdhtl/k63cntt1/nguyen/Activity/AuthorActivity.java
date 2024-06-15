package phdhtl.k63cntt1.nguyen.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.adapter.CustomAuthorAdapter;
import phdhtl.k63cntt1.nguyen.detailsActivity.AuthorDetailsActivity;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Author;

public class AuthorActivity extends AppCompatActivity {
    private ListView lvAuthor;
    private CustomAuthorAdapter customAuthorAdapter;
    private ArrayList<Author> authorList;
    Toolbar toolbar;
    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

//        initDummyAuthorData();
        initView();
        dbh = new DBHelper(this);
        showList();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lvAuthor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(getApplicationContext(), AuthorDetailsActivity.class);
                myIntent.putExtra("EventCode",0);
                myIntent.putExtra("matg",authorList.get(position).getId());
                myIntent.putExtra("tentg",authorList.get(position).getName());
                myIntent.putExtra("gthtg",authorList.get(position).getIntroducing());
                myIntent.putExtra("imgtg",authorList.get(position).getImgdaidien());

                startActivityForResult(myIntent, 99);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_insert_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_insert){
            Toast.makeText(getApplicationContext(), "oke", Toast.LENGTH_LONG).show();
            Intent myIntent= new Intent(getApplicationContext(), AuthorDetailsActivity.class);
            myIntent.putExtra("EventCode",1);
            startActivityForResult(myIntent, 99);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showList(){
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from authors", null);
        authorList.clear();
//        cs.moveToNext();
        try{
            while(cs.moveToNext()){
                Author newauthor = new Author(cs.getString(0),cs.getString(1),cs.getString(2),cs.getString(3));
                Log.d("newtg", cs.getString(0));
                authorList.add(newauthor);
                customAuthorAdapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            Log.d("author db error", e.getMessage());
        }
    }

    private void initView() {
        lvAuthor = findViewById(R.id.lv_author);
        authorList = new ArrayList<>();
        customAuthorAdapter = new CustomAuthorAdapter(this, R.layout.custom_author_row ,authorList);
        lvAuthor.setAdapter(customAuthorAdapter);
        toolbar = findViewById(R.id.toolbarauthor);

    }

    private void initDummyAuthorData() {
        authorList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            authorList.add(new Author("test", "test", "test", ""));
        }
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