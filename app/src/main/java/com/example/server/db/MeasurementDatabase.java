package com.example.server.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Measurement.class, version = 1)
@TypeConverters(Converters.class)
public abstract class MeasurementDatabase extends RoomDatabase {
    public abstract MeasurementDAO measurementDAO();
}
