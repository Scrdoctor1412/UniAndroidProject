package phdhtl.k63cntt1.nguyen.detailsActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.adapter.CustomTheLoaiMultiSpinner;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Author;
import phdhtl.k63cntt1.nguyen.model.ListItem;
import phdhtl.k63cntt1.nguyen.model.Publisher;
import phdhtl.k63cntt1.nguyen.model.Type;

public class StoryDetailsActivity extends AppCompatActivity {

    private static final int PICK_PHOTO_FOR_AVATAR = 1;
    ImageView imgdaidien;
    EditText edtmatruyen, edttentruyen, edtnoidung, edtsochuong, edtluotlike,edtluotxem;
    Button btnpickimg, btnluu;
    Spinner sptacgia, sptheloai, spnxb;
    ArrayList<String> listStrTacGia, listStrNxb;
    ArrayList<Type> listTheLoai;
    ArrayList<Author> listTacGia;
    ArrayList<Publisher> listNxb;
    ArrayAdapter<String> adapterTacGia,adapterNxb;
    CustomTheLoaiMultiSpinner adapterTheLoai;
    private List<ListItem> selectedItem = new ArrayList<>();
    private ArrayList<ListItem> spinnerListItem = new ArrayList<>();
    private  List<ListItem> getSelectedTheLoaiList = new ArrayList<>();
    Toolbar toolbar;
    DBHelper dbh;
    int EventCode;
    Intent intent;

    private Animation rotateOpen;
    private Animation rotateClose;
    private Animation fromBottom;
    private Animation toBottom;
    Boolean clicked = false;
    FloatingActionButton fabAdd, fabEdit, fabGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_story_details);
        dbh = new DBHelper(this);
        initView();
        initTacgiaSpinner();
        initNxbSpinner();
        initTheloaiSpinner();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        intent = getIntent();
        EventCode = intent.getIntExtra("EventCode", 0);
//        edtmatruyen.setText(myIntent.getStringExtra("matruyen"));
//        edttentruyen.setText(myIntent.getStringExtra("tentruyen"));
//        edtnoidung.setText(myIntent.getStringExtra("noidung"));
//        edttacgia.setText(myIntent.getStringExtra("tacgia"));
//        edtnxb.setText(myIntent.getStringExtra("nxb"));
//        edttheloai.setText(myIntent.getStringExtra("theloai"));
//        int sc = myIntent.getIntExtra("sochuong",0);
//        edtsochuong.setText(""+sc);
//        int img = myIntent.getIntExtra("anhbia",R.drawable.ic_launcher_background);
//        imganhtruyen.setImageResource(img);
//
//        edtluotlike.setText(myIntent.getIntExtra("luotlike", 0)+"");
//        edtluotxem.setText(myIntent.getIntExtra("luotxem",0)+"");
        if(EventCode == 0){
            edtmatruyen.setEnabled(false);
            edttentruyen.setEnabled(false);
            edtluotlike.setEnabled(false);
            edtluotxem.setEnabled(false);
            edtnoidung.setEnabled(false);
            sptheloai.setEnabled(false);
            spnxb.setEnabled(false);
            sptacgia.setEnabled(false);
            btnluu.setVisibility(View.INVISIBLE);
            btnpickimg.setVisibility(View.INVISIBLE);

            edtmatruyen.setText(intent.getStringExtra("matruyen"));
            edttentruyen.setText(intent.getStringExtra("tentruyen"));
            edtnoidung.setText(intent.getStringExtra("noidung"));
            int sc = intent.getIntExtra("sochuong",0);
            edtsochuong.setText(""+sc);
            String img = intent.getStringExtra("anhbia");
            imgdaidien.setImageBitmap(ConvertHelper.StringToBitMap(img));

            edtluotlike.setText(intent.getIntExtra("luotlike", 0)+"");
            edtluotxem.setText(intent.getIntExtra("luotxem",0)+"");
        }else if(EventCode == 2){
            fabAdd.setVisibility(View.INVISIBLE);
            edtmatruyen.setEnabled(false);

            edtmatruyen.setText(intent.getStringExtra("matruyen"));
            edttentruyen.setText(intent.getStringExtra("tentruyen"));
            edtnoidung.setText(intent.getStringExtra("noidung"));
            int sc = intent.getIntExtra("sochuong",0);
            edtsochuong.setText(""+sc);
            String img = intent.getStringExtra("anhbia");
            imgdaidien.setImageBitmap(ConvertHelper.StringToBitMap(img));

            edtluotlike.setText(intent.getIntExtra("luotlike", 0)+"");
            edtluotxem.setText(intent.getIntExtra("luotxem",0)+"");
        } else if (EventCode == 1) {
            fabAdd.setVisibility(View.INVISIBLE);
        }

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtmatruyen.setEnabled(true);
                edttentruyen.setEnabled(true);
                edtluotlike.setEnabled(true);
                edtluotxem.setEnabled(true);
                edtnoidung.setEnabled(true);
                sptheloai.setEnabled(true);
                spnxb.setEnabled(true);
                sptacgia.setEnabled(true);
                btnluu.setVisibility(View.VISIBLE);
                btnpickimg.setVisibility(View.VISIBLE);
                fabAdd.setVisibility(View.INVISIBLE);
            }
        });

        btnpickimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EventCode == 1){
                    String ma = edtmatruyen.getText().toString();
                    String ten = edttentruyen.getText().toString();
                    String noidung = edtnoidung.getText().toString();
                    String img = ConvertHelper.BitMapToString(((BitmapDrawable)imgdaidien.getDrawable()).getBitmap());
                    int like = Integer.parseInt(edtluotlike.getText().toString());
                    int view = Integer.parseInt(edtluotxem.getText().toString());
//                    String matg = listTacGia
                    int positionTacGia = sptacgia.getSelectedItemPosition();
                    int positionNxb = spnxb.getSelectedItemPosition();
                    String matg = listTacGia.get(positionTacGia).getId();
                    String manxb = listNxb.get(positionNxb).getManxb();

                    SQLiteDatabase db = dbh.getWritableDatabase();
                    ContentValues myvalue = new ContentValues();
                    myvalue.put("matruyen",ma);
                    myvalue.put("tentruyen",ten);
                    myvalue.put("noidung",noidung);
                    myvalue.put("imgdaidien",img);
                    myvalue.put("luotlike",like);
                    myvalue.put("luotxem",view);
                    myvalue.put("matacgia",matg);
                    myvalue.put("manxb",manxb);
                    db.insert("stories",null,myvalue);

                    ContentValues storyTypeValue = new ContentValues();
                    for (ListItem item: getSelectedTheLoaiList) {
                        storyTypeValue.put("matl",item.getType().getMatl());
                        storyTypeValue.put("matruyen", ma);
                        db.insert("typestory",null,storyTypeValue);
                    }
                    setResult(33);
                    finish();
                }else if(EventCode == 2 || EventCode == 0){
                    String ma = edtmatruyen.getText().toString();
                    String ten = edttentruyen.getText().toString();
                    String noidung = edtnoidung.getText().toString();
                    String img = ConvertHelper.BitMapToString(((BitmapDrawable)imgdaidien.getDrawable()).getBitmap());
                    int like = Integer.parseInt(edtluotlike.getText().toString());
                    int view = Integer.parseInt(edtluotxem.getText().toString());
//                    String matg = listTacGia
                    int positionTacGia = sptacgia.getSelectedItemPosition();
                    int positionNxb = spnxb.getSelectedItemPosition();
                    String matg = listTacGia.get(positionTacGia).getId();
                    String manxb = listNxb.get(positionNxb).getManxb();

                    SQLiteDatabase db = dbh.getWritableDatabase();
                    ContentValues myvalue = new ContentValues();
//                    myvalue.put("matruyen",ma);
                    myvalue.put("tentruyen",ten);
                    myvalue.put("noidung",noidung);
                    myvalue.put("imgdaidien",img);
                    myvalue.put("luotlike",like);
                    myvalue.put("luotxem",view);
                    myvalue.put("matacgia",matg);
                    myvalue.put("manxb",manxb);
                    db.update("stories",myvalue, "matruyen = ?", new String[]{ma});

                    ContentValues storyTypeValue = new ContentValues();
                    if(getSelectedTheLoaiList.size() != 0){
                        db.delete("typestory", "matruyen = ?",new String[]{ma});
                        for (ListItem item: getSelectedTheLoaiList) {
                            storyTypeValue.put("matl",item.getType().getMatl());
                            storyTypeValue.put("matruyen", ma);
                            db.insert("typestory",null,storyTypeValue);
                        }
                    }
                    setResult(34);
                    finish();
                }
            }
        });

        adapterTheLoai.setOnItemSelectedListener(new CustomTheLoaiMultiSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(List<ListItem> selectedItems, int pos) {
                if (selectedItems == null || selectedItems.isEmpty()) {
                    Log.d("multiple spinner", "nothing");
                } else {
                    StringBuilder selectedNames = new StringBuilder();
                    for (ListItem item : selectedItems) {
                        selectedNames.append(item.getType().getTentl()).append(", ");
                    }
//                     Remove the trailing comma and space
                        selectedNames.delete(selectedNames.length() - 2, selectedNames.length());
//                        name.setText(selectedNames.toString());
                }

                getSelectedTheLoaiList = selectedItems;
                Log.e("getSelectedItems", selectedItems.get(pos).getType().getTentl());
                Log.e("getSelectedItems", String.valueOf(selectedItems.size()));
            }
        });
    }

    public void initTacgiaSpinner(){
        SQLiteDatabase db = dbh.getReadableDatabase();
        String sql = "select * from authors";
        Cursor cs = db.rawQuery(sql,null);
        listStrTacGia = new ArrayList<>();
        listTacGia = new ArrayList<>();
        while(cs.moveToNext()){
            Author newauthor = new Author(cs.getString(0),cs.getString(1),cs.getString(2),cs.getString(3));
            listTacGia.add(newauthor);
            listStrTacGia.add(newauthor.getName());
            adapterTacGia = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listStrTacGia);
            sptacgia.setAdapter(adapterTacGia);
        }
    }

    public void initNxbSpinner(){
        SQLiteDatabase db = dbh.getReadableDatabase();
        String sql = "select * from publisher";
        Cursor cs = db.rawQuery(sql,null);
        listStrNxb = new ArrayList<>();
        listNxb = new ArrayList<>();
        try{
            while(cs.moveToNext()){
                Publisher newnxb = new Publisher(cs.getString(0),cs.getString(1),cs.getString(2),cs.getString(3));
                listNxb.add(newnxb);
                listStrNxb.add(newnxb.getTennxb());
                adapterNxb = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listStrNxb);
                spnxb.setAdapter(adapterNxb);
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void initTheloaiSpinner(){
        SQLiteDatabase db = dbh.getReadableDatabase();
        String sql = "select * from types";
        Cursor cs = db.rawQuery(sql,null);
        listTheLoai = new ArrayList<>();
        try{
            while(cs.moveToNext()){
                Type newtype = new Type(cs.getString(0), cs.getString(1), cs.getString(2) );
                listTheLoai.add(newtype);
                spinnerListItem.add(new ListItem(newtype));
            }
            selectedItem.clear();
            adapterTheLoai = new CustomTheLoaiMultiSpinner(this,spinnerListItem, selectedItem);
            sptheloai.setAdapter(adapterTheLoai);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void initView(){
        listTheLoai = new ArrayList<>();
        listTacGia = new ArrayList<>();
        listNxb = new ArrayList<>();
        edtmatruyen = findViewById(R.id.edtmatruyen);
        edttentruyen= findViewById(R.id.edttentruyen);
        edtnoidung = findViewById(R.id.edtnoidung);
        edtsochuong = findViewById(R.id.edtsochuong);
        edtluotlike = findViewById(R.id.edtluotlike);
        edtluotxem = findViewById(R.id.edtluotxem);
        toolbar = findViewById(R.id.toolbar3);
        imgdaidien = findViewById(R.id.imgstorydetails);

        spnxb = findViewById(R.id.sppublisherinstorydetails);
        sptacgia = findViewById(R.id.spauthorinstorydetails);
        sptheloai = findViewById(R.id.sptypeinstorydetails);

        btnluu = findViewById(R.id.btnluustoru);
        btnpickimg = findViewById(R.id.btnpickimgstory);

        rotateOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.to_bottom_anim);


        fabAdd = findViewById(R.id.fab_story_expand);
        fabEdit = findViewById(R.id.fab_story_edit);
        fabGallery = findViewById(R.id.fab_story_gallery);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = !clicked;
                if(clicked){
                    fabEdit.setVisibility(View.VISIBLE);
                    fabGallery.setVisibility(View.VISIBLE);

                    fabEdit.startAnimation(fromBottom);
                    fabGallery.startAnimation(fromBottom);
                    fabAdd.startAnimation(rotateOpen);
                }else{
                    fabEdit.setVisibility(View.INVISIBLE);
                    fabGallery.setVisibility(View.INVISIBLE);

                    fabEdit.startAnimation(toBottom);
                    fabGallery.startAnimation(toBottom);
                    fabAdd.startAnimation(rotateClose);
                }

            }
        });
    }

    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == PICK_PHOTO_FOR_AVATAR) && (resultCode == Activity.RESULT_OK)) {
            if (data == null) {
                //Display an error
                Log.d("IMAGENULL", "NO IMG");
                return;
            }
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap selectedImage = BitmapFactory.decodeStream(inputStream);
                imgdaidien.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                Log.d("IMAGENULL", "NO IMG");
                throw new RuntimeException(e);
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }
    }
}