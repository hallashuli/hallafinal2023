package shu.example.hallafinal2023;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class singup extends AppCompatActivity
{
    private Button btnCancel;
    private TextInputLayout etEmail;
    private TextInputLayout etPhonenum;
    private TextInputLayout etUsername2;
    private TextInputLayout etPassword2;
    private TextInputLayout etRepassword;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        etEmail=findViewById(R.id.etEmail);
        etPhonenum=findViewById(R.id.etPhonenum);
        etUsername2=findViewById(R.id.etUsername2);
        etPassword2=findViewById(R.id.etPassword2);
        etRepassword=findViewById(R.id.etRepassword);
    }
}