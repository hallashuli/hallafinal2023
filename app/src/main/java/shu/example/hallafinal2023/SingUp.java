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
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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

    public void onClickSingupToMainactivityChoose(View v) {
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
        //فحص رقم الهاتف
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
                    finish();
                }
            }
        }
    }
    public void onClickSingUptoSingIn(View v) {
        //to open new activity from current to next activity
        Intent i = new Intent(SingUp.this, SingIn.class);
        startActivity(i);
    }
    //FireBase
    private void checkAndSignUP_FB() {
        boolean isAllok = true; // يحوي نتيجة فحص الحقول ان كانت  السليمة
        String email = etEmail.getText().toString();
        //استخراج النص رقم الهاتف
        String phone=etPhonenum.getText().toString();
         //استخراج النص كلمة المرور
        String password = etPassword2.getText().toString();
        //استخراج نص الذي يحوي على الاسم
        String name = etUsername2.getText().toString();
        // استخراج النص الذي يحوي على كلمة المرور الجديدة
        String rePaswword = etRepassword.getText().toString();
        //فحص الايميل ان كان طوله اقل من 6 او لا يحوي على @ فهو خطأ
        if (email.length() < 6 || email.contains("@") == false) {
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل الايميل
            etEmail.setError("worng email");
        }
        //فحص كلمة المرور اذا كانت اقل من 8 او تحتوي على فراغ
        if (password.length() < 8 || password.contains(" ") == true) {
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
        if (isAllok) {
            //كائن لعملية تسجيل
            FirebaseAuth auth = FirebaseAuth.getInstance();
            //יצירת חשבון בעזרת מיל ו סיסמא
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override //התגובה שמתקבל הנסיון הרשיום בען
                public void onComplete(@NonNull Task<AuthResult> task) // הפרמטר מכיל מידע מהשרת על תוצאת הבקשה לרישום
                {
                    if (task.isSuccessful()) {//
                        saveUser_FB(email,name,phone,password);
                        Toast.makeText(SingUp.this, "Signing up Succeeded", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SingUp.this, "Signing up Failed", Toast.LENGTH_SHORT).show();
                        etEmail.setError(task.getException().getMessage());//
                    }
                }
            });
        }
    }
    private void saveUser_FB(String email, String name, String phone, String password) {
        //مؤشر لقاعدة البيانات
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //استخراج الرقم المميز للمستعمل الذي سجل الدخول
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //بناء الكائن الذي سيتم حفظه
        Myuser user = new Myuser();
        user.setEmail(email);
        user.setFullName(name);
        user.setPhone(phone);
        user.setPassw(password);
        user.setId(uid);
        //اضافة كائن لمجموعة المستعملين ومعالج حدث لفحص نجاح الاضافة
        db.collection("MyUsers").document(uid).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SingUp.this, "Succeed to add User", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SingUp.this, "Failed to add user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClickSingupToSinginFireBace (View v){
        checkAndSignUP_FB();
    }
    public void onClickCancelADDFireBace (View v){
        finish();
    }
}
