package shu.example.hallafinal2023.MyData.MyFilmTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movei {
    @PrimaryKey(autoGenerate = true) //والذي ينتج بشكل تلقائي تحديد الصفة كمفتاح رئيسي
    //رقم الفيلم
    public String Mid;
    //اسم الفيلم
    public String MoveiName;
    //نوع الفيلم
    public String Type;
    //مدة الفيلم
    public String Time;
    //لغة الفيلم
    public String Langage;
    //رقم الموسم
    public String SeosonNuumber;
    //gitter+sitter
    public String getMid() {
        return Mid;
    }
    public void setMid(String mid) {
        Mid = mid;
    }

    public String getMoveiName() {
        return MoveiName;
    }
    public void setMoveiName(String moveiName) {
        MoveiName = moveiName;
    }

    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }

    public String getTime() {return Time;}
    public void setTime(String time) {Time = time;}

    public String getSeosonNuumber() {return SeosonNuumber;}
    public void setSeosonNuumber(String seosonNuumber) {SeosonNuumber = seosonNuumber;}

    public String getLangage() {return Langage;}
    public void setLangage(String langage) {Langage = langage;}
    //to String
    @Override
    public String toString() {
        return "Movei{" +
                "Mid='" + Mid + '\'' +
                ", MoveiName='" + MoveiName + '\'' +
                ", Type='" + Type + '\'' +
                ", Time=" + Time +
                ", Langage='" + Langage + '\'' +
                ", SeosonNuumber=" + SeosonNuumber +
                '}';
    }
}
