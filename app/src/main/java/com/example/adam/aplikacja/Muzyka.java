package com.example.adam.aplikacja;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Adam on 10.01.2016.
 */
public class Muzyka {
    private static MediaPlayer mp = null;
    public static void play (Context kontekst, int zasob)
    {
        stop(kontekst);
        if(Preferencje.wezMuzyka(kontekst))
        {
            mp= MediaPlayer.create(kontekst,zasob);
            mp.setLooping(true);
            mp.start();
        }
    }
    public static void stop(Context context)
    {
        if(mp!=null)
        {
            mp.stop();
            mp.release();
            mp= null;
        }
    }
}
