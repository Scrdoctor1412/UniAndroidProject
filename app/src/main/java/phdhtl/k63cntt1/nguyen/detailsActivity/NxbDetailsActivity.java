package phdhtl.k63cntt1.nguyen.detailsActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;

public class NxbDetailsActivity extends AppCompatActivity {

    private static final int PICK_PHOTO_FOR_AVATAR = 90;
    Toolbar toolbar;
    ImageView imgdaidien;
    EditText edttennxb, edtmanxb, edtgthnxb;

    Button btnluu, btnpickimg;

    FloatingActionButton fabEditNxb;
    String imgText;
    int EventCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nxb_details);

        initView();

        Intent intent = getIntent();
        EventCode = intent.getIntExtra("EventCode", 0);
        if(EventCode == 1){
            edtmanxb.setEnabled(true);
            fabEditNxb.setVisibility(View.INVISIBLE);
            btnpickimg.setVisibility(View.VISIBLE);
            btnluu.setVisibility(View.VISIBLE);
        }else if(EventCode == 2){
            edtmanxb.setEnabled(false);
            fabEditNxb.setVisibility(View.INVISIBLE);
            String ma = intent.getStringExtra("manxb");
            String ten = intent.getStringExtra("tennxb");
            String gth = intent.getStringExtra("gthnxb");
            String img = intent.getStringExtra("imgnxb");

            edtmanxb.setText(ma);
            edtgthnxb.setText(gth);
            edttennxb.setText(ten);
            imgdaidien.setImageBitmap(ConvertHelper.StringToBitMap(img));

        }else if(EventCode == 0){
            edtmanxb.setEnabled(false);
            edttennxb.setEnabled(false);
            edtgthnxb.setEnabled(false);
            btnpickimg.setVisibility(View.INVISIBLE);
            btnluu.setVisibility(View.INVISIBLE);
            fabEditNxb.setVisibility(View.VISIBLE);
            String ma = intent.getStringExtra("manxb");
            String ten = intent.getStringExtra("tennxb");
            String gth = intent.getStringExtra("gthnxb");
            String img = intent.getStringExtra("imgnxb");

            edtmanxb.setText(ma);
            edtgthnxb.setText(gth);
            edttennxb.setText(ten);
            imgdaidien.setImageBitmap(ConvertHelper.StringToBitMap(img));
        }
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
        //Toolbar setup code
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Database helper
        DBHelper dbh = new DBHelper(this);

        fabEditNxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtmanxb.setEnabled(true);
                edttennxb.setEnabled(true);
                edtgthnxb.setEnabled(true);
                btnluu.setVisibility(View.VISIBLE);
                btnpickimg.setVisibility(View.VISIBLE);
            }
        });

        //Button picking image logic
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
                    Intent myIntent = getIntent();
                    String ma = edtmanxb.getText().toString();
                    String ten = edttennxb.getText().toString();
                    String gth = edtgthnxb.getText().toString();
                    String img = ConvertHelper.BitMapToString( ((BitmapDrawable)imgdaidien.getDrawable()).getBitmap() );
                    myIntent.putExtra("manxb", ma);
                    myIntent.putExtra("tennxb", ten);
                    myIntent.putExtra("gthnxb", gth);
                    myIntent.putExtra("imgnxb", img);
                    setResult(33, myIntent);

                    SQLiteDatabase db = dbh.getWritableDatabase();
                    String sqlInsertNXB = "insert into publisher values('"+ma+"', '"+ten+"', '"+gth+"', '"+img+"')";
                    db.execSQL(sqlInsertNXB);
                    finish();
                }else if(EventCode == 2){
                    Intent myIntent = getIntent();
                    String ma = edtmanxb.getText().toString();
                    String ten = edttennxb.getText().toString();
                    String gth = edtgthnxb.getText().toString();
                    String img = ConvertHelper.BitMapToString( ((BitmapDrawable)imgdaidien.getDrawable()).getBitmap() );
                    ContentValues myvalue = new ContentValues();
                    myvalue.put("manxb",ma);
                    myvalue.put("tennxb", ten);
                    myvalue.put("gioithieu", gth);
                    myvalue.put("imgdaidien", img);
                    setResult(34, myIntent);

                    SQLiteDatabase db = dbh.getWritableDatabase();
                    int n = db.update("publisher", myvalue, "manxb = ?", new String[]{ma});
                    String msg = "";
                    if(n==0){
                        msg = "No record was updated!";
                    }else{
                        msg = n + " record was updated!";
                    }
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public void initView(){
        toolbar = findViewById(R.id.toolbarnxbdetails);
        imgdaidien = findViewById(R.id.imgdaidiennxb2);
        edtmanxb = findViewById(R.id.editmanxb);
        edttennxb = findViewById(R.id.edttennxb2);
        edtgthnxb = findViewById(R.id.edtgthnxb);
        btnluu = findViewById(R.id.btnluunxb);
        btnpickimg = findViewById(R.id.btnpickimgnxb);
        fabEditNxb = findViewById(R.id.fab_edit_nxb);
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
                imgText = ConvertHelper.BitMapToString(selectedImage);
                imgdaidien.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                Log.d("IMAGENULL", "NO IMG");
                throw new RuntimeException(e);
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }
    }


}