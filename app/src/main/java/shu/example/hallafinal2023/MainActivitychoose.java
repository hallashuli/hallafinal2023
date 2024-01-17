package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivitychoose extends AppCompatActivity {
    private Button bM;
    private Button bS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitychoose);
        @Override
        public void onClickChooseToMovie(View view) {
            Intent i=new Intent(MainActivitychoose.this, MainActivityMovei.class);
            startActivity(i);
            finish();
        }
    });
    }

