package com.example.myapplication;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

public class SoundPlayer {
    private static int sound_click;
    private static int sound_ost;
    static SoundPool soundPool;
    private static int current;

    static  void init(Context context){
        AudioAttributes
                audioAttributes
                = new AudioAttributes
                .Builder()
                .setUsage(
                        AudioAttributes
                                .USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(
                        AudioAttributes
                                .CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder().setMaxStreams(3)
                .setAudioAttributes(
                        audioAttributes).build();
        sound_click = soundPool.load(context, R.raw.click, 1);
        sound_ost = soundPool.load(context, R.raw.ost, 1);



    }

    public static void playClick(){
        soundPool.play(
                sound_click, 1, 1, 0, 0, 1);
        soundPool.autoPause();
    }
    public static void playOst(){
       current =  soundPool.play(
                sound_ost, 1, 1, 0, -1, 1);
    }
    public static void stopOst(){
        soundPool.stop(current);
    }
}
