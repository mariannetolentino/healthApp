package com.example.Foodorie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class addItem extends AppCompatActivity {

    foodorieData myFood = new foodorieData();
    String foodName;
    int calories;
    int day, month, year;

    EditText itemInput;
    EditText calorieInput;

    Button addItem;
    Button cancel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_additem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemInput = (EditText) findViewById(R.id.itemInput);
        calorieInput = (EditText) findViewById(R.id.calorieInput);

        addItem = (Button) findViewById(R.id.addItemButton);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodName = itemInput.getText().toString();
                if(!calorieInput.getText().toString().isEmpty() && !foodName.isEmpty())
                {
                    Context context = getApplicationContext();
                    Calendar todaysDate = Calendar.getInstance();
                    calories = Integer.parseInt(calorieInput.getText().toString());
                    year = todaysDate.get(Calendar.YEAR);
                    month = todaysDate.get(Calendar.MONTH);
                    day = todaysDate.get(Calendar.DAY_OF_MONTH);
                    myFood.saveData(year, month, day, foodName + " " + calories, context);
                    Intent intent = new Intent(com.example.Foodorie.addItem.this, dayOfActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    finish();
            }
        });
    }

    //for testing
    private void showToast(String text){
        Toast.makeText(addItem.this, text, Toast.LENGTH_SHORT).show();
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

}
