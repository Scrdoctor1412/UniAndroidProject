package phdhtl.k63cntt1.nguyen.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.detailsActivity.StoryDetailsActivity;
import phdhtl.k63cntt1.nguyen.adapter.CustomStoryAdapter;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Publisher;
import phdhtl.k63cntt1.nguyen.model.Story;
import phdhtl.k63cntt1.nguyen.model.Type_Story;

public class StoryActivity extends AppCompatActivity {

    ListView storylv;
    ArrayList<Story> listTruyen;
    CustomStoryAdapter myAdapter;

    Toolbar toolbar;
    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        initView();
        ImageView img = new ImageView(getApplicationContext());
        img.setImageResource(R.drawable.author);
        Bitmap bm = ((BitmapDrawable)img.getDrawable()).getBitmap();
        String bmText = ConvertHelper.BitMapToString(bm);
        dbh = new DBHelper(this);
        showList();
//        listTruyen.add(new Story("T01", "testing", bmText , "tg01", "test", 10, 10000, 32,"",""));

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Set click event for sending data to Story Details activity
        storylv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Story mytruyen = listTruyen.get(position);
                Intent myIntent = new Intent(getApplicationContext(), StoryDetailsActivity.class);
                myIntent.putExtra("EventCode",0);
                myIntent.putExtra("matruyen", mytruyen.getMatruyen());
                myIntent.putExtra("tentruyen", mytruyen.getTentruyen());
//                myIntent.putExtra("tacgia", mytruyen.getTentg());
                myIntent.putExtra("noidung", mytruyen.getNoidung());
                myIntent.putExtra("theloai", mytruyen.getTheloai());
//                myIntent.putExtra("sochuong", mytruyen.getSochuong());
                myIntent.putExtra("anhbia", mytruyen.getAnhbia());
//                myIntent.putExtra("nxb", mytruyen.getTennxb());
                myIntent.putExtra("luotlike",mytruyen.getLuotlike());
                myIntent.putExtra("luotxem",mytruyen.getLuotxem());

                startActivityForResult(myIntent, 99);
            }
        });
    }

    public void showList(){
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cs = db.rawQuery("select stories.matruyen, stories.tentruyen, stories.noidung, stories.imgdaidien, authors.tentacgia, publisher.tennxb, stories.luotlike, stories.luotxem from stories inner join authors on stories.matacgia = authors.matacgia inner join publisher on stories.manxb = publisher.manxb", null);
        String theloai = "";
        Story newstory;
        listTruyen.clear();
        try{
            while(cs.moveToNext()){
                newstory = new Story(cs.getString(0),cs.getString(1),cs.getString(2), cs.getString(3), "", cs.getString(4), cs.getString(5) ,cs.getInt(6), cs.getInt(7));
                Cursor cs2 = db.rawQuery("select * from typestory where matruyen = '"+newstory.getMatruyen()+"'",null);
                theloai = "";
                while(cs2.moveToNext()){
                    Type_Story getTypeStory = new Type_Story(cs2.getString(0), cs2.getString(1));
                    Cursor cs3 = db.rawQuery("select types.tentl from types where types.matl = '"+getTypeStory.getMatl()+"'",null);
                    cs3.moveToNext();
                    theloai += cs3.getString(0);
                }
                newstory.setTheloai(theloai);
                listTruyen.add(newstory);
                myAdapter.notifyDataSetChanged();
            }

        }catch (Exception e){
            Log.d("publisher db error", e.getMessage());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_insert_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_insert){
            Intent myIntent = new Intent(getApplicationContext(),StoryDetailsActivity.class);
            myIntent.putExtra("EventCode", 1);
            startActivityForResult(myIntent,99);
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView(){
        storylv = findViewById(R.id.storylv);
        listTruyen = new ArrayList<>();
        myAdapter = new CustomStoryAdapter(StoryActivity.this, R.layout.custom_storylv, listTruyen);
        storylv.setAdapter(myAdapter);
        toolbar = findViewById(R.id.toolbar2);
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