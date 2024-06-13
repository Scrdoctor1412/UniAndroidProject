package phdhtl.k63cntt1.nguyen;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import phdhtl.k63cntt1.nguyen.adapter.UserAdapter;
import phdhtl.k63cntt1.nguyen.model.User;

public class UserActivity extends AppCompatActivity {
    ListView lvUser;
    UserAdapter userAdapter;
    ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initDummyUserData();
        initView();

    }

    private void initView() {
        lvUser = findViewById(R.id.lv_user);
        userAdapter = new UserAdapter(this, userArrayList);
        lvUser.setAdapter(userAdapter);
    }

    private void initDummyUserData() {
        userArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            userArrayList.add(new User());
        }
    }
}