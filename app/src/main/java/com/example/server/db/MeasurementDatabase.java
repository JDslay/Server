package com.example.server.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Measurement.class, version = 1)
public abstract class MeasurementDatabase extends RoomDatabase {

    private static MeasurementDatabase instance;

    public static synchronized MeasurementDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MeasurementDatabase.class, "measurement_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract MeasurementDAO measurementDAO();
}
