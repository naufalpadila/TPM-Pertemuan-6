package com.example.testapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.testapi.database.AppDatabase;
import com.example.testapi.database.DataDiri;

public class ReadDataActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readdata);


        //init database
        appDatabase = AppDatabase.initDB(this);

        rc = findViewById(R.id.rv_data);
        rc.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        read();
    }

    private void read() {
        // TODO 1: mengambil data dari database
        DataDiri[] list = appDatabase.dao().getData();
        // TODO 2: Tampilin Databse
        rc.setLayoutManager(new LinearLayoutManager(this));
        DataDiriAdapater dataDiriAdapater = new DataDiriAdapater(list, this);
        rc.setAdapter(dataDiriAdapater);
    }


}
