package shu.example.hallafinal2023.MyData.MyFilmTable;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import shu.example.hallafinal2023.R;

public class MoveiAdaptar extends ArrayAdapter<Movei>
{
    private ImageView movei_image;
    private TextView movei_name;
    private ImageView _sendimage;
    private ImageView edit_image;
    private ImageView delete_image;
    private ImageView informathion_image;
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
        //
        ImageView movei_image=vitem.findViewById(R.id.movei_image);
        TextView movei_name=vitem.findViewById(R.id.movei_name);
        ImageView _sendimage=vitem.findViewById(R.id._sendimage);
        ImageView edit_image=vitem.findViewById(R.id.edit_image);
        ImageView delete_image=vitem.findViewById(R.id.delete_image);
        ImageView informathion_image=vitem.findViewById(R.id.informathion_image);


        return vitem;
    }

}


