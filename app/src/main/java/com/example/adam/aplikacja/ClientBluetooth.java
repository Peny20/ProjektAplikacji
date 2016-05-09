package com.example.adam.aplikacja;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.UUID;


import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

public class ClientBluetooth extends Thread {
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private TextView t4;
    public InputStream in;
    public OutputStream out;
    private boolean skocz = false;
    Context kontekst;

    public ClientBluetooth(BluetoothDevice device, Context context) {
        kontekst=context;
        BluetoothSocket tmp = null;

        mmDevice = device;
        try {
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            tmp = device.createRfcommSocketToServiceRecord(uuid);
        } catch (Exception e) { }
        mmSocket = tmp;
       // t4=t3;
    }
    public void stop1()
    {

        try{
            out = mmSocket.getOutputStream();
            byte[] writebuffer1 = {  'b', 'b', 'b'};
            // writebuffer = {  a, a, 'a'}
            out.write(writebuffer1);
            this.mmSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public static int unsignedToBytes1(int b) {
        return b & 0xFF;
    }
    public static int unsignedToBytes(byte b) {
        return b & 0xFF;
    }
    public void run() {
 int po =0;
        try {
            Log.d("INFO", "Próba połączenia....");
            mmSocket.connect();
            Log.d("INFO"," Połączono z serwerem!");
           // BufferedReader in = new BufferedReader(new InputStreamReader(mmSocket.getInputStream()));
            out = mmSocket.getOutputStream();
            byte[] writebuffer = {  'a', 'a', 'a'};
            // writebuffer = {  a, a, 'a'}
            out.write(writebuffer);


                    in = mmSocket.getInputStream();
            while(in!=null){
                 char a =(char)in.read();
                if(a == 'a' )
                {
                    byte[] buffer = new byte [6];
                    byte[] buffer1 = new byte [1];
                    char[] znaki = new char [5];
                    int [] buffer2 = new int [6];
                    int suma =0;
                    in.read( buffer, 0, 6);
                    //int result=(buffer[0]<<8)+buffer[1];
                   // String str =Integer.toString(result);
                    // String str = new String(buffer, Charset.forName("utf-16"));
                    //Log.d("INFO", "Siłownik 1 " + str);
                   // t4.setText(str);
                   // int result1=(buffer[2]<<8)+buffer[3];
                   // String str1 =Integer.toString(result1);
                    //Log.d("INFO", "Siłownik 2 " + str1);
                    //String text1 = new String(buffer1, "UTF-8");
                   // char result2= (char)buffer[4];
                   // String str2 =Character.toString(result2);
                   // int result1= (unsignedToBytes(buffer[0]) << 8) + unsignedToBytes(buffer[1]);
                   // Log.d("INFO",   "   " +  result1);

                    for (int i =0;i <4;i++) {
                        //buffer2[i] =  buffer[i];
                        suma = suma + unsignedToBytes(buffer[i]);
                    }
                    for (int i =0;i <6;i++) {
                        buffer2[i] =  unsignedToBytes(buffer[i]);

                    }

                   // for (int i =0;i <5;i++) {
                    //    znaki[i] = (char) buffer[i];
                   // }
                   // String str2 = String.copyValueOf(znaki);
                    //suma = CalculateCrc.CalculateCrc1(buffer2);
                    int result1= (unsignedToBytes(buffer[4]) << 8) + unsignedToBytes(buffer[5]);
                    //short result2 = (short) result1;
                    //if (suma == result1)
                    //{
                      //  Log.d("INFO", suma +  "   " + result1);
                    //}
                    if (suma == result1) {
                        int silownik = (unsignedToBytes(buffer[0]) << 8) + unsignedToBytes(buffer[1]);
                        int silownik1 = (unsignedToBytes(buffer[2]) << 8) + unsignedToBytes(buffer[3]);

                        Log.d("INFO", "Siła" + silownik + " " + silownik1);
                         //boolean skocz = false;

                        if ((silownik+silownik1)> 1600)
                        {
                            if(!Preferencje.wezTryb(kontekst)){


                                menu.cos1=true;


                                menu.cos = true;
                            }
/*
                            if(menu.cos)
                                 {    //skocz=true;
                                 Log.d("INFO", "Prawda");
                              }*/
                            if(Preferencje.wezTryb(kontekst)) {
                                if (skocz) {

                                    menu.cos1 = true;

                                    menu.cos = true;

                                    skocz = false;
                                }
                            }


                        }
                       if ((silownik+silownik1)<1600)
                        {
                            if(Preferencje.wezTryb(kontekst)) {
                                skocz = true;
                            }




                            if(!Preferencje.wezTryb(kontekst)) {
                                menu.cos = false;
                            }
/*
                         if(!menu.cos)
                          {    //skocz=true;
                              Log.d("INFO", "Fałsz");
                          }*/
                        }

                    }

                }



           }


        } catch (Exception ce) {
            try {
                mmSocket.close();
            } catch (Exception cle) { }
        }

    }
}
