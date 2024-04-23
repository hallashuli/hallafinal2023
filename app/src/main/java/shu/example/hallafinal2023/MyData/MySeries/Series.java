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
    public String SeriesType;
    //مدة المسلسل
    public String SeriesTime;
    //لغة امسلسل
    public String SeriesLangage;
    //رقم الحلقات
    public String SeriesEpisodeNumber;
    //gitter+sitter
    //id
    public String getSid() {return Sid;}
    public void setSid(String sid) {
        Sid = sid;
    }
    //name
    public String getSeriesName() {
        return SeriesName;
    }
    public void setSeriesName(String seriesName) {
        SeriesName = seriesName;
    }
    //Type
    public String getSeriesType() {
        return SeriesType;
    }
    public void setSeriesType(String seriesType) {
        SeriesType = seriesType;
    }
    //Time
    public String getSeriesTime() {
        return SeriesTime;
    }
    public void setSeriesTime(String seriesTime) {
        SeriesTime = seriesTime;
    }
    //Langage
    public String getSeriesLangage() {
        return SeriesLangage;
    }
    public void setSeriesLangage(String seriesLangage) {
        SeriesLangage = seriesLangage;
    }
    //EpisodeNumber
    public String getSeriesEpisodeNumber() {
        return SeriesEpisodeNumber;
    }
    public void setSeriesEpisodeNumber(String seriesEpisodeNumber) {SeriesEpisodeNumber = seriesEpisodeNumber;}
    //ToString
    @Override
    public String toString() {
        return "Series{" +
                "Sid='" + Sid + '\'' +
                ", SeriesName='" + SeriesName + '\'' +
                ", SeriesType='" + SeriesType + '\'' +
                ", SeriesTime='" + SeriesTime + '\'' +
                ", SeriesLangage='" + SeriesLangage + '\'' +
                ", SeriesEpisodeNumber='" + SeriesEpisodeNumber + '\'' +
                '}';
    }
}
