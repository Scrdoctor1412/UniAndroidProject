package phdhtl.k63cntt1.nguyen.detailsActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;

public class ChapterImageDetailsActivity extends AppCompatActivity {
    private static final int PICK_PHOTO_FOR_AVATAR = 90;
    Toolbar toolbar;
    Button btnpickimg, btnluu;
    ImageView imgchapter;
    Intent intent;
    DBHelper dbh;
    int EventCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_image_details);

        intent = getIntent();
        dbh = new DBHelper(this);
        EventCode = intent.getIntExtra("EventCode", 0);
        initView();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(EventCode == 2){
            String img = intent.getStringExtra("img");
            imgchapter.setImageBitmap(ConvertHelper.StringToBitMap(img));
        }
        btnpickimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EventCode == 1) {
                    SQLiteDatabase db = dbh.getWritableDatabase();
                    String img = ConvertHelper.BitMapToString(((BitmapDrawable)imgchapter.getDrawable()).getBitmap());
                    String machuong = intent.getStringExtra("machuong");
                    String sql = "insert into chapterimage values(NULL, '"+img+"', '"+machuong+"')";
                    try{
                        db.execSQL(sql);
                        setResult(33);
                        finish();
                        Toast.makeText(getApplicationContext(), "inserted successfully!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else if (EventCode == 2) {
                    SQLiteDatabase db = dbh.getWritableDatabase();
                    String img = ConvertHelper.BitMapToString(((BitmapDrawable)imgchapter.getDrawable()).getBitmap());
                    String mahinhanhchuong = intent.getStringExtra("mahinhanhchuong");
                    try{
                        ContentValues myvalue = new ContentValues();
                        myvalue.put("hinhanh", img);
                        db.update("chapterimage", myvalue, "mahinhanhchuong = ?", new  String[]{mahinhanhchuong});
                        setResult(33);
                        finish();
                        Toast.makeText(getApplicationContext(), "updated successfully!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    public void initView(){
        toolbar = findViewById(R.id.toolbarimgchapterdetails);
        btnluu = findViewById(R.id.btnluuimgchapter);
        btnpickimg = findViewById(R.id.btnpickimgchapter);
        imgchapter = findViewById(R.id.imgchapterdetails);
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
                imgchapter.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                Log.d("IMAGENULL", "NO IMG");
                throw new RuntimeException(e);
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }
    }
}