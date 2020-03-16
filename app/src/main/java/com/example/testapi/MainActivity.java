package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapi.database.AppDatabase;
import com.example.testapi.database.DataDiri;

public class MainActivity extends AppCompatActivity {
    private EditText etNama, etAlamat, etJk;
    private Button btnInsert, btnData;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabase.initDB(getApplicationContext());
        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etJk = findViewById(R.id.etJk);
        btnInsert = findViewById(R.id.btnInsert);
        btnData = findViewById(R.id.btnData);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });


        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReadDataActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertData() {
        try {
            String nama = etNama.getText().toString();
            String alamat = etAlamat.getText().toString();
            char jk = etJk.getText().toString().charAt(0);

            DataDiri item = new DataDiri();
            item.setNama(nama);
            item.setAlamat(alamat);
            item.setJkelamin(jk);

            //Setelah itu kirim ke database
            appDatabase.dao().insertData(item);
            Toast.makeText(getApplicationContext(), "Data berhasil dimasukkan", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Data harap di isi", Toast.LENGTH_SHORT).show();
        }

        etNama.setText("");
        etAlamat.setText("");
        etJk.setText("");
    }
}
