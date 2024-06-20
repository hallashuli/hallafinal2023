package shu.example.hallafinal2023;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.material.textfield.TextInputEditText;

import shu.example.hallafinal2023.MyData.MyFilmTable.Movei;

public class PlayvideoActivity extends AppCompatActivity {
    Movei movei;
    private TextView nameformovei;
    private TextView infoformovei;
    private VideoView movei_video;
    private ImageView play_video;
    private ImageView poss_video;
    private ImageView stop_video;
    private ImageView seek_lvideo;
    private ImageView seek_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        nameformovei=findViewById(R.id.nameformovei);
        infoformovei=findViewById(R.id.nameformovei);
        movei_video=findViewById(R.id.movei_video);
        play_video=findViewById(R.id.play_video);
        poss_video=findViewById(R.id.poss_video);
        stop_video=findViewById(R.id.stop_video);
        seek_lvideo=findViewById(R.id.seek_lvideo);
        seek_video=findViewById(R.id.seek_video);


    }
    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent()!=null&& getIntent().getExtras().get("vid")!=null)
        {
          movei= (Movei) getIntent().getExtras().get("vid");
          nameformovei.setText(movei.getMoveiName());
          infoformovei.setText(movei.toString());

          movei_video.setVideoURI(Uri.parse(movei.getVideo()));
          movei_video.seekTo(2);
          play_video.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                 if( movei_video.isPlaying()==false)
                     movei_video.start();
              }
          });
          poss_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( movei_video.isPlaying()==true)
                        movei_video.pause();
                }
            });
            stop_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( movei_video.isPlaying()==true)
                        movei_video.stopPlayback();
                }
            });
            seek_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( movei_video.canSeekForward())
                        movei_video.seekTo(1000);

                }
            });
          seek_lvideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( movei_video.canSeekBackward())
                        movei_video.seekTo(1000);
                }
            });








        }

    }
}