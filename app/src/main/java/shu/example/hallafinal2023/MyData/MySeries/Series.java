package shu.example.hallafinal2023.MyData.MySeries;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Series {
    @PrimaryKey(autoGenerate = true) //الذي ينتج بشكل تلقائي تحديد الصفة كمفتاح رئيسي
    //رقم الفيلم
    public String Sid;
    //اسم المسلسل
    public String SeriesName;
    //نوع المسلسل
    public String Type;
    //مدة المسلسل
    public String Time;
    //لغة امسلسل
    public String Langage;
    //رقم الحلقات
    public String EpisodeNumber;
    //Gitte+Sitter
    public String getSid() {return Sid;}
    public void setSid(String sid) {Sid = sid;}

    public String getSeriesName() {return SeriesName;}
    public void setSeriesName(String seriesName) {SeriesName = seriesName;}

    public String getType() {return Type;}
    public void setType(String type) {Type = type;}

    public String getTime() {return Time;}
    public void setTime(String time) {Time = time;}

    public String getLangage() {return Langage;}
    public void setLangage(String langage) {Langage = langage;}

    public String getEpisodeNumber() {return EpisodeNumber;}
    public void setEpisodeNumber(String episodeNumber) {EpisodeNumber = episodeNumber;}
    //To String
    @Override
    public String toString() {
        return "Series{" +
                "Sid='" + Sid + '\'' +
                ", SeriesName='" + SeriesName + '\'' +
                ", Type='" + Type + '\'' +
                ", Time='" + Time + '\'' +
                ", Langage='" + Langage + '\'' +
                ", EpisodeNumber='" + EpisodeNumber + '\'' +
                '}';
    }
}
