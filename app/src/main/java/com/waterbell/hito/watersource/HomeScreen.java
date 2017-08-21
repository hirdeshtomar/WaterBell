package com.waterbell.hito.watersource;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.waterapp.search.SearchSuppliers;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class HomeScreen extends Activity {
    Button buyBtn;
    TextView welcomeMsg;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        welcomeMsg = (TextView) findViewById(R.id.welcomeMsg);
        Typeface porkys = Typeface.createFromAsset(getAssets(), getString(R.string.typeface_porkys));
        welcomeMsg.setTypeface(porkys);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        welcomeMsg.setAnimation(animFadeIn);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

               if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {

                    Intent intent = new Intent(HomeScreen.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    ParseUser currentUser = ParseUser.getCurrentUser();

                    if (currentUser != null) {
                        Intent intent = new Intent(HomeScreen.this, SearchSuppliers.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(HomeScreen.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
