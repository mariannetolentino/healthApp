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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class calorieActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    foodorieData myFood = new foodorieData();
    int year, month, day, dayOfWeek, totalcalories = 0, totalweekcal = 0;
    ArrayList textOutput;
    String[] splitStr;
    String foodcalorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView[] item = new TextView[8];

        item[0] = (TextView) findViewById(R.id.textView11); //sunday
        item[1] = (TextView) findViewById(R.id.textView12); //monday
        item[2] = (TextView) findViewById(R.id.textView13); //tuesday
        item[3] = (TextView) findViewById(R.id.textView14); //wednesday
        item[4] = (TextView) findViewById(R.id.textView15); //thursday
        item[5] = (TextView) findViewById(R.id.textView16); //friday
        item[6] = (TextView) findViewById(R.id.textView17); //saturday
        item[7] = (TextView) findViewById(R.id.textView20); //total calories for the week

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
                    totalweekcal += Integer.parseInt(splitStr[1]);
                }
            }
            if(x == 6) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(item[0].length()) + "\t";
                    String calorie = cal_temp;
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    item[0].append(textStr);
                }
            }
            else if(x == 5) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(item[1].length()) + "\t";
                    String calorie = cal_temp;
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    item[1].append(textStr);
                }
            }
            else if(x == 4) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(item[2].length()) + "\t";
                    String calorie = cal_temp;
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    item[2].append(textStr);
                }
            }
            else if(x == 3) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(item[3].length()) + "\t";
                    String calorie = cal_temp;
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    item[3].append(textStr);
                }
            }
            else if(x == 2) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(item[4].length()) + "\t";
                    String calorie = cal_temp;
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    item[4].append(textStr);
                }
            }
            else if(x == 1) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-25s", "");
                    String day = whitespace.substring(item[5].length()) + "\t";
                    String calorie = cal_temp;
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    item[5].append(textStr);
                }
            }
            else if(x == 0) {
                String cal_temp = Integer.toString(totalcalories);
                if(totalcalories > 0) {
                    String whitespace = String.format("%-9s", "");
                    String day = whitespace.substring(item[6].length()) + "\t";
                    String calorie = cal_temp;
                    String textStr = day + whitespace.substring(calorie.length()) + calorie;
                    item[6].append(textStr);
                }
            }
        }
        item[7].setText(Integer.toString(totalweekcal) + " calories");
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
