package shu.example.hallafinal2023;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SingIn extends AppCompatActivity {
    private TextInputEditText etEmail2;
    private TextInputEditText etPass;
    private Button btnSingin;
    private Button btnSingup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);
        //
        etUsername=findViewById(R.id.etEmail2);
        etPass=findViewById(R.id.etPassword);
    }

    public void onClickSinginToSingup(View v) {
        //to open new activity from current to next activity
        Intent i = new Intent(SingIn.this, SingUp.class);
        startActivity(i);
    }

    public void onClickSinginToMainactivity(View v) {
        ckeckEmailPassw();

    }

    private void ckeckEmailPassw() {
        boolean isAllok = true; // يحوي نتيجة فحص الحقول ان كانت  السليمة
        //استخراج النص كلمة المرور
        String password=etPass.getText().toString();
        //فحص الايميل ان كان طوله اقل من 6 او لا يحوي على @ فهو خطأ
        if (password.length()<8 || password.contains(" ")==true)
        {
            isAllok=false;
            etPass.setError("worng password");
        }
        if (isAllok){
            Toast.makeText(this, "All Ok", Toast.LENGTH_SHORT).show();
        }

    }

}
