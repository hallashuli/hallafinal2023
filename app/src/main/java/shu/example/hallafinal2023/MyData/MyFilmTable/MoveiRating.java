package shu.example.hallafinal2023.MyData.MyFilmTable;

import android.app.Dialog;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.VideoView;

import com.google.android.material.textfield.TextInputEditText;

import shu.example.hallafinal2023.R;

public class MoveiRating
{
    public String tId;
    public String comment;
    public float rate;
    private Dialog dialog;
    private Movei m;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public Movei getM() {
        return m;
    }

    public void setM(Movei m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return "MoveiRating{" +
                "tId='" + tId + '\'' +
                ", comment='" + comment + '\'' +
                ", rate=" + rate +
                ", dialog=" + dialog +
                ", m=" + m +
                '}';
    }
}
