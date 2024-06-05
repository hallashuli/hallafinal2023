package shu.example.hallafinal2023;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import shu.example.hallafinal2023.MyData.MySeries.Series;
//
public class AddSeries extends AppCompatActivity {
//    private TextInputEditText name2;
//    private TextInputEditText type2;
//    private TextInputEditText lang2;
//    private TextInputEditText num2;
//    private TextInputEditText time2;
//    private Button btnsave3;
//    private Button btncancel3;
//    //upload: 1 add Xml image view or button and upload button
////upload: 2 add next fileds
//    private final int IMAGE_PICK_CODE = 100;// קוד מזהה לבקשת בחירת תמונה
//    private final int PERMISSION_CODE = 101;//קוד מזהה לבחירת הרשאת גישה לקבצים
//    private ImageView seriesphoto;//כפתור/ לחצן לבחירת תמונה והצגתה
//    private Uri toUploadimageUri;// כתוב הקובץ(תמונה) שרוצים להעלות
//    private Uri downladuri;//כתובת הקוץ בענן אחרי ההעלאה
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_series);
//        //توثيق الصفات
//        name2 = findViewById(R.id.name2);
//        type2 = findViewById(R.id.type2);
//        lang2 = findViewById(R.id.lang2);
//        num2 = findViewById(R.id.num2);
//        time2 = findViewById(R.id.time2);
//        btnsave3 = findViewById(R.id.btnsave3);
//        //upload: 3
//        seriesphoto = findViewById(R.id.seriesphoto);
//        seriesphoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//
//        });
//
//        btnsave3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cheackSeriesDetails();
//            }
//        });
//        //upload: 3
//        seriesphoto = findViewById(R.id.seriesphoto);
//        seriesphoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //upload: 8
//                checkPermission();
//            }
//        });
//    }
//
//    //دالة تفحص معطيات المسلسل
//    private void cheackSeriesDetails() {
//        boolean isAllok = true; // يحوي نتيجة فحص الحقول ان كانت  السليمة
//        //يستخرج اسم المسلسل
//        String Name = name2.getText().toString();
//        //يستخرج نوع المسلسل
//        String Type = type2.getText().toString();
//        //يستخرج لغة المسلسل
//        String Langage = lang2.getText().toString();
//        //يستخرد عدد حلقات المسلسل
//        String EpisodeNumber = num2.getText().toString();
//        //يستخرج مدة المسلسل
//        String Time = time2.getText().toString();
//        //يفحص تفاصيل المسلسل
//        if (name2.length() < 0) {
//            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
//            isAllok = false;
//            //عرض النتيجة خطأ في حقل
//            name2.setError("worng name");
//        }
//        if (type2.length() < 0) {
//            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
//            isAllok = false;
//            //عرض النتيجة خطأ في حقل
//            type2.setError("worng Type");
//        }
//        if (lang2.length() < 0) {
//            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
//            isAllok = false;
//            //عرض النتيجة خطأ في حقل
//            lang2.setError("worng langage");
//        }
//        if (num2.length() < 0) {
//            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
//            isAllok = false;
//            //عرض النتيجة خطأ في حقل
//            num2.setError("worng Epissode Number");
//        }
//        if (time2.length() < 0) {
//            // تعديل المتغير و يدل على انه فحص و يعطي نتيجة خاطئة
//            isAllok = false;
//            //عرض النتيجة خطأ في حقل
//            time2.setError("worng Time");
//        }
//        if (toUploadimageUri != null) {
//            //
//            isAllok = false;
//            //
//            Toast.makeText(this, "must choose image", Toast.LENGTH_SHORT).show();
//        }
//        if (isAllok) {
//            series.setSeriesName(Name);
//            series.setSeriesLangage(Langage);
//            series.setSeriesType(Type);
//            series.setSeriesEpisodeNumber(EpisodeNumber);
//            series.setSeriesTime(Time);
//            uploadImage(toUploadimageUri);
//        }
//
//        //Firebase
//        private void saveSeries_FB ()
//        {
//            //مؤشر لقاعدة البيانات
//            FirebaseFirestore db = FirebaseFirestore.getInstance();
//            String sid = db.collection("Myseries").document().getId();
//            //بناء الكائن الذي سيتم حفظه
//            series.setSid(sid);
//            //اضافة كائن لمجموعة المستعملين ومعالج حدث لفحص نجاح الاضافة
//            db.collection("MySeries").document(sid).set(series).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(AddSeries.this, "Succeed to add Series", Toast.LENGTH_SHORT).show();
//                        finish();
//                    } else {
//                        Toast.makeText(AddSeries.this, "Failed to add Series", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//            private void pickImageFromGallery () {
//            //implicit intent (מרומז) to pick image
//            Intent intent = new Intent(Intent.ACTION_PICK);
//            intent.setType("image/*");
//            startActivityForResult(intent, IMAGE_PICK_CODE);//הפעלתה האינטנט עם קוד הבקשה
//        }
//            //upload: 5:handle result of picked images
//            /**
//             *
//             * @param requestCode מספר הקשה
//             * @param resultCode תוצאה הבקשה (אם נבחר משהו או בוטלה)
//             * @param data הנתונים שנבחרו
//             */
//            @Override
//            protected void onActivityResult ( int requestCode, int resultCode, Intent data){
//            super.onActivityResult(requestCode, resultCode, data);
//            //אם נבחר משהו ואם זה קוד בקשת התמונה
//            if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
//                //a עידכון תכונת כתובת התמונה
//                toUploadimageUri = data.getData();//קבלת כתובת התמונה הנתונים שניבחרו
//                seriesphoto.setImageURI(toUploadimageUri);// הצגת התמונה שנבחרה על רכיב התמונה
//            }
//        }
//            //upload: 6
//            /**
//             * בדיקה האם יש הרשאה לגישה לקבצים בטלפון
//             */
//            private void checkPermission ()
//            {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//בדיקת גרסאות
//                    //בדיקה אם ההשאה לא אושרה בעבר
//                    if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
//                        //רשימת ההרשאות שרוצים לבקש אישור
//                        String[] permissions = {android.Manifest.permission.READ_EXTERNAL_STORAGE};
//                        //בקשת אישור ההשאות (שולחים קוד הבקשה)
//                        //התשובה תתקבל בפעולה onRequestPermissionsResult
//                        requestPermissions(permissions, PERMISSION_CODE);
//                    } else {
//                        //permission already granted אם יש הרשאה מקודם אז מפעילים בחירת תמונה מהטלפון
//                        pickImageFromGallery();
//                    }
//                } else {//אם גרסה ישנה ולא צריך קבלת אישור
//                    pickImageFromGallery();
//                }
//            }
//            //upload: 7
//            /**
//             * @param requestCode The request code passed in מספר בקשת ההרשאה
//             * @param permissions The requested permissions. Never null. רשימת ההרשאות לאישור
//             * @param grantResults The grant results for the corresponding permissions תוצאה עבור כל הרשאה
//             *   PERMISSION_GRANTED אושר or PERMISSION_DENIED נדחה . Never null.
//             */
//            @Override
//            public void onRequestPermissionsResult ( int requestCode, String[] permissions,
//            int[] grantResults){
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//            if (requestCode == PERMISSION_CODE) {//בדיקת קוד בקשת ההרשאה
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //permission was granted אם יש אישור
//                    pickImageFromGallery();
//                } else {
//                    //permission was denied אם אין אישור
//                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//            private void uploadImage (Uri filePath){
//            if (filePath != null) {
//                //יצירת דיאלוג התקדמות
//                final ProgressDialog progressDialog = new ProgressDialog(this);
//                progressDialog.setTitle("Uploading...");
//                progressDialog.show();//הצגת הדיאלוג
//                //קבלת כתובת האחסון בענן
//                FirebaseStorage storage = FirebaseStorage.getInstance();
//                StorageReference storageReference = storage.getReference();
//                //יצירת תיקיה ושם גלובלי לקובץ
//                final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
//                // יצירת ״תהליך מקביל״ להעלאת תמונה
//                ref.putFile(filePath)
//                        //הוספת מאזין למצב ההעלאה
//                        .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    progressDialog.dismiss();// הסתרת הדיאלוג
//                                    //קבלת כתובת הקובץ שהועלה
//                                    ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Uri> task) {
//                                            downladuri = task.getResult();
//                                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
//                                            series.setImage(downladuri.toString());//עדכון כתובת התמונה שהועלתה
//                                            saveSeries_FB();
//                                        }
//                                    });
//                                } else {
//                                    progressDialog.dismiss();//הסתרת הדיאלוג
//                                    Toast.makeText(getApplicationContext(), "Failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        })
//                        //הוספת מאזין שמציג מהו אחוז ההעלאה
//                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                                //חישוב מה הגודל שהועלה
//                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
//                                        .getTotalByteCount());
//                                progressDialog.setMessage("Uploaded " + (int) progress + "%");
//                            }
//                        });
//            } else {
//                saveSeries_FB();
//            }
//        }
//
//        }
//    }
}