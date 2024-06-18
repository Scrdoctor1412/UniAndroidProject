package phdhtl.k63cntt1.nguyen.detailsActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;

public class ChapterDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText edtmachuong, edtchuongso, edttenchuong;
    Button btnluu;

    int EventCode;
    Intent intent;
    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_details);

        dbh = new DBHelper(this);
        intent = getIntent();
        initView();

        EventCode = intent.getIntExtra("EventCode",0);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(EventCode == 2){
            edtmachuong.setEnabled(false);

            String ma = intent.getStringExtra("machuong");
            String ten = intent.getStringExtra("chuongten");
            int so = intent.getIntExtra("chuongso",0);
            edttenchuong.setText(ten);
            edtchuongso.setText(so+"");
            edtmachuong.setText(ma);
        }
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EventCode == 1){
                    SQLiteDatabase db = dbh.getWritableDatabase();
                    ContentValues myvalue = new ContentValues();
                    myvalue.put("machuong", edtmachuong.getText().toString());
                    myvalue.put("chuongso", Integer.parseInt(edtchuongso.getText().toString()));
                    myvalue.put("chuongten", edttenchuong.getText().toString());
                    String matruyen = intent.getStringExtra("matruyen");
                    myvalue.put("matruyen", matruyen);
                    int n = (int) db.insert("chapters",null, myvalue);
                    String msg = "";
                    if(n==0){
                        msg = "no record was inserted";
                    }else{
                        msg = n + " record was inserted";
                    }
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    setResult(33);
                    finish();
                }else if(EventCode == 2){
                    SQLiteDatabase db = dbh.getWritableDatabase();
                    ContentValues myvalue = new ContentValues();
                    myvalue.put("chuongso", Integer.parseInt(edtchuongso.getText().toString()));
                    myvalue.put("chuongten", edttenchuong.getText().toString());
                    int n = db.update("chapters",myvalue,"machuong = ?", new String[]{edtmachuong.getText().toString()});
                    String msg = "";
                    if(n==0){
                        msg = "no record was updated";
                    }else{
                        msg = n + " record was updated";
                    }
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    setResult(34);
                    finish();
                }
            }
        });

    }

    public void initView(){
        toolbar = findViewById(R.id.toolbarchapterdetails);
        edtmachuong = findViewById(R.id.edtmachuong);
        edtchuongso = findViewById(R.id.edtchuongso);
        edttenchuong = findViewById(R.id.edttenchuong);

        btnluu = findViewById(R.id.btnluuchapterdetails);
    }

}