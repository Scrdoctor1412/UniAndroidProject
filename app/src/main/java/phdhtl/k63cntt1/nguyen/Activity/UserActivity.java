package phdhtl.k63cntt1.nguyen.Activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.adapter.UserAdapter;
import phdhtl.k63cntt1.nguyen.model.User;

public class UserActivity extends AppCompatActivity {
    ListView lvUser;
    UserAdapter userAdapter;
    ArrayList<User> userArrayList;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

//        initDummyUserData();
        initView();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void initView() {
        userArrayList = new ArrayList<>();
        lvUser = findViewById(R.id.lv_user);
        userAdapter = new UserAdapter(this,R.layout.custom_user_row ,userArrayList);
        lvUser.setAdapter(userAdapter);
        toolbar = findViewById(R.id.toolbaruser);
    }

    private void initDummyUserData() {
        userArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            userArrayList.add(new User());
        }
    }
}