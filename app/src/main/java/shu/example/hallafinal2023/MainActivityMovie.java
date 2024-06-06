package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
        moveiAdaptar = new MoveiAdaptar(this, R.layout.movei_item_layout);
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

    /**
     * קריאת נתונים ממסד הנתונים firestore
     *
     * @return .... רשימת הנתונים שנקראה ממסד הנתונים
     */
    public void readMoveiFrom_FB() {
        //בניית רשימה ריקה
        ArrayList<Movei> arrayList = new ArrayList<>();
        //קבלת הפנייה למסד הנתונים
        FirebaseFirestore ffRef = FirebaseFirestore.getInstance();
        //קישור לקבוצה collection שרוצים לקרוא
        ffRef.collection("MyUsers").
                document(FirebaseAuth.getInstance().getUid()).
                collection("Movei").
                document(spnrM.getSelectedItem().toString()).
                //הוספת מאזין לקריאת הנתונים
                        collection("Tasks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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


