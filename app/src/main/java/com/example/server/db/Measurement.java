package com.example.server.db;

import android.graphics.Bitmap;
import android.location.Location;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "measurement_table")
public class Measurement {
    @Nullable
    public Bitmap img = null;
    public Long timeStamp = 0L;
    public double fieldStrength = 0.;
    @Nullable
    public Location location  = null;

    @PrimaryKey(autoGenerate = true)
    @Nullable
    public int uid = 0;

}
