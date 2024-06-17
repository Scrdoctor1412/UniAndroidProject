package phdhtl.k63cntt1.nguyen;

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

import model.Nxb;

public class NxbActivity extends AppCompatActivity {

    ListView nxblv;

    Toolbar toolbar;

    ArrayList<Nxb> listNxb;
    CustomNxbAdapter myadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nxb);

        initView();
        listNxb.add(new Nxb("nxb01","testingnxb","nxbtesting",R.drawable.author));
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