package shu.example.hallafinal2023.MyData.MyFilmTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movei {
    @PrimaryKey(autoGenerate = true) //والذي ينتج بشكل تلقائي تحديد الصفة كمفتاح رئيسي

    public long Mid;
    //اسم الفيلم
    public String MoveiName;
    //نوع الفيلم
    public String Type;
    //مدة الفيلم
    public Double Time;
    //لغة الفيلم
    public String Langage;
    //رقم الموسم
    public Integer SeosonNuumber;



    /**
     * Gitter
     **/
    public String MoveiName(){return MoveiName;}
    public String Type(){return Type;}
    public Double Time(){return Time;}
    public String Langage(){return Langage;}
    public Integer SeosonNuber(){ return SeosonNuumber;}

    /**
     * Sitter
     **/
    public void setFilmName(String name) {
        MoveiName = name;
    }
    public void setType(String type) {
        Type = type;
    }
    public void setTime(Double time) {
        Time = time;
    }
    public void setLangage(String langage) {
        Langage = langage;
    }
    public void setSeosonNuumber(Integer seosonNuumber) {
        SeosonNuumber = seosonNuumber;
    }

    /**
     * toString
     **/
    @Override
    public String toString() {
        return "Movei{" +
                "Mid=" + Mid +
                ", MoveiName='" + MoveiName + '\'' +
                ", Type='" + Type + '\'' +
                ", Time=" + Time +
                ", Langage='" + Langage + '\'' +
                ", SeosonNuumber=" + SeosonNuumber +
                '}';
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

    public Double getTime() {
        return Time;
    }

    public String getLangage() {
        return Langage;
    }

    public Integer getSeosonNuumber() {
        return SeosonNuumber;
    }

    public long getMid() {
        return Mid;
    }

    public void setMid(long mid) {
        Mid = mid;
    }
}
