package com.example.Foodorie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Calendar;

public class historyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    foodorieData myFood = new foodorieData();
    ArrayList textOutput = new ArrayList();
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Context context = getApplicationContext();
        Calendar todaysDate = Calendar.getInstance();
        year = todaysDate.get(Calendar.YEAR);
        month = todaysDate.get(Calendar.MONTH);
        day = todaysDate.get(Calendar.DAY_OF_MONTH);
        textOutput = myFood.getData(year, month, day, context);
        if(textOutput.get(0).toString() == "") {
            textOutput.set(0,"No food consumed on this day");
        }
        else {
            for(int x = 0; x < textOutput.size(); ++x) {
                if(textOutput.get(x).toString() != "") {
                    String[] splitStr = textOutput.get(x).toString().split(" ");
                    String whitespace = String.format("%-27s", "");
                    String food = splitStr[0].replaceAll("_", " ") + whitespace.substring(splitStr[0].length()) + "\t";
                    String calorie = splitStr[1] + "  calories";
                    String textStr = food + whitespace.substring(calorie.length()) + calorie;
                    textOutput.set(x, textStr);
                }
            }
        }
        ArrayAdapter adapter1 = new ArrayAdapter<String>(historyActivity.this, R.layout.history_list, textOutput);
        ListView listView1 = (ListView) findViewById(R.id.mobile_list1);
        listView1.setAdapter(adapter1);

        CalendarView calView_historyDate;
        calView_historyDate = (CalendarView) findViewById(R.id.calendarView);
        calView_historyDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year_buff, int month_buff, int day_buff) {
                Context context = getApplicationContext();
                textOutput = myFood.getData(year_buff, month_buff, day_buff, context);
                for(int x = 0; x < textOutput.size(); ++x) {
                    if(textOutput.get(x).toString() != "") {
                        String[] splitStr = textOutput.get(x).toString().split(" ");
                        String whitespace = String.format("%-27s", "");
                        String food = splitStr[0].replaceAll("_", " ") + whitespace.substring(splitStr[0].length()) + "\t";
                        String calorie = splitStr[1] + "  calories";
                        String textStr = food + whitespace.substring(calorie.length()) + calorie;
                        textOutput.set(x, textStr);
                    }
                }
                if(textOutput.get(0).toString() == "") {
                    textOutput.set(0,"No food consumed on this day");
                }
                ArrayAdapter adapter1 = new ArrayAdapter<String>(historyActivity.this, R.layout.history_list, textOutput);
                ListView listView1 = (ListView) findViewById(R.id.mobile_list1);
                listView1.setAdapter(adapter1);
            }
        });
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