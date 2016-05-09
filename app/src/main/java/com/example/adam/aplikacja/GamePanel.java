package com.example.adam.aplikacja;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Adam on 03.01.2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -5;
    private MainThread thread;
    //private Background bg;
    static public Player player;
    private static final int MAX_CLICK_DURATION = 200;
    private long startClickTime;
    Context kontekst;
    private ArrayList<Slup> slup;
    private ArrayList<Slup> slup1;
    private ArrayList<Background> bg;
    private Random rand = new Random();
    private long slupStartTime;
    private double przyciStartTime;
    private int wys;
    public boolean newGameCreated;
    private long startReset;
    public boolean reset;
    //public boolean started;
    private int best;
    public long iloscprz;
    private Bitmap res;
    private Bitmap res1;
    private Bitmap res2;
    public GamePanel(Context context) {
        super(context);
        kontekst=context;

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        //best=0;

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        DataDbAdapter dataDbAdapter;
        boolean retry = true;
        int counter =0;
        while(retry && counter<1000)
        {
            counter++;
            try{
                thread.setRunning(false);
                thread.join();
                //((Activity)getContext()).finish();
                retry = false;
                thread = null;

            }catch(InterruptedException e){e.printStackTrace();}

        }
        if (Preferencje.wezTryb(kontekst)) {

                dataDbAdapter = new DataDbAdapter(kontekst);
                dataDbAdapter.open();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            double przyciElapsed = ((double)System.currentTimeMillis()-przyciStartTime)/(1000*60);
            System.out.println("Czas gry " +  przyciElapsed );
                if (!dataDbAdapter.updateData(date, iloscprz, przyciElapsed))
                {
                    dataDbAdapter.insertData(date,iloscprz, przyciElapsed);
                }



            dataDbAdapter.close();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        menu.cos1=false;
        iloscprz =0;
        //best=Preferencje.wezWynik(kontekst);
        SharedPreferences prefs = this.getContext().getSharedPreferences("Ptaszek", Context.MODE_PRIVATE);
        best = prefs.getInt("Wynik", 0); //0 is the default value﻿
        bg = new ArrayList<Background>();
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.bird),40,40, 2);
        res = BitmapFactory.decodeResource(getResources(), R.drawable.slup);
        res1 = BitmapFactory.decodeResource(getResources(),R.drawable.slup1);
        res2 = BitmapFactory.decodeResource(getResources(),R.drawable.tloandroid);
        slup = new ArrayList<Slup>();
        slup1 = new ArrayList<Slup>();
        bg.add(new Background(res2 , 0));
        bg.add(new Background(res2, WIDTH));
        bg.add(new Background(res2, WIDTH * 2));
        slupStartTime = System.nanoTime();
        przyciStartTime = System.currentTimeMillis();

        //we can safely start the game loop
        //if (thread.getState() == Thread.State.TERMINATED) {
        //    thread = new MainThread(getHolder(), this);
        //}
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(Preferencje.wezDotyk(kontekst)) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startClickTime = Calendar.getInstance().getTimeInMillis();
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if (clickDuration < MAX_CLICK_DURATION) {
                    if (!player.getPlaying()&& newGameCreated && reset) {
                        player.setPlaying(true);
                    }
                    if(player.getPlaying())
                    {

                       // if(!started)started = true;
                        reset = false;
                        player.setUp(true);
                    }
                    return true;
                }


                player.setUp(false);
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    public void update() {
        if(player.getPlaying()) {
            for(int i = 0; i<bg.size();i++)
            {
                bg.get(i).update();
                if(bg.get(i).getX()<-900)
                {
                    bg.get(i).setX(bg.get(i).getX()+(3*WIDTH));
                    //break;
                }
            }
            player.update();
            //add missiles on timer
            long slupElapsed = (System.nanoTime()-slupStartTime)/1000000;
            if(slupElapsed >(2400)){

                System.out.println("making slup");
                //first missile always goes down the middle
                if(slup.size()==0)
                {
                    slup.add(new Slup(res ,WIDTH + 10, (HEIGHT/2-880), 60, 800, player.getScore()));

                    slup1.add(new Slup(res1,WIDTH + 10, (HEIGHT/2+80), 60, 800, player.getScore()));
                }
                else
                {

                    wys=(int)(rand.nextDouble()*(HEIGHT));
                    if(wys<130) wys=wys +135;
                    if (wys>350) wys=wys -135;
                    slup.add(new Slup(res,WIDTH+10, (wys-880),60,800, player.getScore()));
                    slup1.add(new Slup(res1,WIDTH + 10, (wys+80), 60, 800, player.getScore()));

                }

                //reset timer
                slupStartTime = System.nanoTime();
            }

            if(player.getY() <=-20 || player.getY()>= 440  )
            {
                player.setPlaying(false);
            }

            //loop through every missile and check collision and remove
            for(int i = 0; i<slup.size();i++)
            {
                //update missile
                slup.get(i).update();
                slup1.get(i).update();

                if(collision(slup.get(i),player) || collision(slup1.get(i),player))
                {
                    slup.remove(i);
                    slup1.remove(i);
                    player.setPlaying(false);
                    break;
                }
                if(slup.get(i).getX()<=(player.getX()+3) && slup.get(i).getX()>=(player.getX()-3))
                {
                    player.addScore();
                    System.out.println("Ilosc punktow " + player.getScore());

                }
                //remove missile if it is way off the screen
                if(slup.get(i).getX()<-600)
                {
                    slup.remove(i);
                    slup1.remove(i);
                    //break;
                }
            }
        }
        else{
            player.resetDY();
            if(!reset)
            {
                newGameCreated = false;
                startReset = System.nanoTime();
                reset = true;
            }
            long resetElapsed = (System.nanoTime()-startReset)/1000000;
            if(resetElapsed > 200 && !newGameCreated)
            {
                newGame();
            }
        }
    }
    public boolean collision(GameObject a, GameObject b) {
        if (Rect.intersects(a.getRectangle(), b.getRectangle()))
        {
            return true;
        }
        return false;
    }
    @Override
    public void draw(Canvas canvas)
    {
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            for(Background m: bg)
            {
                m.draw(canvas);
            }
            player.draw(canvas);
            for(Slup m: slup)
            {
                m.draw(canvas);
            }
            for(Slup m: slup1)
            {
                m.draw(canvas);
            }
            drawText(canvas);
            canvas.restoreToCount(savedState);
        }
    }
    public void newGame() {
        slup.clear();
        slup1.clear();
        bg.clear();
        bg.add(new Background(res2, 0));
        bg.add(new Background(res2, WIDTH));
        bg.add(new Background(res2, WIDTH * 2));


        if(player.getScore()>best)
        {
            best=player.getScore();
            SharedPreferences sharedPref = this.getContext().getSharedPreferences("Ptaszek", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("Wynik", best);
            editor.commit();


        }

        player.resetDY();
        player.resetScore();
        player.setY(HEIGHT/2);


        newGameCreated = true;


    }
    public void drawText(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("WYNIK: " + player.getScore(), 10, HEIGHT - 10, paint);
        canvas.drawText("NAJLEPSZY: " + best, WIDTH - 215, HEIGHT - 10, paint);

        if(!player.getPlaying()&&newGameCreated&&reset)
        {
            Paint paint1 = new Paint();
            paint1.setTextSize(40);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("Przyciśnij aby zacząć", WIDTH/2-50, HEIGHT/2, paint1);

        }
    }
}

