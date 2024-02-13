package shu.example.hallafinal2023.MyData.MyFilmTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Film {
    @PrimaryKey(autoGenerate = true) //والذي ينتج بشكل تلقائي تحديد الصفة كمفتاح رئيسي
    //اسم الفيلم
    public String FilmName;
    //نوع الفيلم
    public String Type;
    public Double Time;
    //لغة الفيلم
    public String Langage;
    //رقم الموسم
    public Integer SeosonNuumber;

    /**
     * Gitter
     **/
    public String FilmName(){return FilmName;}
    public String Type(){return Type;}
    public Double Time(){return Time;}
    public String Langage(){return Langage;}
    public Integer SeosonNuber(){ return SeosonNuumber;}

    /**
     * Sitter
     **/
    public void setFilmName(String name) {
        FilmName = name;
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
        return "Film{" +
                "Name='" + FilmName + '\'' +
                ", Type='" + Type + '\'' +
                ", Time=" + Time +
                ", Langage='" + Langage + '\'' +
                ", SeosonNuumber=" + SeosonNuumber +
                '}';
    }
}
