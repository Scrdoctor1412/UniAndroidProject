package phdhtl.k63cntt1.nguyen;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

import phdhtl.k63cntt1.nguyen.adapter.CustomNxbAdapter;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.model.Publisher;

public class NxbActivity extends AppCompatActivity {

    ListView nxblv;

    Toolbar toolbar;

    ArrayList<Publisher> listNxb;
    CustomNxbAdapter myadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nxb);

        initView();

        ImageView testImg = new ImageView(getApplicationContext());
        testImg.setImageResource(R.drawable.author);
        Bitmap bm = ((BitmapDrawable)testImg.getDrawable()).getBitmap();
        String bmText = ConvertHelper.BitMapToString(bm);

        listNxb.add(new Publisher("nxb01","testingnxb","nxbtesting",bmText));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void initView(){
        nxblv = findViewById(R.id.nxblv);
        toolbar = findViewById(R.id.toolbarnxb);
        listNxb = new ArrayList<>();
        myadapter = new CustomNxbAdapter(NxbActivity.this, R.layout.custom_nxblv, listNxb);
        nxblv.setAdapter(myadapter);
    }
}