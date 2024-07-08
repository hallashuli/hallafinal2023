package shu.example.hallafinal2023.MyData.MyFilmTable;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import shu.example.hallafinal2023.AddMovie;
import shu.example.hallafinal2023.MainActivityMovie;
import shu.example.hallafinal2023.MyData.myuser.Myuser;
import shu.example.hallafinal2023.R;
//الهدف من هذا الكود هو عرض بيانات قائمة من الأفلام (أو أي عناصر أخرى) في واجهة المستخدم بطريقة مخصصة.
public class  MoveiAdaptar extends ArrayAdapter<Movei> {
    private ImageView movei_image;
    private TextView movei_name;
    private ImageView _sendimage;
   // private ImageView edit_image;
    private ImageView Reate_image;
    private ImageView informathion_image;
    private ImageView video_play;
    private Dialog dialog;
    //המזהה של קובץ עיצוב הפריט
    private final int itemLayout;
    Myuser users=new Myuser();
    /**
     * פעולה בונה מתאם
     * @param context  קישור להקשר (מסך- אקטיביטי)
     * @param resource עיצוב של פריט שיציג הנתונים של העצם
     */
    /*
    هي دالة البناء (constructor) لفئة MoveiAdaptar.
    الهدف من هذه الدالة هو تهيئة كائن جديد من الفئة MoveiAdaptar باستخدام المعطيات المقدمة.
    /*
    *المعطى context هو كائن من نوع Context، ويُستخدم عادة لتوفير الوصول إلى موارد التطبيق والمعلومات الخاصة به.
     يمكن استخدام context للوصول إلى الموارد:
    *  مثل السلاسل النصية (strings)، الصور (drawables)، الألوان (colors)، تخطيطات الواجهة (layouts)، وغيرها.
    * المعطى resource هو معرف لمورد تخطيط (layout resource) يُستخدم لتحديد تصميم العناصر التي سيتم عرضها في (adapter).
    * عند استخدام (adapter) لعرض مجموعة من البيانات في مكونات عرض مثل ListView، GridView، أو RecyclerView،
     فإنك تحتاج إلى طريقة لتحديد كيف يجب أن تبدو كل عنصر من العناصر في هذه المجموعة. هنا يأتي دور layout resource.
     * */
    //
    public MoveiAdaptar(@NonNull Context context, int resource) {
        super(context, resource);
        this.itemLayout = resource;//يعين قيمة المتغير resource إلى المتغير الخاص بالفئة itemLayout. هذا يتيح ل(adapter) معرفة تخطيط العناصر التي سيتم عرضها.
    }
    /**
     * בונה פריט גרפי אחד בהתאם לעיצוב והצגת נתוני העצם עליו
     *
     * @param position    מיקום הפריט החל מ 0
     * @param convertView
     * @param parent      רכיב האוסף שיכיל את הפריטים כמו listview
     * @return . פריט גרפי שמציג נתוני עצם אחד
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //בניית הפריט הגרפי מתו קובץ העיצוב\بناء القائمة الرسومية من ملف التصميم
        View vitem = convertView;//convertView: العرض القديم الذي يمكن إعادة استخدامه.
        if (vitem == null)
            vitem = LayoutInflater.from(getContext()).inflate(itemLayout, parent, false);
        //קבלת הפניות לרכיבים בקובץ העיצוב\ // الحصول على المراجع إلى المكونات في ملف التصميم
        ImageView movei_image = vitem.findViewById(R.id.movei_image);//صورة من الفيديو
        TextView movei_name = vitem.findViewById(R.id.movei_name);//عرض اسم الفيلم
        ImageView _sendimage = vitem.findViewById(R.id._sendimage);//ارسال
        ImageView video_play = vitem.findViewById(R.id.video_play2);//تشغيل الفيديو
//        ImageView btnEdit = vitem.findViewById(R.id.edit_image);
       ImageView btnReate_image = vitem.findViewById(R.id.Reate_image);//تقييم
       ImageView btnInformathion_image = vitem.findViewById(R.id.informathion_image);//اظهار التقييم
        //Current
        //الحصول على البيانات (الكائن) الحالية\קבלת הנתון (עצם) הנוכחי
        Movei current = getItem(position);

        // عرض اسم الفيلم في عنصر النص
        movei_name.setText(current.getMoveiName());

        /**يتم هنا تحميل الصورة المصغرة للفيديو بواسطة دالة downloadvideothumbnail
         *  التي تأخذ عنوان الفيديو (current.getVideo()) ومكان عرض الصورة (movei_image).
         */
        //تحميل الصورة المصغرة للفيديو بواسطة دالة downloadvideothumbnail
        downloadvideothumbnail(current.getVideo(), movei_image);
//// إعداد Listener  للنقر على زر تشغيل الفيديو
        video_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showvediodialog(current.video);
            }
        });
        //  //  إعداد Listener  للنقر على زر تقييم الفيلم
        btnReate_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eidtreatingdialog(current);
            }
        });
        //// إعداد Listener  للنقر على زر عرض تقييم الفيلم
        btnInformathion_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showreatingdialog(current);
            }
        });
        //إرسال رسالة عبر واتساب// إعداد Listener  للنقر على زر
        _sendimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSendWhatsAppV2(" ", users.getPhone());
            }
        });
        // إعادة العرض المخصص للعنصر الحالي
        return vitem;
    }

    private void downloadimagethumbnail(String image, ImageView imgbtnmed) {
        //  long interval = getPosition()*1000;
        //RequestOptions options = new RequestOptions().frame(interval);
        if (image == null || image.length() == 0) {
            return;
        }
        Glide.with(getContext()).asBitmap()
                .load(Uri.parse(image))
                //.apply(options)
                .into(imgbtnmed);
    }
/*
الهدف من الدالة هو تحميل صورة مصغرة لفيديو معين عرضها في عنصر ImageView المحدد.
 إذا كان الفيديو غير متاح (فارغ)، يتم إنهاء الدالة بدون فعل شيء.
 */
    private void downloadvideothumbnail(String video, ImageView vifbtnmed) {
        //  long interval = getPosition()*1000;
        //RequestOptions options = new RequestOptions().frame(interval);
        if (video == null || video.length() == 0) {
            return;
        }
        Glide.with(getContext()).asBitmap()
                .load(Uri.parse(video))
                //.apply(options)
                .into(vifbtnmed);
    }
    /**
     *if (dialog == null)
               dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.______________);
     *  الهدف من هذه الاوامر هو انشاء جديد dialogاذا لم يكن موجدًا
     *إذا كان dialog هو null، فهذا يعني أنه لم يتم إنشاؤه بعد.
     * إذا تحقق الشرط (dialog == null)،
      يتم إنشاء واحد جديد باستخدام Dialog وبتمرير سياق التطبيق أو النشاط (Activity) بواسطة getContext().
     dialog.setContentView(R.layout.-------------);
     بعد التأكد من وجوده  (إما تم إنشاؤه حديثًا أو كان موجودًا بالفعل)، يتم تعيين تخطيط المحتوى باستخدام setContentView.
     R.layout.---- يشير إلى ملف تخطيط XML يحتوي على تعريف واجهة المستخدم التي سيتم عرضها داخل الحوار.
     */
/*
الهدف من هذه الدالة هو عرض الفيديو  حيث يمكن للمستخدم تشغيل الفيديو والتحكم فيه باستخدام MediaController.
 بالإضافة إلى ذلك، يمكن للمستخدم إغلاق الفيديو بالنقر على صورة الإلغاء.
 */
    public void showvediodialog(String vedioUrl)//الدالة showvediodialog تأخذ متغير واحد vedioUrl، والذي تمثل رابط الفيديو الذي سيتم عرضه.
    {
        if (dialog == null)
            dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_playvideo);
        VideoView videoVdial = dialog.findViewById(R.id.movei_video);
        ImageView cancel_image = dialog.findViewById(R.id.cancel_image);
        cancel_image.setOnClickListener(new View.OnClickListener() { //عند النقر على cancel_image، يتم إغلاق الشاشة باستخدام dialog.dismiss().
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //يتم تعيين عرض النافذة ليكون مطابقًا للأصل (MATCH_PARENT) والارتفاع ليكون محتوى قابل للتغيير (WRAP_CONTENT).
        //و الهدف هنا هو جعل الفيديو قابلاً للإلغاء بالنقر خارج نطاقه.
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        // dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        videoVdial.setVideoURI(Uri.parse(vedioUrl));//يتم تعيين رابط الفيديو لـ VideoView باستخدام setVideoURI.
        // creating object of
        // media controller class
        MediaController mediaController = new MediaController(getContext());//يتم إنشاء كائن MediaController للتحكم في تشغيل الفيديو.
        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(videoVdial);//يتم تعيين mediaController كمرساة لـ VideoView.
        // sets the media player to the videoView
        mediaController.setMediaPlayer(videoVdial);//يتم تعيين mediaController كتحكم في الوسائط لـ VideoView.
        // sets the media controller to the videoView
        videoVdial.setMediaController(mediaController);//يتم عرض mediaController وتشغيل الفيديو.
        mediaController.show();
        videoVdial.start();
        dialog.show();//يتم عرض الفيديو باستخدام dialog.show().
    }
//الهدف من الدالة هو اتاحة للمستخدم تقييم فيلم معين .
    public void eidtreatingdialog(Movei m) //الدالة eidtreatingdialog تأخذ متغير كائن Movei m، الذي يمثل الفيلم الذي سيتم تقييمه.
    {
        if (dialog == null)
            dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_add_reating);
        // العثور على قيم في التخطيط
        RatingBar ratingTV = dialog.findViewById(R.id.ratingTV);
        TextInputEditText comment = dialog.findViewById(R.id.comment);
        Button saveR = dialog.findViewById(R.id.saveR);   // عند النقر على زر الحفظ، قم بتنفيذ العمليات التالية:
        /*
         * قراءة التعليق المدخل من المستخدم وتخزينه في متغير s.
         *قراءة التقييم المدخل من المستخدم وتخزينه في متغير r.
         * إنشاء كائن MoveiRating جديد وتعيين التقييم (rate) والتعليق (comment) ومعرف الفيلم (tId).
         * إضافة التقييم الجديد إلى قائمة التقييمات الخاصة بالفيلم (m.getMoveiRatings().add(mr)).
         * تحديث بيانات الفيلم في قاعدة البيانات (من المفترض أن تكون Firebase) باستخدام دالة UpdateMovei_FB(m).
         */
        saveR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // قراءة التعليق والتقييم من المستخدم
                String s=comment.getText().toString();
                float r= ratingTV.getRating();
                // إنشاء كائن MoveiRating جديد وتعيين القيم
                MoveiRating mr=new MoveiRating();
                mr.setRate(r);
                mr.setComment(s);
                mr.settId(m.getmUid());
                // إضافة التقييم الجديد إلى قائمة تقييمات الفيلم
                m.getMoveiRatings().add(mr);
                // تحديث بيانات الفيلم في قاعدة البيانات
                UpdateMovei_FB(m);
                // إغلاق بعد حفظ التقييم
                dialog.dismiss();
            }
        });
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        // creating object of
        // media controller class
        MediaController mediaController = new MediaController(getContext());
        // sets the anchor view
        // anchor view for the videoView
        /*عند النقر على زر الحفظ، يتم حفظ التقييم والتعليق في كائن MoveiRating جديد
          ثم يتم إضافته إلى قائمة التقييمات الخاصة بالفيلم وتحديث بيانات الفيلم في قاعدة البيانات. بعد ذلك، يتم إغلاق الشاشة.
         */
        dialog.show();
    }
//الهدف من الدالة هو تحديث بيانات فيلم معين في قاعدة بيانات Firebase Firestore.
    private void UpdateMovei_FB(Movei m) // الدالة UpdateMovei_FB تتلقى  كائن Movei m، الذي يمثل الفيلم الذي سيتم تحديثه في قاعدة البيانات
    {
        //مؤشر لقاعدة البيانات
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //بناء الكائن الذي سيتم حفظه
        //اضافة كائن لمجموعة الافلام ومعالج حدث لفحص نجاح الاضافة
        /**
         * يتم الوصول إلى مجموعة الأفلام في قاعدة البيانات باستخدام db.collection("Mymovies").
         *يتم تحديد الفيلم المراد تحديثه باستخدام document(m.mid).
         *باستخدام addOnCompleteListener نتحقق مما إذا كانت العملية ناجحة أم لا.
         */
        // الوصول إلى مجموعة الأفلام في قاعدة البيانات باستخدام db.collection("Mymovies")
        // وتحديد الفيلم المراد تحديثه باستخدام document(m.mid)
        db.collection("Mymovies").document(m.mid).set(m).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {  // التحقق مما إذا كانت العملية ناجحة أم لا
                    // إذا كانت العملية ناجحة
                    Toast.makeText(getContext(), "Succeed to Update Rating Movei", Toast.LENGTH_SHORT).show();
                    // استدعاء الدالة readMoveiFrom_FB لتحديث واجهة المستخدم ببيانات الأفلام المحدثة
                    ((MainActivityMovie)getContext()).readMoveiFrom_FB();
                } else {
                    // إذا فشلت العملية
                    Toast.makeText(getContext(), "Failed to Update Rating Movei", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//الهدف من الدالة هو عرض متوسط التقييمات والتعليقات الخاصة بفيلم معين
    public void showreatingdialog(Movei m) // الدالة showreatingdialog تتلقى كائن Movei m، الذي يمثل الفيلم الذي سيتم عرض تقييماته وتعليقاته.
    {
        if (dialog == null)
            dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_show_rating);
        // العثور على القيم في التخطيط
        RatingBar ratingShow = dialog.findViewById(R.id.ratingShow);
        TextView commentShow = dialog.findViewById(R.id.commentShow);
        Button CancelR = dialog.findViewById(R.id.CancelR);
        /** يتم تهيئة متغير r لحفظ متوسط التقييمات ومتغير s لجمع التعليقات.
         * يتم استخدام حلقة for للتكرار على قائمة التقييمات الخاصة بالفيلم (m.getMoveiRatings())
         *يتم إضافة قيمة التقييم إلى r بعد قسمتها على حجم قائمة التقييمات للحصول على المتوسط.
         *يتم إضافة كل تعليق إلى s، مع إضافة سطر جديد بعد كل تعليق.
         * يتم تعيين قيمة r كالتقييم في RatingBar.
         * يتم تعيين قيمة s كنص في TextView.
         */
        // تهيئة متغير لحفظ مجموع التقييمات وآخر لجمع التعليقات
        float r=0;
        StringBuffer s=new StringBuffer();
        // التكرار على قائمة التقييمات الخاصة بالفيلم
        for (MoveiRating moveiRating : m.getMoveiRatings()) {
            r=r+moveiRating.getRate()/m.getMoveiRatings().size();// جمع التقييمات
            s.append(moveiRating.getComment()); // جمع التعليقات مع إضافة سطر جديد بعد كل تعليق
            s.append('\n');//enter
        }
        ratingShow.setRating(r);
        commentShow.setText(s);
        //عند النقر على CancelR، يتم إغلاق الشاشة باستخدام dialog.dismiss().
        CancelR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        // creating object of
        // media controller class
        MediaController mediaController = new MediaController(getContext());
        // sets the anchor view
        // anchor view for the videoView
        dialog.show();
    }
    /**
     * פתיחת אפליקצית שליחת whatsapp
     *
     * @param msg   .. ההודעה שרוצים לשלוח
     * @param phone
     */
    //فتح تطبيق WhatsApp وإرسال رسالة إلى رقم هاتف محدد.
    public void openSendWhatsAppV2(String msg, String phone) {
        //אינטנט מרומז לפתיחת אפליקצית ההודות סמס
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        ;
        String url = null;
        try {
            url = "https://api.whatsapp.com/send?phone=" + phone + "&text=" + URLEncoder.encode(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            Toast.makeText(getContext(), "there is no whatsapp!!", Toast.LENGTH_SHORT).show();
        }
        sendIntent.setData(Uri.parse(url));
        sendIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        sendIntent.addCategory(Intent.CATEGORY_DEFAULT);
        //פתיחת אפליקציית ה סמס \    // فتح تطبيق WhatsApp
        getContext().startActivity(sendIntent);
    }/**



//public void ShowMoveiInformationdialog(Movei m) {
//
//    if (dialog == null)
//        dialog = new Dialog(getContext());
//    dialog.setContentView(R.layout.activity_show_information);
//    TextView n = dialog.findViewById(R.id.n);
//    TextView t = dialog.findViewById(R.id.t);
//    TextView ti = dialog.findViewById(R.id.ti);
//    TextView l = dialog.findViewById(R.id.l);
//    TextView sn = dialog.findViewById(R.id.sn);
//    Button buttonIn = dialog.findViewById(R.id.buttonIn);
//
//    // ملء عناصر واجهة المستخدم بالبيانات من كائن الفيلم
//    n.setText(m.getName());
//    t.setText(m.getTitle());
//    ti.setText(m.getTime());
//    l.setText(m.getLanguage());
//    sn.setText(m.getDirectorName());
//
//    buttonIn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            dialog.dismiss();
//        }
//    });
//    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//    dialog.setCancelable(false);
//    dialog.show();
//}
//    /**
//     *  פתיחת אפליקצית שליחת sms
//     * @param msg .. ההודעה שרוצים לשלוח
//     * @param phone
//     */
//    public void openSendSmsApp(String msg, String phone)
//    {
//        //אינטנט מרומז לפתיחת אפליקצית ההודות סמס
//        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
//        //מעבירים מספר הטלפון
//        smsIntent.setData(Uri.parse("smsto:"+phone));
//        //ההודעה שנרצה שתופיע באפליקצית ה סמס
//        smsIntent.putExtra("sms_body",msg);
//        smsIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
//        //פתיחת אפליקציית ה סמס
//        getContext().startActivity(smsIntent);
//    }

}