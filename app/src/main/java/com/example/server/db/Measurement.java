package com.example.server.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "measurement_table")
public class Measurement {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    //public Bitmap img = null;
    public double timeStamp = 11;
    public double fieldStrength = 1;
    public double latitude = 2;

    public Measurement(double timeStamp, double fieldStrength, double latitude) {
        //this.img = img;
        this.timeStamp = timeStamp;
        this.fieldStrength = fieldStrength;
        this.latitude = latitude;
    }
}
