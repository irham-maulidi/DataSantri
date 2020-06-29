package com.example.data_santri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.data_santri.Constant;

@Entity(tableName = Constant.nama_table)

public class Model {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.id_kelas)
    private int id_kelas;

    @ColumnInfo(name = Constant.nama_kelas)
    private String nama_kelas;

    @ColumnInfo(name = Constant.nama_wali)
    private String nama_wali;

    @ColumnInfo(name = Constant.no_wali)
    private String no_wali;

    @ColumnInfo(name = Constant.alamat_wali)
    private String alamat_wali;

    public int getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(int id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getNama_wali() {
        return nama_wali;
    }

    public void setNama_wali(String nama_wali) {
        this.nama_wali = nama_wali;
    }

    public String getNo_wali() {
        return no_wali;
    }

    public void setNo_wali(String no_wali) {
        this.no_wali = no_wali;
    }

    public String getAlamat_wali() {
        return alamat_wali;
    }

    public void setAlamat_wali(String alamat_wali) {
        this.alamat_wali = alamat_wali;
    }
}
