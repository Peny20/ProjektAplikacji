package com.example.adam.aplikacja;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by Adam on 03.01.2016.
 */
public class Background extends GameObject{

    private Bitmap image;
    private int dx;


    public Background(Bitmap res, int x)
    {
        this.image = res;
        this.x = x;
        this.y = y;
        this.dx = GamePanel.MOVESPEED;
    }
    public void update()
    {
        x+=dx;

    }
    public void draw(Canvas canvas)
    {
        try{
            canvas.drawBitmap(image,x,y,null);
        }catch(Exception e){}

    }


}
