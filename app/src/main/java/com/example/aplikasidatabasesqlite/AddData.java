package com.example.aplikasidatabasesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddData extends AppCompatActivity {

    EditText edName, edAlamat;
    Button btnSubmit, btnCancel;
    DatabaseHelper databaseHelper;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        edName=findViewById(R.id.edName);
        edAlamat=findViewById(R.id.edAlamat);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnCancel=findViewById(R.id.btnCancel);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddData.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }return  super.onOptionsItemSelected(item);
    }
}
