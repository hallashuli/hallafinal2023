package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler h = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                //to open new activity from current to next
                Intent i = new Intent(SplashScreen.this, SingIn.class);
                startActivity(i);
                //to close current activity
                finish();
            }
        };
        h.postDelayed(r, 3000);
    }
}