package shu.example.hallafinal2023;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;
import shu.example.hallafinal2023.MyData.MyFilmTable.Movei;
import shu.example.hallafinal2023.MyData.MyFilmTable.MoveiAdaptar;

public class MainActivityMovie extends AppCompatActivity {
    private FloatingActionButton fabAdd;
    private SearchView srchv;
    private Spinner spnrM;
    private ListView istTv;
    private MoveiAdaptar moveiAdaptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_movie);
        istTv = findViewById(R.id.istTvM);
        //الغرض: إنشاء كائن جديد من فئة MoveiAdaptar، وهو مخصص لعرض البيانات في واجهة المستخدم.
        moveiAdaptar = new MoveiAdaptar(this, R.layout.movei_item_layout);
        //يقوم بتعيين moveiAdaptar كمحول لـ istTv، بحيث يستخدم moveiAdaptar لتحديد كيفية عرض البيانات في القائمة.
        istTv.setAdapter(moveiAdaptar);
        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityMovie.this, AddMovie.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    protected  void onResume(){
        super.onResume();
        readMoveiFrom_FB();
    }
    @Override//بناء قائمة
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opitionmain_menu, menu);
        return true;
    }
    //menu
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId() == R.id.sitting) {
            Intent i=new Intent(MainActivityMovie.this,Settings.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.logout){
            showYesNoDialog();
        }
        if (item.getItemId()==R.id.itmAddMovei)
        {
            Toast.makeText(MainActivityMovie.this, "Add", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(MainActivityMovie.this,AddMovie.class);
        }
        return true;
    }
    /**
     * دالة مساعدة لفتح قائمة تتلقى
     * بارامتر للكائن لفتح الذي يسبب فتح قائمة
     *
     * @param v
     */
    public void ShowPoupMenu(View v, Movei item) {
        //popup menu بناء قائمة
        PopupMenu popupMenu = new PopupMenu(this, v);//الكائن الذي سبب فتح القائمة v
        //ملف القائمة
        popupMenu.inflate(R.menu.popup_menu);
        //اضافة معالج دث عنصر لاختيار عنصر من القائمة
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.itmAdd) {
                    //هنا نكتب رد فعل لاختيار هذا العنصر من القائمة
                    Toast.makeText(MainActivityMovie.this, "ADD", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivityMovie.this, AddMovie.class);
                    startActivity(i);
                }
                if (menuItem.getItemId() == R.id.itmDelete) {
                    Toast.makeText(MainActivityMovie.this, "Delete", Toast.LENGTH_SHORT).show();
                }
                if (menuItem.getItemId() == R.id.itmEidt) {
                    Toast.makeText(MainActivityMovie.this, "Complete", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        popupMenu.show();//فتح و عرض القائمة
    }
        /**
         * بناء ديالوج
         */
        public void showYesNoDialog()
        {
            //تجهيز بناء شباك حوار ديالوج يتلقى برامتر مؤشر للنشاط (اكتفتي) الحالي
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Log Out");//تحديد العنوان
            builder.setMessage("Are you sure?");//تحدي فحوى شباك الحوار
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                //  // عند الضغط على زر "Yes" يتم معالجة الحدث
                public void onClick(DialogInterface dialogInterface, int i) {
                    //معالجة حدث للمرافقة
                    Toast.makeText(MainActivityMovie.this, "Sing Out", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            ////ضغط على زر معالج الحدث
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //معالجة حدث للمرافقة
                    Toast.makeText(MainActivityMovie.this, "Sing Out", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog=builder.create();//بناء شباك الحوار ديالوغ
            dialog.show();//عرض الشباك
        }
        /**
         * קריאת נתונים ממסד הנתונים firestore
         *
         * @return .... רשימת הנתונים שנקראה ממסד הנתונים
         */
        public void readMoveiFrom_FB () {
            //בניית רשימה ריקה
            ArrayList<Movei> arrayList = new ArrayList<>();
            //קבלת הפנייה למסד הנתונים
            FirebaseFirestore ffRef = FirebaseFirestore.getInstance();
            //קישור לקבוצה collection שרוצים לקרוא
            ffRef.collection("Mymovies")
                    //הוספת מאזין לקריאת הנתונים
                           .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        /**
                         * תגובה לאירוע השלמת קריאת הנתונים
                         *
                         * @param task הנתונים שהתקבלו מענן מסד הנתונים
                         */
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {// אם בקשת הנתונים התקבלה בהצלחה
                                //מעבר על כל ה״מסמכים״= עצמים והוספתם למבנה הנתונים
                                for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                    //המרת העצם לטיפוס שלו// הוספת העצם למבנה הנתונים
                                    arrayList.add(document.toObject(Movei.class));
                                }
                                moveiAdaptar.clear();//ניקוי המתאם מכל הנתונים
                                moveiAdaptar.addAll(arrayList);//הוספת כל הנתונים למתאם
                            } else {
                                Toast.makeText(MainActivityMovie.this, "Error Reading data" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }



