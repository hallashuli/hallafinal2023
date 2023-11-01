package shu.example.hallafinal2023;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class Singin extends AppCompatActivity {
    private TextInputLayout etUsername;
    private TextInputLayout etPassword;
    private Button btnSingin;
    private Button btnSingup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);
    }
}
