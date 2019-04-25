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

import java.util.ArrayList;
import java.util.Calendar;

public class dayOfActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    foodorieData myFood = new foodorieData();
    private CheckBox chk2, chk3, chk4, chk5, chk6, chk7, chk8;
    int year, month, day;
    ArrayList foodList;
    String[] separate;

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

        chk2 = (CheckBox) findViewById(R.id.checkBox2);
        chk3 = (CheckBox) findViewById(R.id.checkBox3);
        chk4 = (CheckBox) findViewById(R.id.checkBox4);
        chk5 = (CheckBox) findViewById(R.id.checkBox5);
        chk6 = (CheckBox) findViewById(R.id.checkBox6);
        chk7 = (CheckBox) findViewById(R.id.checkBox7);
        chk8 = (CheckBox) findViewById(R.id.checkBox8);

        for(int x = 0; x < foodList.size(); ++x)
        {
            if(foodList.get(x).toString() != "")
            {
                if(chk2.getText() == "")
                {
                    chk2.setText(foodList.get(x).toString());
                    chk2.setChecked(true);
                    chk2.setVisibility(View.VISIBLE);
                }
                else if(chk3.getText() == "")
                {
                    chk3.setText(foodList.get(x).toString());
                    chk3.setChecked(true);
                    chk3.setVisibility(View.VISIBLE);
                }
                else if(chk4.getText() == "")
                {
                    chk4.setText(foodList.get(x).toString());
                    chk4.setChecked(true);
                    chk4.setVisibility(View.VISIBLE);
                }
                else if(chk5.getText() == "")
                {
                    chk5.setText(foodList.get(x).toString());
                    chk5.setChecked(true);
                    chk5.setVisibility(View.VISIBLE);
                }
                else if(chk6.getText() == "")
                {
                    chk6.setText(foodList.get(x).toString());
                    chk6.setChecked(true);
                    chk6.setVisibility(View.VISIBLE);
                }
                else if(chk7.getText() == "")
                {
                    chk7.setText(foodList.get(x).toString());
                    chk7.setChecked(true);
                    chk7.setVisibility(View.VISIBLE);
                }
                else if(chk8.getText() == "")
                {
                    chk8.setText(foodList.get(x).toString());
                    chk8.setChecked(true);
                    chk8.setVisibility(View.VISIBLE);
                }
                else
                {
                    break;
                }
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addItem = new Intent(dayOfActivity.this, addItem.class);
                startActivity(addItem);
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

    @SuppressWarnings("StatementWithEmptyBody")
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
