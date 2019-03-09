package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static android.widget.Toast.makeText;

public class MyRestaurantsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyRestaurantActivity";
    FloatingActionButton fab;
    CheckBox checkBox;
    ListView listView2;
    ArrayList<String> myRList;
    ArrayList<String> rList;
    boolean[] checkedPositions;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_restaurants);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        count = 0;
        fab = findViewById(R.id.fab);
        checkBox = findViewById(R.id.checkBox);
        listView2 = findViewById(R.id.listView2);

        fab.setOnClickListener(this);
//        checkBox.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        String[] addedRestaurants = bundle.getStringArray("addedRestaurants");
        rList = bundle.getStringArrayList("rList");
        myRList = bundle.getStringArrayList("myRList");
        myRList.addAll(Arrays.asList(addedRestaurants));
        checkedPositions = new boolean[myRList.size()];

        listView2.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_activated_1, myRList);
        listView2.setAdapter(arrayAdapter);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                makeText(MyRestaurantsActivity.this, "Clicked Item: " + position + " " +
                        myRList.get(position).toString(), Toast.LENGTH_LONG).show();
                Log.d(TAG, position + "");
                if (checkedPositions[position]) {
                    count--;
                } else {
                    count++;
                }
                checkedPositions[position] = !checkedPositions[position];
                Log.d(TAG, position + "");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                String[] selectedItems = new String[count];
                int x = 0;
                for (int i = 0; i < checkedPositions.length; i++) {
                    if (x < count) {
                        if (checkedPositions[i]) {
                            selectedItems[x] = myRList.get(i);
                            x++;
                        }
                    }
                }
                for (int i = 0; i < selectedItems.length; i++) {
                    Log.d(TAG, selectedItems[i].toString());
                    myRList.remove(selectedItems[i]);
                }
                Intent intent = new Intent(MyRestaurantsActivity.this, MainActivity.class);
                intent.putExtra("removedRestaurants", selectedItems);
                intent.putExtra("rList", rList);
                intent.putExtra("myRList", myRList);
                startActivity(intent);
                String str = "Removed from My Restaurants";
                Snackbar.make(v, str, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.checkBox:
//                checkBox.setSelected(!checkBox.isSelected());
                break;
        }
    }
}
