package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;

import com.google.android.material.textfield.TextInputEditText;

public class rating extends AppCompatActivity {
    private TextInputEditText comment;
    private RatingBar ratingTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reating);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsltemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sitting) {
        }
        return true;
    }
        if (item.getItemId() == R.id.logout) {
        }
        return true;
    }

}