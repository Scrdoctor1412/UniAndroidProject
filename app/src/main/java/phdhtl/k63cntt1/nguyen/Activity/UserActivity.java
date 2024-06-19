package phdhtl.k63cntt1.nguyen.Activity;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.adapter.CustomUserAdapter;
import phdhtl.k63cntt1.nguyen.detailsActivity.UserDetailsActivity;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.User;

public class UserActivity extends AppCompatActivity {
    ListView lvUser;
    CustomUserAdapter customUserAdapter;
    ArrayList<User> userArrayList;
    Toolbar toolbar;

    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

//        initDummyUserData();
        initView();
        dbh = new DBHelper(this);

//        userArrayList.add(new User());
        showList();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(getApplicationContext(), UserDetailsActivity.class);
                myIntent.putExtra("EventCode",0);
                myIntent.putExtra("mauser", userArrayList.get(position).getId());
                myIntent.putExtra("username", userArrayList.get(position).getUserName());
                myIntent.putExtra("email", userArrayList.get(position).getEmail());
                myIntent.putExtra("password", userArrayList.get(position).getPassWord());
                myIntent.putExtra("imgdaidien", userArrayList.get(position).getImgdaidien());
                myIntent.putExtra("level", userArrayList.get(position).getLevel());

                startActivityForResult(myIntent,99);
            }
        });
    }

    public void showList(){
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from users", null);
        userArrayList.clear();
//        cs.moveToNext();
        try{
            while(cs.moveToNext()){
                User newuser = new User(cs.getString(0),cs.getString(1),cs.getString(2),cs.getString(3), cs.getString(4), cs.getInt(5));
                userArrayList.add(newuser);
                customUserAdapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            Log.d("user db error", e.getMessage());
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
            Intent myIntent = new Intent(getApplicationContext(), UserDetailsActivity.class);
            myIntent.putExtra("EventCode",1);
            startActivityForResult(myIntent, 99);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        userArrayList = new ArrayList<>();
        lvUser = findViewById(R.id.lv_user);
        customUserAdapter = new CustomUserAdapter(this,R.layout.custom_user_row ,userArrayList);
        lvUser.setAdapter(customUserAdapter);
        toolbar = findViewById(R.id.toolbaruser);
    }

    private void initDummyUserData() {
        userArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            userArrayList.add(new User());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 99 && resultCode == 33){
            showList();
        } else if (requestCode == 99 && resultCode == 34) {
            showList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}