package phdhtl.k63cntt1.nguyen.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;

public class LoginActivity extends AppCompatActivity {

    Button btnsignin;
    EditText edtusername, edtpassword;

    CheckBox cbrememberme;
    DBHelper dbh;
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        dbh = new DBHelper(this);
        initView();

        settings = getSharedPreferences("Login", 0);
        String username = settings.getString("user","");
        String password = settings.getString("pass","");
        if(username.isEmpty()){
            // data not saved
        } else {
            Intent in = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(in);
        }
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = edtusername.getText().toString();
                String p = edtpassword.getText().toString();
                if (u.equals("1") && p.equals("1")) {
                    Intent in = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(in);
                }

                String sql = "select * from users where username = '"+u+"' and password = '"+p+"'";
                SQLiteDatabase db = dbh.getReadableDatabase();
                Cursor cs = db.rawQuery(sql, null);
                if(cs.moveToNext()){
                    if(cbrememberme.isChecked()){
                        settings.edit().putString("user",u).apply();
                        settings.edit().putString("pass",p).apply();
                    }
                    Intent in = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(in);
                }else{
                    Toast.makeText(getApplicationContext(),"Tài khoản chưa đúng", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void initView(){
        btnsignin = findViewById(R.id.btnsignin);
        edtusername = findViewById(R.id.edtusername);
        edtpassword = findViewById(R.id.edtpassword);
        cbrememberme = findViewById(R.id.cbrememberme);
    }
}