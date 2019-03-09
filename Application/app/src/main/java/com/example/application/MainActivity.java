package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    FloatingActionButton fab;
    CheckBox checkBox;
    //    RecyclerView recyclerView;
//    RecyclerView.LayoutManager layoutManager;
//    RecyclerView.Adapter adapter;
    ListView listView;
    ArrayList<String> rList;
    ArrayList<String> myRList;
    boolean[] checkedPositions;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        count = 0;
        fab = findViewById(R.id.fab);
        checkBox = findViewById(R.id.checkBox);
        listView = findViewById(R.id.listView);

        fab.setOnClickListener(this);
//        checkBox.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            rList = new ArrayList<>();
            myRList = new ArrayList<>();
            for (int i = 0; i < 25; i++) {
                rList.add("Restaurant #" + i);
            }
        } else {
            rList = bundle.getStringArrayList("rList");
            myRList = bundle.getStringArrayList("myRList");
            String[] removedRestaurants = bundle.getStringArray("removedRestaurants");
            rList.addAll(Arrays.asList(removedRestaurants));
        }
        Log.d(TAG, rList.size() + "");
        checkedPositions = new boolean[rList.size()];

        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_activated_1, rList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                makeText(MainActivity.this, "Clicked Item: " + position + " " +
                        rList.get(position).toString(), Toast.LENGTH_LONG).show();
                if (checkedPositions[position]) {
                    count--;
                } else {
                    count++;
                }
                Log.d(TAG, position + "");
                checkedPositions[position] = !checkedPositions[position];
            }
        });
//        listView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        adapter = new MainAdapter(rList);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);

//        ImageView photo = findViewById(R.id.photo);
//        int imageResource = getResources().getIdentifier("@drawable/mcd_logo",
//                null, this.getPackageName());
//        photo.setImageResource(imageResource);
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
                            selectedItems[x] = rList.get(i);
                            x++;
                        }
                    }
                }
                for (int i = 0; i < selectedItems.length; i++) {
                    Log.d(TAG, selectedItems[i]);
                    rList.remove(selectedItems[i]);
                }
                Intent intent = new Intent(MainActivity.this, MyRestaurantsActivity.class);
                intent.putExtra("addedRestaurants", selectedItems);
                intent.putExtra("rList", rList);
                intent.putExtra("myRList", myRList);
                startActivity(intent);
//                String str = selectedItems.toString();
//                Snackbar.make(v, str, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.checkBox:
//                checkBox.setSelected(!checkBox.isSelected());
                break;
        }
    }
}
