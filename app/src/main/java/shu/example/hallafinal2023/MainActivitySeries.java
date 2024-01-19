package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivitySeries extends AppCompatActivity {
    private FloatingActionButton faBaDd;
    private SearchView srchS;
    private Spinner spnrS;
    private ListView istvS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_series);
        spnrS=findViewById(R.id.spnrS);
        istvS=findViewById(R.id.istvS);
        faBaDd=findViewById(R.id.faBaDd);
        faBaDd.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivitySeries.this, AddMovie.class);
                startActivity(i);
                finish();
            }
        });
    }
}