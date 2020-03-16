package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapi.database.AppDatabase;
import com.example.testapi.database.DataDiri;

public class UpdateActivity extends AppCompatActivity {
    EditText etnama, etalamat, etkelamin;
    String name, address;
    char gender;
    Button btnupdate, btndelete;
    AppDatabase appDatabase;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        appDatabase = AppDatabase.initDB(getApplicationContext());
        Intent bundle = getIntent();

        name = bundle.getStringExtra("name");
        address = bundle.getStringExtra("address");
        gender = bundle.getCharExtra("gender", ' ');
        id = bundle.getIntExtra("id",0);

        etnama = findViewById(R.id.etNama);
        etalamat = findViewById(R.id.etAlamat);
        etkelamin = findViewById(R.id.etJk);
        btnupdate = findViewById(R.id.btnUpdate);
        btndelete = findViewById(R.id.btn_delete);

        // TODO 1 : setText hasil intent tadi
        etnama.setText(name);
        etalamat.setText(address);
        etkelamin.setText(""+gender);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
    }

    private void update() {
        // TODO 3 : Simpan hasil inputan ke dalam String tadi
        name = etnama.getText().toString();
        address = etalamat.getText().toString();
        gender = etkelamin.getText().toString().charAt(0);

        DataDiri item = new DataDiri();
        item.setNama(name);
        item.setAlamat(address);
        item.setJkelamin(gender);
        item.setId(id);

        // TODO 4: Panggil fungsi update DAO()
        appDatabase.dao().updateData(item);
        Toast.makeText(getApplicationContext(), "Data berhasil diubah", Toast.LENGTH_SHORT).show();

        // TODO 5: finish
        finish();

    }

    private void delete(){
        name = etnama.getText().toString();
        address = etalamat.getText().toString();
        gender = etkelamin.getText().toString().charAt(0);

        DataDiri item = new DataDiri();
        item.setNama(name);
        item.setAlamat(address);
        item.setJkelamin(gender);
        item.setId(id);

        appDatabase.dao().deleteData(item);
        Toast.makeText(getApplicationContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show();

        finish();
    }

}
