package shu.example.hallafinal2023;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import shu.example.hallafinal2023.MyData.MySeries.Series;

public class AddSeries extends AppCompatActivity {
    private TextInputEditText name2;
    private TextInputEditText type2;
    private TextInputEditText lang2;
    private TextInputEditText num2;
    private TextInputEditText time2;
    private Button btnsave3;
    private Button btncancel3;
    private ImageView seriesphoto;
    //upload: 1 add Xml image view or button and upload button
//upload: 2 add next fileds
    private final int IMAGE_PICK_CODE=100;// קוד מזהה לבקשת בחירת תמונה
    private final int PERMISSION_CODE=101;//קוד מזהה לבחירת הרשאת גישה לקבצים
    private ImageView moveiphoto;//כפתור/ לחצן לבחירת תמונה והצגתה
    private Uri toUploadimageUri;// כתוב הקובץ(תמונה) שרוצים להעלות
    private Uri downladuri;//כתובת הקוץ בענן אחרי ההעלאה

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
            //توثيق الصفات
            name2=findViewById(R.id.name2);
            type2=findViewById(R.id.type2);
            lang2=findViewById(R.id.lang2);
            num2=findViewById(R.id.num2);
           time2=findViewById(R.id.time2);
        btnsave3=findViewById(R.id.btnsave3);

        //upload: 3
        seriesphoto=findViewById(R.id.seriesphoto);
        seriesphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        btnsave3.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View view) {
                cheackSeriesDetails();
            }
        });
        //upload: 3
        //seriesphoto=findViewById(R.id.seriesphoto);
        seriesphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //upload: 8
                //lcheckPermission();
            }
        });
    }
//دالة تفحص معطيات المسلسل
    private void cheackSeriesDetails() {
        boolean isAllok = true; // يحوي نتيجة فحص الحقول ان كانت  السليمة
        //يستخرج اسم المسلسل
        String Name = name2.getText().toString();
        //يستخرج نوع المسلسل
        String Type = type2.getText().toString();
        //يستخرج لغة المسلسل
        String Langage = lang2.getText().toString();
        //يستخرد عدد حلقات المسلسل
        String EpisodeNumber = num2.getText().toString();
        //يستخرج مدة المسلسل
        String Time = time2.getText().toString();
        //يفحص تفاصيل المسلسل
        if (name2.length() < 0) {
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            name2.setError("worng name");
        }
        if (type2.length() < 0) {
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            type2.setError("worng Type");
        }
        if (lang2.length() < 0) {
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            lang2.setError("worng langage");
        }
        if (num2.length() < 0) {
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            num2.setError("worng Epissode Number");
        }
        if (time2.length() < 0) {
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            time2.setError("worng Time");
        }
        if (toUploadimageUri!=null)
        {
            //
            isAllok=false;
            //
            Toast.makeText(this, "must choose image", Toast.LENGTH_SHORT).show();

        }
        if (isAllok) {saveSeries_FB(Name, Type, Langage, EpisodeNumber, Time);
        }
    }
        //Firebase
        private void saveSeries_FB(String name2, String type2, String lang2, String num2,String time2 )
        {
            //مؤشر لقاعدة البيانات
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String sid = db.collection("Myseries").document().getId();
            //بناء الكائن الذي سيتم حفظه
            Series series=new Series();
            series.setSeriesName(name2);
            series.setSeriesLangage(lang2);
            series.setSeriesType(type2);
            series.setSeriesEpisodeNumber(num2);
            series.setSeriesTime(time2);
            series.setSid(sid);
            //اضافة كائن لمجموعة المستعملين ومعالج حدث لفحص نجاح الاضافة
            db.collection("MySeries").document(sid).set(series).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(AddSeries.this, "Succeed to add Series", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddSeries.this, "Failed to add Series", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }