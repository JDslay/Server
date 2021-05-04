package com.example.server.db;

import android.graphics.Bitmap;
import android.location.Location;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "measurement_table")
public class Measurement {

    @PrimaryKey(autoGenerate = true)
    public int uid = 0;
    public Bitmap img = null;
    public Long timeStamp = 0L;
    public double fieldStrength = 0.;
    public double latitude  = 0.;

    public Measurement(Bitmap img, Long timeStamp, double fieldStrength, double latitude) {
        this.img = img;
        this.timeStamp = timeStamp;
        this.fieldStrength = fieldStrength;
        this.latitude = latitude;
    }
}
