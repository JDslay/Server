package com.example.server.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MeasurementDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeasurement(Measurement measurement);

    @Delete
    void deleteMeasurement(Measurement measurement);

    @Query("SELECT * FROM measurement_table")
    List<Measurement>  getAllMeasurements();

    @Query("SELECT * FROM measurement_table ORDER BY fieldStrength DESC")
    LiveData<List<Measurement>>  getAllMeasurementsSortedByFieldStrength();

    @Query("SELECT AVG(fieldStrength) FROM measurement_table")
    LiveData<Double> getFieldStrengthAverage();

}
