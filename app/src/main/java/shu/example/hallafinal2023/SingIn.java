package shu.example.hallafinal2023;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import shu.example.hallafinal2023.MyData.AppDatabase;
import shu.example.hallafinal2023.MyData.myuser.Myuser;
import shu.example.hallafinal2023.MyData.myuser.MyUserQuery;

public class SingIn extends AppCompatActivity {
    private TextInputEditText etEmail2;
    private TextInputEditText etPass;
    private Button btnSingin;
    private Button btnSingup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);
        //توثيق صفات
        etEmail2 = findViewById(R.id.etEmail2);
        etPass = findViewById(R.id.etPassword);
    }

    public void onClickSinginToSingup(View v) {
        //to open new activity from current to next activity
        Intent i = new Intent(SingIn.this, SingUp.class);
        startActivity(i);
    }

    public void onClickSinginToMainActivitychoose(View v) {
        ckeckEmailPassw();
    }
    //فحص الايميل و الباسوورد
    private void ckeckEmailPassw() {
        boolean isAllok = true; // يحوي نتيجة فحص الحقول ان كانت  السليمة
        //استخراج نص البريد الالكتروني
        String email = etEmail2.getText().toString();
        //استخراج النص كلمة المرور
        String password = etPass.getText().toString();
        //فحص الايميل ان كان طوله اقل من 6 او لا يحوي على @ فهو خطأ
        if (email.length() < 6 || email.contains("@") == false) {
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل الايميل
            etEmail2.setError("worng email");
        }
        //فحص الايميل ان كان طوله اقل من 6 او لا يحوي على @ فهو خطأ
        if (password.length() < 8 || password.contains(" ") == true) {
            isAllok = false;
            etPass.setError("worng password");
        }
        if (isAllok) {
            Toast.makeText(this, "All Ok", Toast.LENGTH_SHORT).show();
            //بناء قاعدة بيانات و ارجاع المؤشر عليها
            AppDatabase db = AppDatabase.getDB(getApplicationContext());
            //مؤشر لكائن عمليات الجدول
            MyUserQuery userQuery = db.getUserQuaery();
            //ان لم يكن موجود null استدعاء العملية التي تنفذ استعلام الذي يفحص البريد و كلمة المرور و يعيد كائنًاان كان موجود
            Myuser myuser = userQuery.checkEmailPassw(email, password);
            if (myuser == null)//هل لا يوجد كائن حسب الايميل و الباسورود
                Toast.makeText(this, "worng email or worng password", Toast.LENGTH_SHORT).show();
            else {//أن كان هنالك حساب حساب ايميل او باسورود ننتقل الى الشاشة الرئيسية
                Intent i = new Intent(SingIn.this, MainActivitychoose.class);
                startActivity(i);
                finish();
            }
            //FireBase
            if (isAllok) {
                //كائن لعملية تسجيل
                FirebaseAuth auth = FirebaseAuth.getInstance();
                //כניסה לחשבון בעזרת מיל ו סיסמא
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override //התגובה שמתקבל הנסיון הרשיום בען
                    public void onComplete(@NonNull Task<AuthResult> task) // הפרמטר מכיל מידע מהשרת על תוצאת הבקשה לרישום
                    {
                        if (task.isSuccessful()) {//
                            Toast.makeText(SingIn.this, "Signing up Succeeded", Toast.LENGTH_SHORT).show();
                            //מעבר למסך הראשי
                            Intent intent=new Intent(SingIn.this,MainActivitychoose.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(SingIn.this, "Signing up Failed", Toast.LENGTH_SHORT).show();
                            etEmail2.setError(task.getException().getMessage());//הצגת הודעות השגיאה שהקבלה מהען
                        }
                    }
                });
            }
        }

    }

}
