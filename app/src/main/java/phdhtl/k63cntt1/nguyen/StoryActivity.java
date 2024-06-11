package phdhtl.k63cntt1.nguyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        storylv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Truyen mytruyen = listTruyen.get(position);
                Intent myIntent = new Intent(getApplicationContext(), StoryDetailsActivity.class);
                myIntent.putExtra("matruyen", mytruyen.getMatruyen());
                myIntent.putExtra("tentruyen", mytruyen.getTentruyen());
                myIntent.putExtra("tacgia", mytruyen.getTacgia());
                myIntent.putExtra("noidung", mytruyen.getNoidung());
                myIntent.putExtra("theloai", mytruyen.getTheloai());
                myIntent.putExtra("sochuong", mytruyen.getSochuong());
                myIntent.putExtra("anhbia", mytruyen.getAnhbia());
                myIntent.putExtra("nxb", mytruyen.getNxb());

                startActivity(myIntent);
            }
        });
    }

    public void initView(){
        storylv = findViewById(R.id.storylv);
        listTruyen = new ArrayList<>();
        myAdapter = new CustomStoryAdapter(StoryActivity.this, R.layout.custom_storylv, listTruyen);
        storylv.setAdapter(myAdapter);
    }
}