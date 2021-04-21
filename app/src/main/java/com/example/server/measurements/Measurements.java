package com.example.server.measurements;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class Measurements extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private static Measurements instance;
    public static boolean isRunning;

    public static  Measurements getInstance(){
         isRunning = false;
         instance = new Measurements("MyService");
         return instance;
    }

    private Measurements(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        isRunning = true;

    }
}
