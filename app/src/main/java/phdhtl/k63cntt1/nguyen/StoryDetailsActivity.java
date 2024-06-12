package phdhtl.k63cntt1.nguyen;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class StoryDetailsActivity extends AppCompatActivity {

    ImageView imganhtruyen;
    EditText edtmatruyen, edttentruyen, edtnoidung, edttacgia, edtnxb, edttheloai, edtsochuong, edtluotlike,edtluotxem;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_story_details);

        initView();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent myIntent = getIntent();
        edtmatruyen.setText(myIntent.getStringExtra("matruyen"));
        edttentruyen.setText(myIntent.getStringExtra("tentruyen"));
        edtnoidung.setText(myIntent.getStringExtra("noidung"));
        edttacgia.setText(myIntent.getStringExtra("tacgia"));
        edtnxb.setText(myIntent.getStringExtra("nxb"));
        edttheloai.setText(myIntent.getStringExtra("theloai"));
        int sc = myIntent.getIntExtra("sochuong",0);
        edtsochuong.setText(""+sc);
        int img = myIntent.getIntExtra("anhbia",R.drawable.ic_launcher_background);
        imganhtruyen.setImageResource(img);

        edtluotlike.setText(myIntent.getIntExtra("luotlike", 0)+"");
        edtluotxem.setText(myIntent.getIntExtra("luotxem",0)+"");

    }

    public void initView(){
        edtmatruyen = findViewById(R.id.edtmatruyen);
        edttentruyen= findViewById(R.id.edttentruyen);
        edtnoidung = findViewById(R.id.edtnoidung);
        edttacgia = findViewById(R.id.edttacgia);
        edtnxb = findViewById(R.id.edtnxb);
        edttheloai = findViewById(R.id.edttheloai);
        edtsochuong = findViewById(R.id.edtsochuong);
        edtluotlike = findViewById(R.id.edtluotlike);
        edtluotxem = findViewById(R.id.edtluotxem);
        toolbar = findViewById(R.id.toolbar3);
        imganhtruyen = findViewById(R.id.imganhtruyen2);

    }
}