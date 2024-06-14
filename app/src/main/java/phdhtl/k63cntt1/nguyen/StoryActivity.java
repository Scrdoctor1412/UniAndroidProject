package phdhtl.k63cntt1.nguyen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.adapter.CustomStoryAdapter;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.model.Story;

public class StoryActivity extends AppCompatActivity {

    ListView storylv;
    ArrayList<Story> listTruyen;
    CustomStoryAdapter myAdapter;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        initView();
        ImageView img = new ImageView(getApplicationContext());
        img.setImageResource(R.drawable.author);
        Bitmap bm = ((BitmapDrawable)img.getDrawable()).getBitmap();
        String bmText = ConvertHelper.BitMapToString(bm);

        listTruyen.add(new Story("T01", "testing", bmText , "tg01", "test", 10, 10000, 32,"",""));

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Set click event for sending data to Story Details activity
        storylv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Story mytruyen = listTruyen.get(position);
                Intent myIntent = new Intent(getApplicationContext(), StoryDetailsActivity.class);
                myIntent.putExtra("matruyen", mytruyen.getMatruyen());
                myIntent.putExtra("tentruyen", mytruyen.getTentruyen());
//                myIntent.putExtra("tacgia", mytruyen.getTentg());
                myIntent.putExtra("noidung", mytruyen.getNoidung());
                myIntent.putExtra("theloai", mytruyen.getTheloai());
                myIntent.putExtra("sochuong", mytruyen.getSochuong());
                myIntent.putExtra("anhbia", mytruyen.getAnhbia());
//                myIntent.putExtra("nxb", mytruyen.getTennxb());
                myIntent.putExtra("luotlike",mytruyen.getLuotlike());
                myIntent.putExtra("luotxem",mytruyen.getLuotxem());

                startActivity(myIntent);
            }
        });
    }



    public void initView(){
        storylv = findViewById(R.id.storylv);
        listTruyen = new ArrayList<>();
        myAdapter = new CustomStoryAdapter(StoryActivity.this, R.layout.custom_storylv, listTruyen);
        storylv.setAdapter(myAdapter);
        toolbar = findViewById(R.id.toolbar2);
    }
}