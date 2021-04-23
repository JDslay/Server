package com.example.server.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface MeasurementDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeasurement(Measurement measurement);

}
