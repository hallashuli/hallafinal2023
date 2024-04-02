package shu.example.hallafinal2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import shu.example.hallafinal2023.MyData.MyFilmTable.Movei;

public class AddMovie extends AppCompatActivity {
    private TextInputEditText name1;
    private TextInputEditText Type1;
    private TextInputEditText lang1;
    private TextInputEditText seoson1;
    private EditText time;
    private Button btnsave2;
    private Button btncancel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovie);
        //توقيت الصفات
        name1=findViewById(R.id.name1);
        Type1=findViewById(R.id.Type1);
        lang1=findViewById(R.id.lang1);
        seoson1=findViewById(R.id.seoson1);
        time=findViewById(R.id.time);
    }
    public void onClickAddmoveiToReating(View v) {
        //to open new activity from current to next activity
        Intent i = new Intent(AddMovie.this, Reating.class);
        startActivity(i);
    }
    private void cheackMoveiDetails(){
        boolean isAllok = true; // يحوي نتيجة فحص الحقول ان كانت  السليمة
        //
        String Name= name1.getText().toString();
        //
        String Type= Type1.getText().toString();
        //
        String Langage= lang1.getText().toString();
        //
        String Seoson= seoson1.getText().toString();
        //
        String Time= time.getText().toString();
        if (name1.length() < 0){
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            name1.setError("worng name");
        }
        if (Type1.length() < 0){
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            Type1.setError("worng Type");
        }
        if (lang1.length() < 0){
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            name1.setError("worng langage");
        }
        if (seoson1.length() < 0){
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            name1.setError("worng Seoson Number");
        }
        if (time.length() < 0){
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            name1.setError("worng Time");
        }
        if (isAllok) {
            saveMovei_FB(Name,Type,Langage,Seoson,Time);
        }
    }
    //Firebase
    private void saveMovei_FB(String name1, String Type1, String lang1, String seoson1,String time )
    {
        //مؤشر لقاعدة البيانات
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //استخراج الرقم المميز للمستعمل الذي سجل الدخول
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //بناء الكائن الذي سيتم حفظه
        Movei movei=new Movei();
        movei.setMoveiName(name1);
        movei.setLangage(lang1);
        movei.setType(Type1);
        movei.setSeosonNuumber(seoson1);
        movei.setTime(time);
        movei.setMid(movei.Mid);
        //اضافة كائن لمجموعة المستعملين ومعالج حدث لفحص نجاح الاضافة
        db.collection("MyUsers").document(uid).set(movei).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddMovie.this, "Succeed to add Movei", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddMovie.this, "Failed to add Movei", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}