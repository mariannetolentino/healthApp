package com.example.Foodorie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class dayOfActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    foodorieData myFood = new foodorieData();
    int year, month, day;
    ArrayList foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dayof);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Context context = getApplicationContext();
        Calendar todaysDate = Calendar.getInstance();
        year = todaysDate.get(Calendar.YEAR);
        month = todaysDate.get(Calendar.MONTH);
        day = todaysDate.get(Calendar.DAY_OF_MONTH);
        foodList = myFood.getData(year, month, day, context);


        TextView[] item = new TextView[8];

        item[0] = (TextView) findViewById(R.id.textView3);
        item[1] = (TextView) findViewById(R.id.textView4);
        item[2] = (TextView) findViewById(R.id.textView5);
        item[3] = (TextView) findViewById(R.id.textView6);
        item[4] = (TextView) findViewById(R.id.textView7);
        item[5] = (TextView) findViewById(R.id.textView8);
        item[6] = (TextView) findViewById(R.id.textView9);
        item[7] = (TextView) findViewById(R.id.textView10);

        for(int x = 0; x < foodList.size(); ++x) {
            if(!foodList.get(x).toString().isEmpty()) {
                String[] splitStr = foodList.get(x).toString().split(" ");
                String whitespace = String.format("%-13s", "");
                String food = splitStr[0].replaceAll("_", " ") + whitespace.substring(splitStr[0].length()) + "\t";
                String calorie = splitStr[1] + " calories";
                String textStr = food + whitespace.substring(calorie.length()) + calorie;
                item[x].setVisibility(View.VISIBLE);
                item[x].setText(textStr);
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addItem = new Intent(dayOfActivity.this, addItem.class);
                startActivity(addItem);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
            finish();
            return true;
        } else if (id == R.id.history) {
            Intent history = new Intent(this, historyActivity.class);
            startActivity(history);
            finish();
            return true;
        } else if (id == R.id.calories) {
            Intent calorie = new Intent(this, calorieActivity.class);
            startActivity(calorie);
            finish();
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
