package com.example.data_santri;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import com.example.data_santri.R;
import com.example.data_santri.DatabaseS;
import com.example.data_santri.Model;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Add extends AppCompatActivity {

    @BindView(R.id.edtNamaKelas)
    EditText edtNamaKelas;
    @BindView(R.id.edtNamaWali)
    EditText edtNamaWali;
    @BindView(R.id.edtNoWali)
    EditText edtNoWali;
    @BindView(R.id.edtAlamatWali)
    EditText edtAlamatWali;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;

    DatabaseS santriDatabase;
    String namaKelas, namaWali, noWali, alamatWali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);

        Toolbar tbDetailDokter = findViewById(R.id.toolbar);
        tbDetailDokter.setTitle("Tambah Kelas");
        setSupportActionBar(tbDetailDokter);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        santriDatabase = DatabaseS.createDatabase(this);
    }

    @OnClick(R.id.btnSimpan)
    public void onViewClicked() {

        getData();

        saveData();

        clearData();

        Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();

        finish();

    }

    private void clearData() {
        edtNamaKelas.setText("");
        edtNamaWali.setText("");
        edtNoWali.setText("");
        edtAlamatWali.setText("");
    }

    private void saveData() {

        // Membuat object KelasModel untuk menampung data
        Model kelasModel = new Model();
        // Memasukkan data ke dalam KelasModel
        kelasModel.setNama_kelas(namaKelas);
        kelasModel.setNama_wali(namaWali);
        kelasModel.setNo_wali(noWali);
        kelasModel.setAlamat_wali(alamatWali);
        // Perintah untuk melakukan operasi Insert menggunakan santriDatabaseS
        santriDatabase.kelasDao().insert(kelasModel);
    }

    private void getData() {
        namaKelas = edtNamaKelas.getText().toString();
        namaWali = edtNamaWali.getText().toString();
        noWali = edtNoWali.getText().toString();
        alamatWali = edtAlamatWali.getText().toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
