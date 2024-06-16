package phdhtl.k63cntt1.nguyen.detailsActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;

public class StoryTypeDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView edtmatldetails, edttentldetails, edtndtldetails;
    Button btnluu;
    FloatingActionButton fabEditStoryType;
    DBHelper dbh;
    int EventCode;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_type_details);

        intent = getIntent();
        dbh = new DBHelper(this);
        initView();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        EventCode = intent.getIntExtra("EventCode",0);
        if(EventCode == 0){
            edtmatldetails.setEnabled(false);
            edttentldetails.setEnabled(false);
            edtndtldetails.setEnabled(false);
            btnluu.setVisibility(View.INVISIBLE);
            fabEditStoryType.setVisibility(View.VISIBLE);

            String matl = intent.getStringExtra("matl");
            String tentl = intent.getStringExtra("tentl");
            String ndtl = intent.getStringExtra("ndtl");
            edtndtldetails.setText(ndtl);
            edtmatldetails.setText(matl);
            edttentldetails.setText(tentl);

        } else if (EventCode == 1) {
            edtmatldetails.setEnabled(true);
            edttentldetails.setEnabled(true);
            edtndtldetails.setEnabled(true);
            btnluu.setVisibility(View.VISIBLE);
            fabEditStoryType.setVisibility(View.INVISIBLE);
        }else if( EventCode == 2){
            edtmatldetails.setEnabled(false);
            edttentldetails.setEnabled(true);
            edtndtldetails.setEnabled(true);
            btnluu.setVisibility(View.VISIBLE);
            fabEditStoryType.setVisibility(View.INVISIBLE);

            String matl = intent.getStringExtra("matl");
            String tentl = intent.getStringExtra("tentl");
            String ndtl = intent.getStringExtra("ndtl");
            edtndtldetails.setText(ndtl);
            edtmatldetails.setText(ndtl);
            edttentldetails.setText(tentl);
        }

        fabEditStoryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtmatldetails.setEnabled(false);
                edttentldetails.setEnabled(true);
                edtndtldetails.setEnabled(true);
                btnluu.setVisibility(View.VISIBLE);
                fabEditStoryType.setVisibility(View.INVISIBLE);
            }
        });
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EventCode == 1){
                    String tentl = edttentldetails.getText().toString();
                    String matl = edtmatldetails.getText().toString();
                    String ndtl = edtndtldetails.getText().toString();

                    SQLiteDatabase db = dbh.getWritableDatabase();
                    ContentValues myvalue = new ContentValues();
                    myvalue.put("matl",matl);
                    myvalue.put("tentl",tentl);
                    myvalue.put("noidungtl",ndtl);
                    int n = (int) db.insert("types",null,myvalue);
                    String msg = "";
                    if(n==0){
                        msg = "no record was inserted!";
                    }else {
                        msg = n + " record was inserted";
                    }
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    setResult(33);
                    finish();
                }else if(EventCode == 2 | EventCode == 0){
                    String tentl = edttentldetails.getText().toString();
                    String matl = edtmatldetails.getText().toString();
                    String ndtl = edtndtldetails.getText().toString();

                    SQLiteDatabase db = dbh.getWritableDatabase();
                    ContentValues myvalue = new ContentValues();
                    myvalue.put("matl",matl);
                    myvalue.put("tentl",tentl);
                    myvalue.put("noidungtl",ndtl);
                    int n = (int) db.update("types",myvalue, "matl = ?", new String[]{matl});
                    String msg = "";
                    if(n==0){
                        msg = "no record was updated!";
                    }else {
                        msg = n + " record was updated";
                    }
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    setResult(33);
                    finish();
                }
            }
        });
    }

    public void initView(){
        edtmatldetails = findViewById(R.id.edtmatldetails);
        edttentldetails = findViewById(R.id.edttentldetails);
        edtndtldetails = findViewById(R.id.edtndtldetails);

        btnluu = findViewById(R.id.btnluutl);
        fabEditStoryType = findViewById(R.id.fab_edit_story_type);
        toolbar = findViewById(R.id.toolbarstorytypdetails);
    }
}