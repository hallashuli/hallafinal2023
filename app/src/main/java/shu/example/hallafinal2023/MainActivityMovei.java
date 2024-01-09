package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivityMovei extends AppCompatActivity {
    private FloatingActionButton fabAdd;
    private Button btnM;
    private SearchView srchv;
    private Spinner spnr;
    private ListView istTv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_movei);
        btnM=findViewById(R.id.btnM);
        spnr=findViewById(R.id.spnrM);
        istTv=findViewById(R.id.istTvM);
        fabAdd=findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivityMovei.this, Addmovei.class);
                startActivity(i);
                finish();
            }
        });


        }
    }
