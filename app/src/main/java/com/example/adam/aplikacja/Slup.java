package com.example.adam.aplikacja;

/**
 * Created by Adam on 10.03.2016.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Slup extends GameObject{
    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;
    private Bitmap image;

    public Slup(Bitmap res, int x, int y, int w, int h, int s)
    {
        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;
        image = res;
        //speed = 7 + (int) (rand.nextDouble()*score/30);
        speed=5;
        //cap missile speed
        //if(speed>40)speed = 40;

       // Bitmap[] image = new Bitmap[numFrames];

       // spritesheet = res;

        //for(int i = 0; i<image.length;i++)
       // {
        //    image[i] = Bitmap.createBitmap(spritesheet, 0, i*height, width, height);
       // }

        //animation.setFrames(image);
        //animation.setDelay(100-speed);
        width = width-5;
        height = height-5;

    }
    public void update()
    {
        x-=speed;
      //  animation.update();
    }
    public void draw(Canvas canvas)
    {
        try{
            canvas.drawBitmap(image,x,y,null);
        }catch(Exception e){}
    }

    @Override
    public int getWidth()
    {
        //offset slightly for more realistic collision detection
        return width-10;
    }
}
