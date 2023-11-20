package shu.example.hallafinal2023;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import shu.example.hallafinal2023.MyData.AppDatabase;
import shu.example.hallafinal2023.MyData.myuser.MyUserQuery;
import shu.example.hallafinal2023.MyData.myuser.Myuser;

public class SingUp extends AppCompatActivity {
    private Button btnCancel;
    private TextInputEditText etEmail;
    private TextInputEditText etPhonenum;
    private TextInputEditText etUsername2;
    private TextInputEditText etPassword2;
    private TextInputEditText etRepassword;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        etEmail = findViewById(R.id.etEmail);
        etPhonenum = findViewById(R.id.etPhonenum);
        etUsername2 = findViewById(R.id.etUsername2);
        etPassword2 = findViewById(R.id.etPassword2);
        etRepassword = findViewById(R.id.etRepassword);
    }

    public void onClickSingupToMainactivity(View v) {
        CkeckDetials();
    }

    private void CkeckDetials() {
        boolean isAllok = true; // يحوي نتيجة فحص الحقول ان كانت  السليمة
        String email = etEmail.getText().toString();
        //استخراج النص كلمة المرور
        String password = etPassword2.getText().toString();
        //استخراج نص الذي يحوي على الاسم
        String name = etUsername2.getText().toString();
        // استخراج النص الذي يحوي على كلمة المرور الجديدة
        String rePaswword = etRepassword.getText().toString();
        //
        String phoneNumber = etPhonenum.getText().toString();
        //فحص الايميل ان كان طوله اقل من 6 او لا يحوي على @ فهو خطأ
        if (email.length() < 6 || email.contains("@") == false) {
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل الايميل
            etEmail.setError("worng email");
        }
        //فحص كلمة المرور اذا كانت اقل من 6 او تحتوي على فراغ
        if (password.length() < 6 || password.contains(" ") == true) {
            //تعديل المتغير على ان يعطي نتيجة خاطئة
            isAllok = false;
            //عرض نتيجة خطأ في حقل كلمة المرور
            etPassword2.setError("worng password");
        }
        //فحص الاسم يجب ان لا يحتوي على اقل من 3 حروف
        if (name.length() < 3) {
            //تعديل المتغير على ان يعطي نتيجة خاطئة
            isAllok = false;
            //عرض نتيجة اسم خاطئ في حقل الاسم
            etUsername2.setError("worng name");
        }
        //فحص اذا كانت كلمة المرور الجديدة نفس الكلمة القديمة(لتأكيد كبمة المرور)
        if (rePaswword.equals(password) == false) {
            //تعديل المتغير على ان يعطي نتيجة خاطئة
            isAllok = false;
            //عرض نتيجة خطأ في الحقل
            etRepassword.setError("worng password");
        }
        //فحص رقم الهاتف اذا صالح ام لا
        if (phoneNumber.length() != 10) {
            //تعديل المتغير على ان يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في الحقل
            etPhonenum.setError("worng phone number");
        }
        if (isAllok) {
            if (isAllok) {
                AppDatabase dp = AppDatabase.getDB(getApplicationContext());
                MyUserQuery MyUserQuery = dp.getUserQuaery();
                //فحص هل البريد الالكتروني موجود من قبل
                if (MyUserQuery.checkEmail(email) != null) {
                    etEmail.setError("found Email");
                } else //ان لم يكن البريد موجودًا نقوم ببناء كائن للمستعمل وادخاله في الجدولMyuser المستعملين
                {
                    //بناء كائن
                    Myuser myuser = new Myuser();
                    //تحديد القيم الصفات بالقيم التي استخرجناها
                    myuser.email = email;
                    myuser.fullName = name;
                    myuser.passw = password;
                    //اضافة كائن الجديد في الجدول
                    MyUserQuery.insert(myuser);
                    //اغلاق الشاشة الحالية
                }
            }


        }
    }
}

