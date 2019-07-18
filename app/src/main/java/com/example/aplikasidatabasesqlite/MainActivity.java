package com.example.aplikasidatabasesqlite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView list_item;
    ArrayList<Map<String, Object>> arrayList;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list_item = findViewById(R.id.list_item);
        databaseHelper = new DatabaseHelper(this);
        arrayList=new ArrayList<>();


        FloatingActionButton tambah = findViewById(R.id.tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddData.class);
                startActivity(intent);
            }
        });

        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int id=(Integer)arrayList.get(i).get("id");
                final String nama=(String) arrayList.get(i).get("nama");
                final String alamat=(String) arrayList.get(i).get("alamat");
                Intent intent=new Intent(MainActivity.this, AddData.class);

                intent.putExtra("id",id);
                intent.putExtra("nama", nama);
                intent.putExtra("alamat", alamat);

                startActivity(intent);
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

/**MENAMPILKAN DATA DI ARRAY LIST**/
    protected void onResume(){
        super.onResume();
        mengambilData();
    }

    private void mengambilData() {
        arrayList=databaseHelper.getAllDataList();
        if(arrayList.size()>0){
            SimpleAdapter simpleAdapter=
                    new SimpleAdapter( this, arrayList, android.R.layout.simple_list_item_2,
                            new String[]{"nama","alamat"},
                            new int[]{android.R.id.text1,
                                        android.R.id.text2});
            list_item.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
        }
    }


}
