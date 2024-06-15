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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;

public class UserDetailsActivity extends AppCompatActivity {

    private static final int PICK_PHOTO_FOR_AVATAR = 1;
    ImageView imgdaidien;
    EditText edtusername, edtemail, edtpassword,edtiduser;
    Button btnluu, btnpickimg;
    FloatingActionButton fabEditUser;
    Toolbar toolbar;
    Spinner splevel;

    ArrayList<String> sparr;
    ArrayAdapter<String> arrayAdapter;
    int levelvalue;
    Intent intent;
    int EventCode;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        initView();
        dbh = new DBHelper(this);
        intent = getIntent();
        EventCode = intent.getIntExtra("EventCode", 0);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(EventCode == 0){
            edtiduser.setEnabled(false);
            edtusername.setEnabled(false);
            edtemail.setEnabled(false);
            edtpassword.setEnabled(false);
            splevel.setEnabled(false);
            btnluu.setVisibility(View.INVISIBLE);
            btnpickimg.setVisibility(View.INVISIBLE);
            fabEditUser.setVisibility(View.VISIBLE);

            String ma = intent.getStringExtra("mauser");
            String username = intent.getStringExtra("username");
            String email= intent.getStringExtra("email");
            String password = intent.getStringExtra("password");
            String img = intent.getStringExtra("imgdaidien");
            int level = intent.getIntExtra("level",0);

            edtiduser.setText(ma);
            edtusername.setText(username);
            edtemail.setText(email);
            edtpassword.setText(password);
            imgdaidien.setImageBitmap(ConvertHelper.StringToBitMap(img));
            splevel.setSelection(level);

        } else if (EventCode == 1) {
            fabEditUser.setVisibility(View.INVISIBLE);
        } else if (EventCode == 2) {
            fabEditUser.setVisibility(View.INVISIBLE);
            edtiduser.setEnabled(false);
            String ma = intent.getStringExtra("mauser");
            String username = intent.getStringExtra("username");
            String email= intent.getStringExtra("email");
            String password = intent.getStringExtra("password");
            String img = intent.getStringExtra("imgdaidien");
            int level = intent.getIntExtra("level",0);

            edtiduser.setText(ma);
            edtusername.setText(username);
            edtemail.setText(email);
            edtpassword.setText(password);
            imgdaidien.setImageBitmap(ConvertHelper.StringToBitMap(img));
            splevel.setSelection(level);
        }
        splevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                levelvalue = sparr.get(position) == "Admin" ? 1 : 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fabEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtiduser.setEnabled(true);
                edtusername.setEnabled(true);
                edtemail.setEnabled(true);
                edtpassword.setEnabled(true);
                splevel.setEnabled(true);
                btnluu.setVisibility(View.VISIBLE);
                btnpickimg.setVisibility(View.VISIBLE);
                fabEditUser.setVisibility(View.INVISIBLE);
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
                    String ma = edtiduser.getText().toString();
                    String username = edtusername.getText().toString();
                    String email = edtemail.getText().toString();
                    String password = edtpassword.getText().toString();
                    int level = splevel.getSelectedItem().toString() == "Client" ? 0 : 1;
                    String img = ConvertHelper.BitMapToString( ((BitmapDrawable)imgdaidien.getDrawable()).getBitmap() );
//                    myIntent.putExtra("manxb", ma);
//                    myIntent.putExtra("tennxb", ten);
//                    myIntent.putExtra("gthnxb", gth);
//                    myIntent.putExtra("imgnxb", img);
                    setResult(33, myIntent);

                    SQLiteDatabase db = dbh.getWritableDatabase();
                    String sqlInsertNXB = "insert into users values('"+ma+"', '"+username+"', '"+email+"', '"+password+"', '"+img+"', "+level+")";
                    db.execSQL(sqlInsertNXB);
                    finish();
                }else if(EventCode == 2 || EventCode == 0){
                    Intent myIntent = getIntent();
                    String ma = edtiduser.getText().toString();
                    String username = edtusername.getText().toString();
                    String email = edtemail.getText().toString();
                    String password = edtpassword.getText().toString();
                    int level = splevel.getSelectedItem().toString() == "Client" ? 0 : 1;
                    String img = ConvertHelper.BitMapToString( ((BitmapDrawable)imgdaidien.getDrawable()).getBitmap() );
                    setResult(34, myIntent);
                    ContentValues myvalue = new ContentValues();
                    myvalue.put("mauser",ma);
                    myvalue.put("username",username);
                    myvalue.put("email",email);
                    myvalue.put("password",password);
                    myvalue.put("imgdaidien",img);
                    myvalue.put("level",level);
                    SQLiteDatabase db = dbh.getWritableDatabase();
                    int n = db.update("users", myvalue, "mauser = ?", new String[]{ma});
                    String msg = "";
                    if(n == 0){
                        msg = "no record was updated!";
                    }else{
                        msg = n + " record was updated";
                    }
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }


    public void initView(){
        imgdaidien = findViewById(R.id.imgdaidienuser);
        edtiduser = findViewById(R.id.edtiduserdetails);
        edtusername = findViewById(R.id.edtusernamedetails);
        edtemail = findViewById(R.id.edtemaildetails);
        edtpassword = findViewById(R.id.edtpassworddetails);
        splevel = findViewById(R.id.spinneruser);
        sparr = new ArrayList<>();
        sparr.add("Client");
        sparr.add("Admin");
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sparr);
        splevel.setAdapter(arrayAdapter);
        toolbar = findViewById(R.id.toolbaruserdetails);

        btnluu = findViewById(R.id.btnluuuser);
        btnpickimg = findViewById(R.id.btnpicimguser);
        fabEditUser = findViewById(R.id.fab_edit_user);
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