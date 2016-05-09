package com.example.adam.aplikacja;

/**
 * Created by Adam on 02.01.2016.
 */
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
public class Informacje extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacje);
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
