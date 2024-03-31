package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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
        //
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
    //Firebase
    private void saveUser_FB(String name1,String Type1,String lang1,Integer seoson1 )
    {
        //
        FirebaseFirestore dp=FirebaseFirestore.getInstance();
        //
        
    }

}