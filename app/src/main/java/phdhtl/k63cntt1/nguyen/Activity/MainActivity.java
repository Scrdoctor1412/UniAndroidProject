package phdhtl.k63cntt1.nguyen.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
<<<<<<< HEAD:app/src/main/java/phdhtl/k63cntt1/nguyen/MainActivity.java
import android.graphics.Color;
=======
import android.content.SharedPreferences;
import android.database.CursorWindow;
>>>>>>> cd1b35ff8b893212976e03ca301af539c0483df2:app/src/main/java/phdhtl/k63cntt1/nguyen/Activity/MainActivity.java
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
<<<<<<< HEAD:app/src/main/java/phdhtl/k63cntt1/nguyen/MainActivity.java
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
=======
>>>>>>> cd1b35ff8b893212976e03ca301af539c0483df2:app/src/main/java/phdhtl/k63cntt1/nguyen/Activity/MainActivity.java

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.navigation.NavigationView;

<<<<<<< HEAD:app/src/main/java/phdhtl/k63cntt1/nguyen/MainActivity.java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
=======
import java.lang.reflect.Field;

import phdhtl.k63cntt1.nguyen.R;
>>>>>>> cd1b35ff8b893212976e03ca301af539c0483df2:app/src/main/java/phdhtl/k63cntt1/nguyen/Activity/MainActivity.java

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    private final String BY_AUTHOR = "Tác giả";
    private final String BY_CATEGORY = "Thể loại";
    private final String BY_COMIC = "Truyện";
    private final String[] chartSpinnerValueList = {"Tác giả", "Thể loại", "Truyện"};
    BarChart barChartComic;
    Spinner sprChar;
    List<BarEntry> barEntryList;
    Button btnExportCsv;

    private String filename = "thongke.csv";
    private String filepath = "CSV";
    File myExternalFile;
    String[] labelsArr;
    int[] viewsArr;
    int[] likesArr;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

<<<<<<< HEAD:app/src/main/java/phdhtl/k63cntt1/nguyen/MainActivity.java
        sprChar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (chartSpinnerValueList[position]) {
                    case BY_AUTHOR:
                        //query data and show
                        labelsArr = new String[] {"Hiếu Hiếu", "Nancy", "Taylor Swift", "K. Nguyên", "Eminem"};
                        viewsArr = new int[] {30, 300, 20, 500, 30};
                        likesArr = new int[] {50, 330, 200, 50, 10};
//                        showMostViewChart(labels, views);
                        showMostViewAndLikeComicChart(labelsArr, viewsArr, likesArr);
                        break;
                    case BY_CATEGORY:
                        //query data and show
                        labelsArr = new String[] {"Hành động", "Lãng mạn", "Trinh thám", "Chuyển sinh", "Kinh dị"};
                        viewsArr = new int[] {300, 30, 200, 50, 80};
                        likesArr = new int[] {50, 30, 200, 50, 109};
//                        showMostViewChart(labels2, views2);
                        showMostViewAndLikeComicChart(labelsArr, viewsArr, likesArr);
                        break;
                    case BY_COMIC:
                        //query data and show
                        labelsArr = new String[] {"AOT", "Gundam 003", "Doraemon 14", "Iron man", "Wing gundam"};
                        viewsArr = new int[] {30, 300, 20, 150, 80};
                        likesArr = new int[] {50, 30, 200, 50, 109};
//                        showMostViewChart(labels2, views2);
                        showMostViewAndLikeComicChart(labelsArr, viewsArr, likesArr);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnExportCsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //thông báo ko ghi đc
                if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
                    Log.d("write file", "onClick: not available");
                else {
                    myExternalFile = new File(getExternalFilesDir(filepath), filename);
                    try {
                        FileOutputStream fos = new FileOutputStream(myExternalFile);
//                        fos.write("hello".getBytes());
                        fos.write("Name, Views, Likes \n".getBytes());
                        for (int i = 0; i < labelsArr.length; i ++) {
                            fos.write((labelsArr[i] + ",").getBytes());
                            fos.write((viewsArr[i] + ",").getBytes());
                            fos.write((likesArr[i] + "\n").getBytes());
                        }
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        //thông báo lỗi
                    }
                    Log.d("path", myExternalFile.getPath());
                }
            }
        });
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
=======
        //for fixing error Row too big to fit into CursorWindow
        try {
            @SuppressLint("PrivateApi")
            Field field =
                    CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }

>>>>>>> cd1b35ff8b893212976e03ca301af539c0483df2:app/src/main/java/phdhtl/k63cntt1/nguyen/Activity/MainActivity.java
    }

    public void initView(){
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.iconmenu);

        btnExportCsv = findViewById(R.id.btn_exportCsvFile);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.item_home){

                    Log.d("Home", "clicked");
                }else if(item.getItemId() == R.id.item_story){
                    Intent myIntent = new Intent(getApplicationContext(), StoryActivity.class);
                    startActivity(myIntent);
                }else if(item.getItemId() == R.id.item_user){

                }else if(item.getItemId() == R.id.item_logout){
                    SharedPreferences settings = getSharedPreferences("Login", MODE_PRIVATE);
                    settings.edit().clear().commit();
                    Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(in);
                } else if (item.getItemId() == R.id.item_author) {
                    Intent userActivityLauncher = new Intent(MainActivity.this, AuthorActivity.class);
                    startActivity(userActivityLauncher);
                }else if(item.getItemId() == R.id.item_user_manager){
                    Intent userActivityLauncher = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(userActivityLauncher);
                }else if(item.getItemId() == R.id.item_publisher){
                    Intent myIntent = new Intent(getApplicationContext(), NxbActivity.class);
                    startActivity(myIntent);
<<<<<<< HEAD:app/src/main/java/phdhtl/k63cntt1/nguyen/MainActivity.java
                } else if (item.getItemId() == R.id.item_statistics) {
                    Intent myIntent = new Intent(getApplicationContext(), StatisticsActivity.class);
=======
                }else if(item.getItemId() == R.id.item_story_type){
                    Intent myIntent = new Intent(getApplicationContext(), StoryTypeActivity.class);
>>>>>>> cd1b35ff8b893212976e03ca301af539c0483df2:app/src/main/java/phdhtl/k63cntt1/nguyen/Activity/MainActivity.java
                    startActivity(myIntent);
                }
                return false;
            }
        });

        //Biểu đồ
        barChartComic = findViewById(R.id.bc_comicStatistics);
        sprChar = findViewById(R.id.spr_charSpinner);

        ArrayAdapter<CharSequence> typeArrayAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                chartSpinnerValueList);
        sprChar.setAdapter(typeArrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
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

//    private void showMostViewAndLikeComicChart(String[] labels, int[] views, int[] likes) {
//        BarData barData = new BarData();
//        barChartComic.clear();
//        int[] colors = getArrayColors(2);
//        List<BarEntry> entriesGroupViews = new ArrayList<>();
//        List<BarEntry> entriesGroupLikes = new ArrayList<>();
//
//        for(int i = 0; i < labels.length; i++) {
//            entriesGroupViews.add(new BarEntry(i, views[i]));
//            entriesGroupLikes.add(new BarEntry(i, likes[i]));
//        }
//
//        BarDataSet setViews = new BarDataSet(entriesGroupViews, "Lượt xem");
//        BarDataSet setLikes = new BarDataSet(entriesGroupLikes, "Lượt thích");
//        setViews.setColor(colors[0]);
//        setLikes.setColor(colors[1]);
//
//        barData.addDataSet(setViews);
//        barData.addDataSet(setLikes);
//
//        float groupSpace = 0.06f;
//        float barSpace = 0.02f; // x2 dataset
//        float barWidth = 0.45f; // x2 dataset
//        // (0.02 + 0.45) * 2 + 0.06 = 1.00 -> interval per "group"
//
//        barData.setBarWidth(barWidth); // set the width of each bar
//        barChartComic.setData(barData);
//        barChartComic.groupBars(0, groupSpace, barSpace); // perform the "explicit" grouping
//        barChartComic.setFitBars(true);
//
//        Description description = new Description();
//        description.setText("Thống kê lượt xem và lượt thích theo " + sprChar.getSelectedItem().toString());
//        barChartComic.setDescription(description);
//
////        barChartComic.getXAxis().setValueFormatter();
//        // Custom IndexAxisValueFormatter for the X-axis
//        IndexAxisValueFormatter xAxisFormatter = new IndexAxisValueFormatter(labels) {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                int index = (int) value; // Adjust index for grouped bars
//
//                if (index >= 0 && index < labels.length) {
//                    return labels[index];
//                }
//                return "";
//            }
//        };
//
//        XAxis xAxis = barChartComic.getXAxis();
//        xAxis.setValueFormatter(xAxisFormatter);
//        xAxis.setGranularity(1f); // Set granularity to 1 to avoid skipping labels
//        xAxis.setGranularityEnabled(true);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        barChartComic.invalidate();
//    }

    private void showMostViewAndLikeComicChart(String[] labels, int[] views, int[] likes) {
        BarData barData = new BarData();
        barChartComic.clear();
        int[] colors = getArrayColors(2);
        List<BarEntry> entriesGroupViews = new ArrayList<>();
        List<BarEntry> entriesGroupLikes = new ArrayList<>();

        for (int i = 0; i < labels.length; i++) {
            entriesGroupViews.add(new BarEntry(i, views[i]));
            entriesGroupLikes.add(new BarEntry(i, likes[i]));
        }

        BarDataSet setViews = new BarDataSet(entriesGroupViews, "Lượt xem");
        BarDataSet setLikes = new BarDataSet(entriesGroupLikes, "Lượt thích");
        setViews.setColor(colors[0]);
        setLikes.setColor(colors[1]);

        barData.addDataSet(setViews);
        barData.addDataSet(setLikes);

        float groupSpace = 0.4f;
        float barSpace = 0.03f; // x2 dataset
        float barWidth = 0.2f; // x2 dataset
        // (0.03 + 0.2) * 2 + 0.4 = 1.00 -> interval per "group"

        barData.setBarWidth(barWidth); // set the width of each bar
        barChartComic.setData(barData);
        barChartComic.groupBars(0, groupSpace, barSpace); // perform the "explicit" grouping
        barChartComic.setFitBars(true);

        Description description = new Description();
        description.setText("Thống kê lượt xem và lượt thích theo " + sprChar.getSelectedItem().toString());
        barChartComic.setDescription(description);

        // Custom IndexAxisValueFormatter for the X-axis
        IndexAxisValueFormatter xAxisFormatter = new IndexAxisValueFormatter(labels);

        XAxis xAxis = barChartComic.getXAxis();
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setGranularity(1f); // Set granularity to 1 to avoid skipping labels
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // Position labels at the bottom

        // Optionally adjust Y-axis for better visualization
        YAxis leftAxis = barChartComic.getAxisLeft();
        leftAxis.setGranularity(1f); // Adjust granularity if necessary
        leftAxis.setGranularityEnabled(true);
        barChartComic.getAxisRight().setEnabled(false); // Disable right Y-axis if not needed

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