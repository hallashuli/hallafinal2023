package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabAdd;
    private Button btnM;
    private Button btnS;
    private SearchView srchv;
    private Spinner spnr;
    private ListView istTv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnM=findViewById(R.id.btnM);
        btnS=findViewById(R.id.btnS);
        spnr=findViewById(R.id.spnr);
        istTv=findViewById(R.id.istTv);
        fabAdd=findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, .class);
                startActivity(i);
                finish();
            }



    }
    //يتم استدعاء هذه الحالة عندما يكون النشاط على وشك أن يصبح مرئيًا للمستخدم. في هذه الحالة، يجب على النشاط بدء أي رسوم متحركة أو عمليات أخرى يحتاج إلى تشغيلها أثناء رؤيته.
    @Override
        {
    protected void onStart() super.onResume();
    }
}
