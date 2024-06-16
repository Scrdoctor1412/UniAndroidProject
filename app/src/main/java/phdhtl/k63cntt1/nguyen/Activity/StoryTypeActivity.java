package phdhtl.k63cntt1.nguyen.Activity;

import static androidx.core.app.ActivityCompat.startActivityForResult;

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
import phdhtl.k63cntt1.nguyen.adapter.CustomStoryTypeAdapter;
import phdhtl.k63cntt1.nguyen.detailsActivity.StoryTypeDetailsActivity;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Publisher;
import phdhtl.k63cntt1.nguyen.model.Type;

public class StoryTypeActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvStoryType;
    CustomStoryTypeAdapter adapter;
    ArrayList<Type> typeList;
    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_type);
        dbh = new DBHelper(this);
        initView();
        showList();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvStoryType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(getApplicationContext(), StoryTypeDetailsActivity.class);
                myIntent.putExtra("EventCode",0);
                myIntent.putExtra("matl", typeList.get(position).getMatl());
                myIntent.putExtra("tentl", typeList.get(position).getTentl());
                myIntent.putExtra("ndtl", typeList.get(position).getNoidungtl());
                startActivityForResult(myIntent, 99);
            }
        });
    }

    public void showList(){
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from types", null);
        typeList.clear();
//        cs.moveToNext();
        try{
            while(cs.moveToNext()){
                Type newtype = new Type(cs.getString(0), cs.getString(1), cs.getString(2) );
                typeList.add(newtype);
                adapter.notifyDataSetChanged();
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
            Intent myIntent = new Intent(getApplicationContext(), StoryTypeDetailsActivity.class);
            myIntent.putExtra("EventCode", 1);
            startActivityForResult(myIntent, 99);
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView(){
        typeList = new ArrayList<>();
        toolbar = findViewById(R.id.toolbarstorytype);
        lvStoryType = findViewById(R.id.lvStoryType);
        adapter = new CustomStoryTypeAdapter(StoryTypeActivity.this, R.layout.custom_story_type_row, typeList);
        lvStoryType.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 99 && resultCode == 33){
            showList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}