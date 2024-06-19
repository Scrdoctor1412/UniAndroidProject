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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;

public class AuthorDetailsActivity extends AppCompatActivity {

    private static final int PICK_PHOTO_FOR_AVATAR = 1;
    ImageView imgdaidien;
    EditText edtmatg, edttentg, edtgthtg;
    Button btnluu, btnpickimg;
    FloatingActionButton fabEditTg;
    Toolbar toolbar;
    int EventCode;

    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_author_details);

        initView();

        dbh = new DBHelper(getApplicationContext());

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        EventCode = intent.getIntExtra("EventCode",0);
        if(EventCode == 0){
            Toast.makeText(getApplicationContext(),EventCode+"",Toast.LENGTH_LONG).show();
            edtgthtg.setEnabled(false);
            edttentg.setEnabled(false);
            edtmatg.setEnabled(false);
            btnluu.setVisibility(View.INVISIBLE);
            btnpickimg.setVisibility(View.INVISIBLE);
            fabEditTg.setVisibility(View.VISIBLE);

            String ma = intent.getStringExtra("matg");
            String ten = intent.getStringExtra("tentg");
            String gth = intent.getStringExtra("gthtg");
            String img = intent.getStringExtra("imgtg");

            edtmatg.setText(ma);
            edttentg.setText(ten);
            edtgthtg.setText(gth);
            imgdaidien.setImageBitmap(ConvertHelper.StringToBitMap(img));
        }else if(EventCode == 1){
            Toast.makeText(getApplicationContext(),EventCode+"",Toast.LENGTH_LONG).show();
            edtgthtg.setEnabled(true);
            edttentg.setEnabled(true);
            edtmatg.setEnabled(true);
            btnluu.setVisibility(View.VISIBLE);
            btnpickimg.setVisibility(View.VISIBLE);
            fabEditTg.setVisibility(View.INVISIBLE);
        } else if(EventCode == 2) {
            edtmatg.setEnabled(false);
            edtgthtg.setEnabled(true);
            edttentg.setEnabled(true);
            String ma = intent.getStringExtra("matg");
            String ten = intent.getStringExtra("tentg");
            String gth = intent.getStringExtra("gthtg");
            String img = intent.getStringExtra("imgtg");

            edtmatg.setText(ma);
            edttentg.setText(ten);
            edtgthtg.setText(gth);
            imgdaidien.setImageBitmap(ConvertHelper.StringToBitMap(img));
        }

        fabEditTg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtmatg.setEnabled(false);
                edtgthtg.setEnabled(true);
                edttentg.setEnabled(true);
                btnluu.setVisibility(View.VISIBLE);
                btnpickimg.setVisibility(View.VISIBLE);
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
                    Intent myIntent = getIntent();
                    String ma = edtmatg.getText().toString();
                    String ten = edttentg.getText().toString();
                    String gth = edtgthtg.getText().toString();
                    String img = ConvertHelper.BitMapToString( ((BitmapDrawable)imgdaidien.getDrawable()).getBitmap() );
                    myIntent.putExtra("man", ma);
                    myIntent.putExtra("tennxb", ten);
                    myIntent.putExtra("gthnxb", gth);
                    myIntent.putExtra("imgnxb", img);
                    setResult(33, myIntent);

                    SQLiteDatabase db = dbh.getWritableDatabase();
                    String sqlInsertAuthor = "insert into authors values('"+ma+"', '"+ten+"', '"+gth+"', '"+img+"')";
                    db.execSQL(sqlInsertAuthor);
                    finish();
                }else if(EventCode == 2 || EventCode == 0){
                    Intent myIntent = getIntent();
                    String ma = edtmatg.getText().toString();
                    String ten = edttentg.getText().toString();
                    String gth = edtgthtg.getText().toString();
                    String img = ConvertHelper.BitMapToString( ((BitmapDrawable)imgdaidien.getDrawable()).getBitmap() );
                    ContentValues myvalue = new ContentValues();
                    myvalue.put("matacgia",ma);
                    myvalue.put("tentacgia", ten);
                    myvalue.put("gioithieu", gth);
                    myvalue.put("imgdaidien", img);
                    setResult(34, myIntent);

                    SQLiteDatabase db = dbh.getWritableDatabase();
                    int n = db.update("authors", myvalue, "matacgia = ?", new String[]{ma});
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
        imgdaidien = findViewById(R.id.imgdaidientg);
        edtgthtg = findViewById(R.id.edtgthtg);
        edttentg = findViewById(R.id.edttentg);
        edtmatg = findViewById(R.id.edtmatg);
        toolbar = findViewById(R.id.toolbarauthordetails);

        btnpickimg = findViewById(R.id.btnpickimgtg);
        btnluu = findViewById(R.id.btnluutg);
        fabEditTg = findViewById(R.id.fab_edit_tg);
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