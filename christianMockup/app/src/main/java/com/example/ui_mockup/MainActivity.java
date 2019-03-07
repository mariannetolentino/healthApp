package com.example.ui_mockup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] mobileArray = { "Sample events here", "Cardio", "Ate a cookie", "Slept", "Walked my dog", "Binged watch GOT", "Played video games"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter1 = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);

        ListView listView1 = (ListView) findViewById(R.id.mobile_list1);
        listView1.setAdapter(adapter1);
    }
}
