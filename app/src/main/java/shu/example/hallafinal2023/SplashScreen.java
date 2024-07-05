package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity
{
    //وظيفته هي إنشاء شاشة البداية (Splash Screen) التي تظهر للمستخدم عند بدء تشغيل التطبيق لفترة قصيرة قبل الانتقال إلى الشاشة الرئيسية أو شاشة تسجيل الدخول.

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
        //إظهار شاشة البداية للمستخدم لمدة 3 ثوانٍ عند بدء تشغيل التطبيق، ثم الانتقال تلقائيًا إلى نشاط تسجيل الدخول وإغلاق شاشة البداية.
    }
}