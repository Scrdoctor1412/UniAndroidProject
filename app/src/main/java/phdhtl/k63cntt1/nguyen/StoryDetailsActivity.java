package phdhtl.k63cntt1.nguyen;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StoryDetailsActivity extends AppCompatActivity {

    ImageView imganhtruyen;
    EditText edtmatruyen, edttentruyen, edtnoidung, edttacgia, edtnxb, edttheloai, edtsochuong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_story_details);

        initView();

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

    }

    public void initView(){
        edtmatruyen = findViewById(R.id.edtmatruyen);
        edttentruyen= findViewById(R.id.edttentruyen);
        edtnoidung = findViewById(R.id.edtnoidung);
        edttacgia = findViewById(R.id.edttacgia);
        edtnxb = findViewById(R.id.edtnxb);
        edttheloai = findViewById(R.id.edttheloai);
        edtsochuong = findViewById(R.id.edtsochuong);
        imganhtruyen = findViewById(R.id.imganhtruyen2);
    }
}