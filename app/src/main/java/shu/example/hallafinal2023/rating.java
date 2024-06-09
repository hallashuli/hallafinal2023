package shu.example.hallafinal2023;

import androidx.annotation.NonNull;
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
        comment=findViewById(R.id.comment);
        ratingTV=findViewById(R.id.ratingTV);
    }



}
