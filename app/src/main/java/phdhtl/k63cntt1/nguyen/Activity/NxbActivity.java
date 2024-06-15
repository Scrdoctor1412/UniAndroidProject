package phdhtl.k63cntt1.nguyen.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.detailsActivity.NxbDetailsActivity;
import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.adapter.CustomNxbAdapter;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Publisher;

public class NxbActivity extends AppCompatActivity {

    ListView nxblv;

    Toolbar toolbar;

    ArrayList<Publisher> listNxb;
    CustomNxbAdapter myadapter;

    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nxb);

        initView();

        //for fixing error Row too big to fit into CursorWindow
        try {
            @SuppressLint("PrivateApi")
            Field field =
                    CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }

        dbh = new DBHelper(this);

        ImageView testImg = new ImageView(getApplicationContext());
        testImg.setImageResource(R.drawable.author);
        Bitmap bm = ((BitmapDrawable)testImg.getDrawable()).getBitmap();
        String bmText = ConvertHelper.BitMapToString(bm);

        showList();
//        listNxb.add(new Publisher("nxb01","testingnxb","nxbtesting",bmText));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nxblv.bringToFront();
        nxblv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(getApplicationContext(), NxbDetailsActivity.class);
                myIntent.putExtra("EventCode",0);
                myIntent.putExtra("manxb",listNxb.get(position).getManxb());
                myIntent.putExtra("tennxb",listNxb.get(position).getTennxb());
                myIntent.putExtra("gthnxb",listNxb.get(position).getGioithieu());
                myIntent.putExtra("imgnxb",listNxb.get(position).getImgdaidien());
                startActivityForResult(myIntent, 99);
            }
        });

    }

    public void showList(){
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from publisher", null);
        listNxb.clear();
//        cs.moveToNext();
        try{
            while(cs.moveToNext()){
                Publisher newnxb = new Publisher(cs.getString(0),cs.getString(1),cs.getString(2),cs.getString(3));
                Log.d("newnxb", cs.getString(0));
                listNxb.add(newnxb);
                myadapter.notifyDataSetChanged();
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
            Intent myIntent = new Intent(getApplicationContext(), NxbDetailsActivity.class);
            myIntent.putExtra("EventCode", 1);
            startActivityForResult(myIntent, 99);
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView(){
        nxblv = findViewById(R.id.nxblv);
        toolbar = findViewById(R.id.toolbarnxb);
        listNxb = new ArrayList<>();
        myadapter = new CustomNxbAdapter(NxbActivity.this, R.layout.custom_nxblv, listNxb);
        nxblv.setAdapter(myadapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 99 && resultCode == 33){
//            String ma = data.getStringExtra("manxb");
//            String ten = data.getStringExtra("tennxb");
//            String gth = data.getStringExtra("gthnxb");
//            String img = data.getStringExtra("imgnxb");
//            String sqlInsertNXB = "insert into publisher values('"+ma+"', '"+ten+"', '"+gth+"', '"+img+"')";
//            try{
//                SQLiteDatabase db = dbh.getWritableDatabase();
//                db.execSQL(sqlInsertNXB);
//                showList();
//
//            }catch (Exception e){
//                Toast.makeText(getApplicationContext(),"Không thành công", Toast.LENGTH_LONG).show();
//
//            }
            showList();
        } else if (requestCode == 99 && resultCode == 34) {
            showList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}