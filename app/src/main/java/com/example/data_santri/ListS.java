package com.example.data_santri;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListS extends AppCompatActivity {

    @BindView(R.id.RVsantri)
    RecyclerView rvSiswa;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    DatabaseS siswaDatabase;
    List<ModelSantri> siswaModelList;
    int id_kelas;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        Toolbar tbDetailDokter = findViewById(R.id.toolbar);
        tbDetailDokter.setTitle("Daftar Santri");
        setSupportActionBar(tbDetailDokter);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            id_kelas = bundle.getInt(Constant.KEY_ID_KELAS);
        }

        siswaDatabase = DatabaseS.createDatabase(this);

        siswaModelList = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        siswaModelList.clear();

        getData();

        rvSiswa.setLayoutManager(new LinearLayoutManager(this));
        rvSiswa.setAdapter(new AdapterSantri(this, siswaModelList));
    }

    private void getData() {
        siswaModelList = siswaDatabase.kelasDao().selectSiswa(id_kelas);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        startActivity(new Intent(this, AddSantri.class).putExtra(Constant.KEY_ID_KELAS, id_kelas));
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