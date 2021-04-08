package com.example.server.functionalities;

import android.content.Context;
import android.media.AudioManager;
import androidx.appcompat.app.AppCompatActivity;

public class Audio extends AppCompatActivity {

    public Audio(){}

public void setVolume(int volLevel){
    AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    int maxLoud = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    System.out.println("the max volume is" + maxLoud);
    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volLevel, 0);

}
    /* val audioManager = applicationContext.getSystemService(AUDIO_SERVICE) as AudioManager
    val maxLoud = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    volumeLevel.text = maxLoud.toString()
    var loudness = 5
        raiseVolBtn.setOnClickListener {
        println("button pressed")
        loudness++
        //STREAM_VOICE_CALL
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, loudness, 0);
        volumeLevel.text = loudness.toString()
    }
        resetBtn.setOnClickListener {
        loudness = 0
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, loudness, 0);
        volumeLevel.text = "reseted"
    }

     */


}
