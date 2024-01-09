package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class Addmovei extends AppCompatActivity {
    private TextInputEditText text1;
    private TextInputEditText text2;
    private TextInputEditText text3;
    private TextInputEditText text4;
    private EditText time;
    private Button btnsave2;
    private Button btncancel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovei);
        //
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        text4=findViewById(R.id.text4);
        time=findViewById(R.id.time);
    }
    public void onClickAddmoveiToReating(View v) {
        //to open new activity from current to next activity
        Intent i = new Intent(Addmovei.this, Reating.class);
        startActivity(i);
    }

}