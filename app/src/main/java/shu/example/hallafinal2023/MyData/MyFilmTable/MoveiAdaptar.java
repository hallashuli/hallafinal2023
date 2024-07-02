package shu.example.hallafinal2023.MyData.MyFilmTable;

import android.app.Dialog;
import android.content.Context;
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

import shu.example.hallafinal2023.AddMovie;
import shu.example.hallafinal2023.MainActivityMovie;
import shu.example.hallafinal2023.R;

public class MoveiAdaptar extends ArrayAdapter<Movei> {
    private ImageView movei_image;
    private TextView movei_name;
    private ImageView _sendimage;
    private ImageView edit_image;
    private ImageView Reate_image;
    private ImageView informathion_image;
    private ImageView video_play;
    private Dialog dialog;
    //המזהה של קובץ עיצוב הפריט
    private final int itemLayout;

    /**
     * פעולה בונה מתאם
     *
     * @param context  קישור להקשר (מסך- אקטיביטי)
     * @param resource עיצוב של פריט שיציג הנתונים של העצם
     */
    public MoveiAdaptar(@NonNull Context context, int resource) {
        super(context, resource);
        this.itemLayout = resource;
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
        //בניית הפריט הגרפי מתו קובץ העיצוב
        View vitem = convertView;
        if (vitem == null)
            vitem = LayoutInflater.from(getContext()).inflate(itemLayout, parent, false);
        //קבלת הפניות לרכיבים בקובץ העיצוב
        ImageView movei_image = vitem.findViewById(R.id.movei_image);
        TextView movei_name = vitem.findViewById(R.id.movei_name);
        ImageView _sendimage = vitem.findViewById(R.id._sendimage);
        ImageView video_play = vitem.findViewById(R.id.video_play2);
        ImageView btnEdit = vitem.findViewById(R.id.edit_image);
        ImageView btnReate_image = vitem.findViewById(R.id.Reate_image);
        ImageView btnInformathion_image = vitem.findViewById(R.id.informathion_image);


        //קבלת הנתון (עצם) הנוכחי
        Movei current = getItem(position);
        //הצגת הנתונים על שדות הרכיב הגרפי
        movei_name.setText(current.getMoveiName());
        downloadvideothumbnail(current.getVideo(), movei_image);
        video_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showvediodialog(current.video);
            }
        });
        btnReate_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eidtreatingdialog(current);
            }
        });
        btnInformathion_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showreatingdialog(current);
            }
        });
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

    public void showvediodialog(String vedioUrl) {

        if (dialog == null)
            dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_playvideo);
        VideoView videoVdial = dialog.findViewById(R.id.movei_video);
        ImageView cancel_image = dialog.findViewById(R.id.cancel_image);
        cancel_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        // dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        videoVdial.setVideoURI(Uri.parse(vedioUrl));
        // creating object of
        // media controller class
        MediaController mediaController = new MediaController(getContext());
        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(videoVdial);
        // sets the media player to the videoView
        mediaController.setMediaPlayer(videoVdial);

        // sets the media controller to the videoView
        videoVdial.setMediaController(mediaController);
        mediaController.show();
        videoVdial.start();
        dialog.show();
    }

    public void eidtreatingdialog(Movei m) {

        if (dialog == null)
            dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_add_reating);
        RatingBar ratingTV = dialog.findViewById(R.id.ratingTV);
        TextInputEditText comment = dialog.findViewById(R.id.comment);
        Button saveR = dialog.findViewById(R.id.saveR);

        saveR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=comment.getText().toString();
                float r= ratingTV.getRating();
                MoveiRating mr=new MoveiRating();
                mr.setRate(r);
                mr.setComment(s);
                mr.settId(m.getmUid());
                m.getMoveiRatings().add(mr);
                UpdateMovei_FB(m);
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
    private void UpdateMovei_FB(Movei m)
    {
        //مؤشر لقاعدة البيانات
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //بناء الكائن الذي سيتم حفظه
        //اضافة كائن لمجموعة الافلام ومعالج حدث لفحص نجاح الاضافة
        db.collection("Mymovies").document(m.mid).set(m).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Succeed to Update Rating Movei", Toast.LENGTH_SHORT).show();
                    ((MainActivityMovie)getContext()).readMoveiFrom_FB();
                } else {
                    Toast.makeText(getContext(), "Failed to Update Rating Movei", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showreatingdialog(Movei m) {

        if (dialog == null)
            dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_show_rating);
        RatingBar ratingShow = dialog.findViewById(R.id.ratingShow);
        TextView commentShow = dialog.findViewById(R.id.commentShow);
        Button CancelR = dialog.findViewById(R.id.CancelR);
        float r=0;
        StringBuffer s=new StringBuffer();
        for (MoveiRating moveiRating : m.getMoveiRatings()) {
            r=r+moveiRating.getRate()/m.getMoveiRatings().size();
            s.append(moveiRating.getComment());
            s.append('\n');//enter
        }
        ratingShow.setRating(r);
        commentShow.setText(s);
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

    public void ShowMoveiInformationdialog(Movei m) {

        if (dialog == null)
            dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_show_information);
        TextView n= dialog.findViewById(R.id.n);
        TextView t= dialog.findViewById(R.id.t);
        TextView ti= dialog.findViewById(R.id.ti);
        TextView l= dialog.findViewById(R.id.l);
        TextView sn= dialog.findViewById(R.id.sn);
        Button buttonIn = dialog.findViewById(R.id.buttonIn);
        buttonIn.setOnClickListener(new View.OnClickListener() {
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
}





