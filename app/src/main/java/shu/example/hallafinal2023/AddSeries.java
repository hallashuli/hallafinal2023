package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class AddSeries extends AppCompatActivity {
    private TextInputEditText name2;
    private TextInputEditText Type2;
    private TextInputEditText lang2;
    private TextInputEditText num2;
    private EditText time2;
    private Button btn;
    private Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
            //توقيت الصفات
            name2=findViewById(R.id.name2);
            Type2=findViewById(R.id.type2);
            lang2=findViewById(R.id.lang2);
            num2=findViewById(R.id.num2);
            time2=findViewById(R.id.Time2);

    }
}