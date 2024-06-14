package phdhtl.k63cntt1.nguyen.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import phdhtl.k63cntt1.nguyen.R;

public class LoginActivity extends AppCompatActivity {

    Button btnsignin;
    EditText edtusername, edtpassword;

    CheckBox cbrememberme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initView();

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
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