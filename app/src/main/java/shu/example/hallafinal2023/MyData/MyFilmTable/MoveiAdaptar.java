package shu.example.hallafinal2023.MyData.MyFilmTable;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import shu.example.hallafinal2023.PlayvideoActivity;
import shu.example.hallafinal2023.R;

public class MoveiAdaptar extends ArrayAdapter<Movei> {
    private ImageView movei_image;
    private TextView movei_name;
    private ImageView _sendimage;
    private ImageView edit_image;
    private ImageView delete_image;
    private ImageView informathion_image;
    private ImageView video_play;
    //המזהה של קובץ עיצוב הפריט
    private final int itemLayout;
    /**
     * פעולה בונה מתאם
     * @param context קישור להקשר (מסך- אקטיביטי)
     * @param resource עיצוב של פריט שיציג הנתונים של העצם
     */
    public MoveiAdaptar(@NonNull Context context, int resource)
    {
        super(context, resource);
        this.itemLayout = resource;
    }
    /**
     * בונה פריט גרפי אחד בהתאם לעיצוב והצגת נתוני העצם עליו
     * @param position מיקום הפריט החל מ 0
     * @param convertView
     * @param parent רכיב האוסף שיכיל את הפריטים כמו listview
     * @return  . פריט גרפי שמציג נתוני עצם אחד
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        //בניית הפריט הגרפי מתו קובץ העיצוב
        View vitem= convertView;
        if(vitem==null)
            vitem= LayoutInflater.from(getContext()).inflate(itemLayout,parent,false);
        //קבלת הפניות לרכיבים בקובץ העיצוב
        ImageView movei_image=vitem.findViewById(R.id.movei_image);
        TextView movei_name=vitem.findViewById(R.id.movei_name);
        ImageView _sendimage=vitem.findViewById(R.id._sendimage);
        ImageView video_play=vitem.findViewById(R.id.video_play2);
        ImageView btnEdit=vitem.findViewById(R.id.edit_image);
        ImageView btnCall=vitem.findViewById(R.id.delete_image);
        ImageView btnDel=vitem.findViewById(R.id.informathion_image);
        //קבלת הנתון (עצם) הנוכחי
        Movei current=getItem(position);
        //הצגת הנתונים על שדות הרכיב הגרפי
        movei_name.setText(current.getMoveiName());
        downloadvideothumbnail(current.getVideo(),movei_image);
        video_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PlayvideoActivity.class);
                i.putExtra("vid",current);
                getContext().startActivity(i);
            }
        });
        return vitem;
    }
    private void downloadimagethumbnail(String image, ImageView imgbtnmed) {
        //  long interval = getPosition()*1000;
        //RequestOptions options = new RequestOptions().frame(interval);
        if (image==null||image.length()==0)
        {
            return;
        }
        Glide.with(getContext()).asBitmap()
                .load(Uri.parse(image))
                //.apply(options)
                .into(imgbtnmed);}

    private void downloadvideothumbnail(String video, ImageView vifbtnmed) {
        //  long interval = getPosition()*1000;
        //RequestOptions options = new RequestOptions().frame(interval);
        if (video==null||video.length()==0)
        {
            return;
        }
        Glide.with(getContext()).asBitmap()
                .load(Uri.parse(video))
                //.apply(options)
                .into(vifbtnmed);}


    }




