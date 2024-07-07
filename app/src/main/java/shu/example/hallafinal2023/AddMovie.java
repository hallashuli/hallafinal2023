package shu.example.hallafinal2023;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import shu.example.hallafinal2023.MyData.MyFilmTable.Movei;

public class AddMovie extends AppCompatActivity {
    private TextInputEditText name1;
    private TextInputEditText Type1;
    private TextInputEditText lang1;
    private TextInputEditText seoson1;
    private  TextInputEditText time1;
    private Button btnsave2;
    private Button btncancel2;
    //upload: 1 add Xml image view or button and upload button
//upload: 2 add next fileds
    private final int IMAGE_PICK_CODE=100;// קוד מזהה לבקשת בחירת תמונה \ كود كود تعريف يستخدم عند اختيار صورة من المعرض
    private final int PERMISSION_CODE=101;//\   كود تعريف لطلب إذن الوصول إلى الملفات من الجهاز\קוד מזהה לבחירת הרשאת גישה לקבצים
    private VideoView moveiphoto;//כפתור/ לחצן לבחירת תמונה והצגתה\ كائن من نوع VideoView يستخدم لعرض الفيديو.
    private Uri toUploadvideoUri;// כתוב הקובץ(תמונה) שרוצים להעלות \ هذا كائن من نوع Uri يمثل عنوان الملف (الصورة) الذي نريد رفعه
    private Uri downladuri;//כתובת הקוץ בענן אחרי ההעלאה \هذا كائن من نوع Uri يمثل عنوان الملف في السحابة بعد رفعه.
    Movei movei=new Movei(); //هذا يقوم بإنشاء كائن جديد من الفئة Movei.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovie);
        //توثيق الصفات
        name1=findViewById(R.id.name1);
        Type1=findViewById(R.id.Type1);
        lang1=findViewById(R.id.lang1);
        seoson1=findViewById(R.id.seoson1);
        time1=findViewById(R.id.time1);
        btnsave2=findViewById(R.id.btnsave2);
        //upload: 3
        btnsave2.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View view) {
               cheackMoveiDetails();
            }
        });
        //upload: 3
        moveiphoto=findViewById(R.id.moveiphoto);
        moveiphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //upload: 8
                checkPermission();
            }
        });
    }
    public void onClickAddMoveitoMainActivity(View v) {
        //to open new activity from current to next activity
        Intent i = new Intent(AddMovie.this, MainActivityMovie.class);
        startActivity(i);
    }
    public void onClickAddMoveitoMainActivityMovei(View v) {
        //to open new activity from current to next activity
        Intent i = new Intent(AddMovie.this, MainActivityMovie.class);
        startActivity(i);
    }


    //دالة تفحص معطيات الفيلم
    private void cheackMoveiDetails(){
        boolean isAllok = true; // يحوي نتيجة فحص الحقول ان كانت  السليمة
        //يستخرج اسم الفيلم
        String Name= name1.getText().toString();
        //يستخرج نوع الفيلم
        String Type= Type1.getText().toString();
        //يستخرج نوع اللغة
        String Langage= lang1.getText().toString();
        //يستخرج عدد مواسم المسلسل
        String Seoson= seoson1.getText().toString();
        //يستخرج وقت الفيلم
        String Time= time1.getText().toString();
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
            lang1.setError("worng langage");
        }
        if (seoson1.length() < 0){
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            seoson1.setError("worng Seoson Number");
        }
        if (time1.length() < 0){
            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
            isAllok = false;
            //عرض النتيجة خطأ في حقل
            time1.setError("worng Time");
        }
        //الكود المرفق هو جزء من عملية التحقق من وجود فيديو تم اختياره من قبل المستخدم قبل متابعة العملية.
        if (toUploadvideoUri ==null)
        {
            isAllok=false;
            Toast.makeText(this, "must choose image", Toast.LENGTH_SHORT).show();
        }
        if (isAllok) {
            movei.setMoveiName(Name);
            movei.setMoveiLangage(Langage);
            movei.setMoveiType(Type);
            movei.setMoveiSeosonNuumber(Seoson);
            movei.setMoveiTime(Time);
            uploadImage(toUploadvideoUri);
        }
    }
    //Firebase
    private void saveMovei_FB()
    {
        //مؤشر لقاعدة البيانات
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String mid = db.collection("Mymovies").document().getId();
        //بناء الكائن الذي سيتم حفظه
        movei.setMid(mid);
        //اضافة كائن لمجموعة الافلام ومعالج حدث لفحص نجاح الاضافة
        db.collection("Mymovies").document(mid).set(movei).addOnCompleteListener(new OnCompleteListener<Void>() {
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
    //لكود يفتح معرض الفيديوهات على الجهاز للسماح للمستخدم باختيار فيديو، ثم يعيد النتيجة
    private void pickVideoFromGallery(){
        //implicit intent (מרומז) to pick image
        //يقوم الكود بإنشاء Intent باستخدام الإجراء Intent.ACTION_PICK، وهو إجراء قياسي لاختيار عنصر من قائمة أو مصدر بيانات
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        //تشغيل Activity و تعيد لنا شيء و هنا تعيد لنا فيديو
        startActivityForResult(Intent.createChooser(intent,"Select Video"),IMAGE_PICK_CODE);//הפעלתה האינטנט עם קוד הבקשה
    }
    //upload: 5:handle result of picked images
    /**
     *
     * @param requestCode מספר הקשה
     * @param resultCode תוצאה הבקשה (אם נבחר משהו או בוטלה)
     * @param data הנתונים שנבחרו
     *             في هذه الدالة، يتم التحقق مما إذا كان كود الطلب هو IMAGE_PICK_CODE،
    وإذا كانت النتيجة ناجحة (RESULT_OK)، وإذا كانت البيانات غير فارغة.
    بعد ذلك، يتم الحصول على URI للفيديو المختار من البيانات ويمكن استخدامه لمزيد من المعالجة (مثل عرض الفيديو أو رفعه)
     */
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        //אם נבחר משהו ואם זה קוד בקשת התמונה
        if (resultCode==RESULT_OK && requestCode== IMAGE_PICK_CODE){
            //a עידכון תכונת כתובת התמונה
            toUploadvideoUri = data.getData();//קבלת כתובת התמונה הנתונים שניבחרו
            moveiphoto.setVideoURI(toUploadvideoUri);// הצגת התמונה שנבחרה על רכיב התמונה
        moveiphoto.seekTo(2);
        }
    }
    //upload: 6
    /**
     * בדיקה האם יש הרשאה לגישה לקבצים בטלפון
     */
    private void checkPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//בדיקת גרסאות
            //בדיקה אם ההשאה לא אושרה בעבר
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                //רשימת ההרשאות שרוצים לבקש אישור
                String[] permissions = {android.Manifest.permission.READ_EXTERNAL_STORAGE};
                //בקשת אישור ההשאות (שולחים קוד הבקשה)
                //התשובה תתקבל בפעולה onRequestPermissionsResult
                requestPermissions(permissions, PERMISSION_CODE);
            } else {
                //permission already granted אם יש הרשאה מקודם אז מפעילים בחירת תמונה מהטלפון
                pickVideoFromGallery();
            }
        }
        else {//אם גרסה ישנה ולא צריך קבלת אישור
            pickVideoFromGallery();
        }
    }
    //upload: 7
    /**
     * @param requestCode The request code passed in מספר בקשת ההרשאה
     * @param permissions The requested permissions. Never null. רשימת ההרשאות לאישור
     * @param grantResults The grant results for the corresponding permissions תוצאה עבור כל הרשאה
     *   PERMISSION_GRANTED אושר or PERMISSION_DENIED נדחה . Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISSION_CODE) {//בדיקת קוד בקשת ההרשאה
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permission was granted אם יש אישור
                pickVideoFromGallery();
            } else {
                //permission was denied אם אין אישור
                Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void uploadImage(Uri filePath) {
        if (filePath != null) {
            //יצירת דיאלוג התקדמות
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();//הצגת הדיאלוג
            //קבלת כתובת האחסון בענן
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            //יצירת תיקיה ושם גלובלי לקובץ
            final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            // יצירת ״תהליך מקביל״ להעלאת תמונה
            ref.putFile(filePath)
                    //הוספת מאזין למצב ההעלאה
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();// הסתרת הדיאלוג
                                //קבלת כתובת הקובץ שהועלה
                                ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        downladuri = task.getResult();
                                        Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                                        movei.setVideo(downladuri.toString());//עדכון כתובת התמונה שהועלתה
                                        saveMovei_FB();
                                    }
                                });
                            } else {
                                progressDialog.dismiss();//הסתרת הדיאלוג
                                Toast.makeText(getApplicationContext(), "Failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    //הוספת מאזין שמציג מהו אחוז ההעלאה
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //חישוב מה הגודל שהועלה
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()/ taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        } else {
            saveMovei_FB();
        }
    }
}