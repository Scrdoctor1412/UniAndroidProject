package phdhtl.k63cntt1.nguyen.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.CursorWindow;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Field;

import phdhtl.k63cntt1.nguyen.R;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

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
                }else if(item.getItemId() == R.id.item_story_type){
                    Intent myIntent = new Intent(getApplicationContext(), StoryTypeActivity.class);
                    startActivity(myIntent);
                }
                return false;
            }
        });


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
}