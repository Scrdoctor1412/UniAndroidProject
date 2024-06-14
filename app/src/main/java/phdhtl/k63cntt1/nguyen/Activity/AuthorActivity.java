package phdhtl.k63cntt1.nguyen.Activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.adapter.AuthorAdapter;
import phdhtl.k63cntt1.nguyen.model.Author;
import phdhtl.k63cntt1.nguyen.model.User;

public class AuthorActivity extends AppCompatActivity {
    private ListView lvAuthor;
    private AuthorAdapter authorAdapter;
    private ArrayList<Author> authorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_author);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initDummyAuthorData();
        initView();

    }

    private void initView() {
        lvAuthor = findViewById(R.id.lv_author);
        authorAdapter = new AuthorAdapter(this, authorList);
        lvAuthor.setAdapter(authorAdapter);
    }

    private void initDummyAuthorData() {
        authorList = new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            authorList.add(new Author());
        }
    }
}