package com.example.Foodorie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView mListView;
    foodorieData myFood = new foodorieData();
    ArrayList textOutput = new ArrayList();
    int day, month, year, dayOfWeek, totalcalories;
    String foodcalorie, sun = "Sunday", mon = "Monday", tue = "Tuesday", wed = "Wednesday", thu= "Thursday", fri = "Friday", sat = "Saturday";
    String[] splitStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mListView = (ListView) findViewById(R.id.listView);
        ArrayList<Card> list = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        setTitle("Today Is: " + sdf.format(d));

        Context context = getApplicationContext();
        Calendar tempCalendar = Calendar.getInstance();
        dayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK);

        for(int x = dayOfWeek - 1; x >= 0; --x) {
            totalcalories = 0;
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.DAY_OF_MONTH, -x);
            year = currentDate.get(Calendar.YEAR);
            month = currentDate.get(Calendar.MONTH);
            day = currentDate.get(Calendar.DAY_OF_MONTH);

            textOutput = myFood.getData(year, month, day, context);
            for(int y = 0; y < textOutput.size(); ++y) {
                if(textOutput.get(y).toString() != "") {
                    foodcalorie = textOutput.get(y).toString();
                    splitStr = foodcalorie.split(" ");
                    totalcalories += Integer.parseInt(splitStr[1]);
                }
            }
            if(x == 6) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(sun.length()) + "\t";
                    String calorie = cal_temp + " calories";
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    sun += textStr;
                }
            }
            else if(x == 5) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(mon.length()) + "\t";
                    String calorie = cal_temp + " calories";
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    mon += textStr;
                }
            }
            else if(x == 4) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(tue.length()) + "\t";
                    String calorie = cal_temp + " calories";
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    tue += textStr;
                }
            }
            else if(x == 3) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(wed.length()) + "\t";
                    String calorie = cal_temp + " calories";
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    wed += textStr;
                }
            }
            else if(x == 2) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(thu.length()) + "\t";
                    String calorie = cal_temp + " calories";
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    thu += textStr;
                }
            }
            else if(x == 1) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(fri.length()) + "\t";
                    String calorie = cal_temp + " calories";
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    fri += textStr;
                }
            }
            else if(x == 0) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(sat.length()) + "\t";
                    String calorie = cal_temp + " calories";
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    sat += textStr;
                }
            }
        }

        list.add(new Card("drawable://" + R.drawable.smokey_mountain, sun));
        list.add(new Card("drawable://" + R.drawable.smokey_mountain, mon));
        list.add(new Card("drawable://" + R.drawable.smokey_mountain, tue));
        list.add(new Card("drawable://" + R.drawable.smokey_mountain, wed));
        list.add(new Card("drawable://" + R.drawable.smokey_mountain, thu));
        list.add(new Card("drawable://" + R.drawable.smokey_mountain, fri));
        list.add(new Card("drawable://" + R.drawable.smokey_mountain, sat));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.content_main, list);
        mListView.setAdapter(adapter);
    }

    public void gotoDaily(View view){
        Intent intent = new Intent(this, dayOfActivity.class);
        startActivity(intent);
        finish();
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