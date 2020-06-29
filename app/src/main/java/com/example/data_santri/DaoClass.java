package com.example.data_santri;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface DaoClass {

    // Mengambil data
    @Query("SELECT * FROM KELAS ORDER BY nama_kelas ASC")
    List<Model> select();

    // Memasukkan data
    @Insert
    void insert(Model kelasModel);

    // Menghapus data
    @Delete
    void delete(Model kelasModel);

    // Mengupdate data
    @androidx.room.Update
    void update(Model kelasModel);

    // Mengambil data siswa
    @Query("SELECT * FROM TB_SISWA WHERE id_siswa = :id_kelas ORDER BY nama_siswa ASC")
    List<ModelSantri> selectSiswa(int id_kelas);

    // Memasukkan data siswa
    @Insert
    void insertSiswa(ModelSantri santriModel);

    // Menghapus data siswa
    @Delete
    void deleteSiswa(ModelSantri santriModel);

    // Mengupdate data
    @Update
    void updateSiswa(ModelSantri santriModel);
}