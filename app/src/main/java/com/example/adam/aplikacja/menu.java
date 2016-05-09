package com.example.adam.aplikacja;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.concurrent.Exchanger;


public class menu extends Activity {
    Button przycisk;
    Button przycisk1;
    Button przycisk10;
    Button przycisk11;
    Button przycisk9;
    Button przycisk12;
    public static boolean cos=false;
    public static boolean cos1=false;
    static ClientBluetooth thread1;
    public static boolean watek=false;
    Exchanger exchanger = new Exchanger();
    Context kontekst;

    //public void onClick(View v) {
       // Intent i = new Intent(this,Bletooth.class);
      //  startActivity(i);

    //}
    private static final String ZNACZNIK= "Tryb Gry";
    private void otworzDialogStatystyki()
    {

        new AlertDialog.Builder(this)
                .setTitle (R.string.tytul_statystyki)
                .setItems(R.array.statystyki,
                new DialogInterface.OnClickListener()
                {

                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                        uruchomStatystyki(i);
                    }
                })
                .show();
    }
    private void uruchomStatystyki(int i)
    {
        if (i==1)
            {
                Intent statystyka = new Intent(menu.this,WykresyLekarz.class);
                startActivity(statystyka);
            }
        if (i==0)
        {
            Intent statystyka = new Intent(menu.this,StatystykiPacjent.class);
            startActivity(statystyka);
        }

        Log.d(ZNACZNIK, "kliknięto " + i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        kontekst=this;
        PreferenceManager.setDefaultValues(this, R.xml.ustawienia, false);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        przycisk = (Button) findViewById(R.id.button);
        przycisk1 = (Button) findViewById(R.id.button8);
        przycisk10 = (Button) findViewById(R.id.button10);
        przycisk11 = (Button) findViewById(R.id.button11);
        przycisk9 = (Button) findViewById(R.id.button9);
        przycisk12 = (Button) findViewById(R.id.button12);
        Log.d("INFO", "Uruchomiono program");
       przycisk.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
               if (!ba.isEnabled()) {
                   Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                   startActivityForResult(i, 1);
               }
               if (BluetoothAdapter.checkBluetoothAddress(Preferencje.wezMac(kontekst))) {
                   BluetoothDevice serwer = ba.getRemoteDevice(Preferencje.wezMac(kontekst));
                   thread1 = new ClientBluetooth(serwer, kontekst);
                   thread1.start();
                   watek = true;
               } else {
                   Log.d(ZNACZNIK, "Zły adress bluetooth ");
               }

           }
       });
        przycisk10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        Intent i = new Intent(menu.this,Informacje.class);
                        startActivity(i);




            }
        });
        przycisk12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                otworzDialogStatystyki();




            }
        });
        przycisk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent gra = new Intent(menu.this, Game.class);
                startActivity(gra);




            }
        });
        przycisk11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(menu.this, Preferencje.class));




            }
        });
        przycisk9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                boolean retry = true;
                int counter =0;
                if(watek) {
                    if(thread1.isAlive())
                    {
                        while (retry && counter < 1000) {
                            counter++;
                            try {
                                thread1.stop1();
                                thread1.join();
                                retry = false;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
                finish();




            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        Muzyka.play(this, R.raw.rock_a_bye_baby_instrumental);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Muzyka.stop(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater wypelniacz = getMenuInflater();
        wypelniacz.inflate(R.menu.menu_menu, menu);

        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_bletooth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem element) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
       //     return true;
       // }

       // return super.onOptionsItemSelected(item);
        switch (element.getItemId())
        {
            case R.id.ustawienia:
                startActivity (new Intent (menu.this, Preferencje.class));
                return true;
        }
        return false;
    }


}
