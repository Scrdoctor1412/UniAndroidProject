package phdhtl.k63cntt1.nguyen.Activity;

import android.content.DialogInterface;
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

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.adapter.CustomChapterImageAdapter;
import phdhtl.k63cntt1.nguyen.detailsActivity.ChapterImageDetailsActivity;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Chapter;
import phdhtl.k63cntt1.nguyen.model.ChapterImage;
import phdhtl.k63cntt1.nguyen.model.Publisher;

public class ChapterImageActivity extends AppCompatActivity {

    Toolbar toolbar;
    CustomChapterImageAdapter adapter;
    ArrayList<ChapterImage> arrayList;
    ListView lvchapterimg;
    Intent intent;
    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_image);

        dbh = new DBHelper(this);
        intent = getIntent();
        initView();

        showList();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("Chapter Image");

        lvchapterimg.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDialog(arrayList.get(position), position);
                return false;
            }
        });
    }

    public void showDialog(ChapterImage mychapterimg,int pos){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Chọn option:");
//        b.setMessage("Bạn có đồng ý xóa mục  "+s+" này không ?");
        b.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                Intent myIntent = new Intent(getApplicationContext() ,ChapterImageDetailsActivity.class);
                myIntent.putExtra("EventCode", 2);
                myIntent.putExtra("img", mychapterimg.getHinhanh());
                myIntent.putExtra("mahinhanhchuong", mychapterimg.getMahinhanhchuong());
                startActivityForResult(myIntent, 99);
            }
        });
        b.setNegativeButton("Xóa ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                showDialogconfirmDelete(pos,Integer.parseInt(mychapterimg.getMahinhanhchuong()));
            }
        });
        AlertDialog al = b.create();
        al.show();
    }

    private void showDialogconfirmDelete(int pos, int bookid){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Xác nhận");
        b.setMessage("Bạn có đồng ý xóa hình này không ?");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                String sql = "delete from chapterimage where mahinhanhchuong='"+bookid+"'";
                try {
                    arrayList.remove(pos);
                    SQLiteDatabase db1 = dbh.getWritableDatabase();
                    db1.execSQL(sql);
                    showList();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Không thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
        b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }

    public void showList(){
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from chapterimage", null);
        arrayList.clear();
        try{
            while(cs.moveToNext()){
                ChapterImage newchapterimg = new ChapterImage(cs.getInt(0)+"",cs.getString(1),cs.getString(2));
                arrayList.add(newchapterimg);
                Log.d("newchapterimg", cs.getInt(0)+"");
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            Log.d("publisher db error", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_insert_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_insert){
            Intent myIntent = new Intent(getApplicationContext(), ChapterImageDetailsActivity.class);
            myIntent.putExtra("EventCode", 1);
            myIntent.putExtra("machuong", intent.getStringExtra("machuong"));
            startActivityForResult(myIntent, 99);
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView(){
        toolbar = findViewById(R.id.toolbarchapterimage);
        arrayList = new ArrayList<>();
        adapter = new CustomChapterImageAdapter(ChapterImageActivity.this, R.layout.custom_chapterimage_row, arrayList);
        lvchapterimg = findViewById(R.id.lvchapterimg);
        lvchapterimg.setAdapter(adapter);
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