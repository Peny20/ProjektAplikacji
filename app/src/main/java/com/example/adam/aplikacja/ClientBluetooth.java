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

    }
    public void stop1()
    {

        try{
            out = mmSocket.getOutputStream();
            byte[] writebuffer1 = {  'b', 'b', 'b'};

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
            Log.d("INFO", " Połączono z serwerem!");
            out = mmSocket.getOutputStream();
            byte[] writebuffer = {'a', 'a', 'a'};
            out.write(writebuffer);


            in = mmSocket.getInputStream();
            while (in != null) {
                char a = (char) in.read();
                if (a == 'a') {
                    byte[] buffer = new byte[4];
                    int suma = 0;
                    in.read(buffer, 0, 4);


                    for (int i = 0; i < 2; i++) {

                        suma = suma + unsignedToBytes(buffer[i]);



                        int result1 = (unsignedToBytes(buffer[2]) << 8) + unsignedToBytes(buffer[3]);

                        if (suma == result1) {
                            int silownik = (unsignedToBytes(buffer[0]) << 8) + unsignedToBytes(buffer[1]);


                            Log.d("INFO", "Siła" + silownik);


                            if ((silownik) > 800) {
                                if (!Preferencje.wezTryb(kontekst)) {


                                    menu.cos1 = true;


                                    menu.cos = true;
                                }

                                if (Preferencje.wezTryb(kontekst)) {
                                    if (skocz) {

                                        menu.cos1 = true;

                                        menu.cos = true;

                                        skocz = false;
                                    }
                                }


                            }
                            if ((silownik) < 800) {
                                if (Preferencje.wezTryb(kontekst)) {
                                    skocz = true;
                                }


                                if (!Preferencje.wezTryb(kontekst)) {
                                    menu.cos = false;
                                }

                            }

                        }

                    }


                }


            }
        }catch (Exception ce) {
            try {
                mmSocket.close();
            } catch (Exception cle) { }
        }

    }
}
