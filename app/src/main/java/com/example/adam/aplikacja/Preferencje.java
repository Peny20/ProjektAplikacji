package com.example.adam.aplikacja;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.View;

/**
 * Created by Adam on 02.01.2016.
 */
public class Preferencje extends PreferenceActivity {
    private static final String OPC_MUZYKA = "muzyka";
    private static final boolean OPC_MUZYKA_DOM = true;
    private static final String OPC_TRYB = "tryb";
    private static final boolean OPC_TRYB_DOM = false;
    private static final String OPC_DOTYK = "dotyk";
    private static final boolean OPC_DOTYK_DOM = false;
    private static final String OPC_MAC = "mac";
    private static final String OPC_MAC_DOM = "98:D3:31:40:1D:DC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        addPreferencesFromResource(R.xml.ustawienia);
    }
    public static boolean wezMuzyka (Context kontekst)
    {
        return PreferenceManager.getDefaultSharedPreferences(kontekst)
                .getBoolean(OPC_MUZYKA, OPC_MUZYKA_DOM);
    }
    public static boolean wezTryb (Context kontekst)
    {
        return PreferenceManager.getDefaultSharedPreferences(kontekst)
                .getBoolean(OPC_TRYB, OPC_TRYB_DOM);
    }
    public static boolean wezDotyk (Context kontekst)
    {
        return PreferenceManager.getDefaultSharedPreferences(kontekst)
                .getBoolean(OPC_DOTYK, OPC_DOTYK_DOM);
    }
    public static String wezMac (Context kontekst)
    {
        return PreferenceManager.getDefaultSharedPreferences(kontekst)
                .getString(OPC_MAC, OPC_MAC_DOM);
    }



}
