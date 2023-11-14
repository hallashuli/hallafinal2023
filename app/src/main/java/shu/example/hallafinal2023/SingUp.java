package shu.example.hallafinal2023;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SingUp extends AppCompatActivity
{
    private Button btnCancel;
    private TextInputEditText etEmail;
    private TextInputEditText etPhonenum;
    private TextInputEditText etUsername2;
    private TextInputEditText etPassword2;
    private TextInputEditText etRepassword;
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
    public void onClickSingupToMainactivity (View v)
    {
        //to open new activity from current to next activity
        Intent i= new Intent(SingUp.this,  MainActivity.class);
        startActivity(i);
    }
    private void ckeckEmailPassw() {
        boolean isAllok = true; // يحوي نتيجة فحص الحقول ان كانت  السليمة
        String email = etEmail.getText().toString();
        //استخراج النص كلمة المرور
        String password=etPassword2.getText().toString();
        //فحص الايميل ان كان طوله اقل من 6 او لا يحوي على @ فهو خطأ
        if (email.length()<6 || email.contains("@")==false) {
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل الايميل
            etEmail.setError("worng email");
        }
        if (password.length()<8 || password.contains(" ")==true){
            isAllok=false;
            etPassword2.setError("worng password");
        }
        if (isAllok){
            Toast.makeText(this, "All Ok", Toast.LENGTH_SHORT).show();
        }

    }

}