package phdhtl.k63cntt1.nguyen;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StatisticsActivity extends AppCompatActivity {
    //thong ke truyen hot theo the loai, tac gia
    // loại, lượt xem
    //bar char
    //spinner

    private final String BY_AUTHOR = "Tác giả";
    private final String BY_CATEGORY = "Thể loại";
    private final String[] chartSpinnerValueList = {"Tác giả", "Thể loại"};
    BarChart barChartComic;
    Spinner sprChar;
    List<BarEntry> barEntryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistics);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        sprChar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (chartSpinnerValueList[position]) {
                    case BY_AUTHOR:
                        //query data and show
                        String[] labels = {"Hiếu Hiếu", "Nancy", "Taylor Swift", "K. Nguyên", "Eminem"};
                        int[] views = {30, 300, 20, 500, 30};
                        showMostViewChart(labels, views);
                        break;
                    case BY_CATEGORY:
                        //query data and show
                        String[] labels2 = {"Hành động", "Lãng mạn", "Trinh thám", "Chuyển sinh", "Kinh dị"};
                        int[] views2 = {300, 30, 200, 50, 80};
                        showMostViewChart(labels2, views2);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initView() {
        barChartComic = findViewById(R.id.bc_comicStatistics);
        sprChar = findViewById(R.id.spr_charSpinner);

        ArrayAdapter<CharSequence> typeArrayAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                chartSpinnerValueList);
        sprChar.setAdapter(typeArrayAdapter);
    }

    private void showMostViewChart(String[] labels, int[] views) {
        BarData barData = new BarData();
        barChartComic.clear();
        int[] colors = getArrayColors(labels.length);

        for (int i = 0; i < labels.length; i ++) {
            List<BarEntry> barEntryList = new ArrayList<>();
            barEntryList.add(new BarEntry(i, views[i]));
            BarDataSet barDataSet = new BarDataSet(barEntryList, labels[i]);
            barDataSet.setColor(colors[i]);
            barData.addDataSet(barDataSet);
        }

        barChartComic.setData(barData);
        barChartComic.setFitBars(true);
        barChartComic.invalidate();
    }

    private int[] getArrayColors(int num) {
        int[] colors = new int[num];
        Log.d("size: ", colors.length + "");

        Random rnd = new Random();

        for (int i = 0; i < colors.length; i ++) {
            int red = 0;
            int green = 0;
            int blue = 0;;
            while (red < 100 || green < 100 || blue <100) {
                red = rnd.nextInt(255);
                green = rnd.nextInt(255);
                blue = rnd.nextInt(255);
            }
            colors[i] = Color.argb(255, red, green, blue);
        }
        return colors;
    }
}