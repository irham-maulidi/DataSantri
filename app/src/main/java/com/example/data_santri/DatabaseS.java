package com.example.data_santri;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.data_santri.Model;
import com.example.data_santri.ModelSantri;

@Database(entities = {Model.class, ModelSantri.class}, version = 2)
public abstract class DatabaseS extends RoomDatabase {

    public abstract DaoClass kelasDao();

    private static DatabaseS INSTANCE;

    // Membuat method return untuk membuat database
    public static DatabaseS createDatabase(Context context){
        synchronized (DatabaseS.class){
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, DatabaseS.class, "db_santri")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }
}