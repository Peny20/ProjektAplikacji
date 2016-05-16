package com.example.adam.aplikacja;

/**
 * Created by Adam on 03.01.2016.
 */
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{
    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }
    @Override
    public void run()
    {

        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount =0;
        long targetTime = 1000/FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if(menu.cos1)
                    {

                        if (!this.gamePanel.player.getPlaying()&& this.gamePanel.newGameCreated && this.gamePanel.reset) {
                            this.gamePanel.player.setPlaying(true);

                        }
                        menu.cos1=false;
                    }
                    if(menu.cos)
                    {
                        if(this.gamePanel.player.getPlaying())
                        {


                            this.gamePanel.reset = false;
                            this.gamePanel.player.setUp(true);
                            this.gamePanel.iloscprz ++;
                        }

                        menu.cos=false;

                    }
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                    timeMillis = (System.nanoTime() - startTime) / 1000000;
                    waitTime = targetTime-timeMillis;

                    if(waitTime>0)
                    {
                    try{
                        this.sleep(waitTime);
                    }catch(Exception e){}}

                    totalTime += System.nanoTime()-startTime;
                    frameCount++;
                    if(frameCount == FPS)
                    {
                        averageFPS = (frameCount/(totalTime/1000000000));
                        frameCount =0;
                        totalTime = 0;
                        System.out.println(averageFPS);
                    }
                }
            } catch (Exception e) {
            }
            finally{
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }


        }
    }
    public void setRunning(boolean running)
    {
        this.running=running;
    }
}
