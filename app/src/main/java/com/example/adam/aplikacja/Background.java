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
    //private ArrayList<Background> bg;

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
        //if(x<-(GamePanel.WIDTH)){
        //    x=0;
      //  }
    }
    public void draw(Canvas canvas)
    {
        try{
            canvas.drawBitmap(image,x,y,null);
        }catch(Exception e){}
        //canvas.drawBitmap(image, x, y,null);
       // if(x<0)
      //  {
       //     canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
//
       // }
    }


}
