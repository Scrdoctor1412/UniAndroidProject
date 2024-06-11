package phdhtl.k63cntt1.nguyen;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import model.Truyen;

public class StoryActivity extends AppCompatActivity {

    ListView storylv;
    ArrayList<Truyen> listTruyen;
    CustomStoryAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        initView();
        listTruyen.add(new Truyen("T01", "testing", R.drawable.author, "tg01", "test", "test", "kinh di", 32));

    }

    public void initView(){
        storylv = findViewById(R.id.storylv);
        listTruyen = new ArrayList<>();
        myAdapter = new CustomStoryAdapter(StoryActivity.this, R.layout.custom_storylv, listTruyen);
        storylv.setAdapter(myAdapter);
    }
}