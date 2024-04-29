package shu.example.hallafinal2023.MyData.MySeries;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Series {
    @PrimaryKey(autoGenerate = true) //الذي ينتج بشكل تلقائي تحديد الصفة كمفتاح رئيسي
    //رقم الفيلم
    public String sid;
    //اسم المسلسل
    public String seriesName;
    //نوع المسلسل
    public String seriesType;
    //مدة المسلسل
    public String seriesTime;
    //لغة امسلسل
    public String seriesLangage;
    //رقم الحلقات
    public String seriesEpisodeNumber;
    //Gitter+Sitter
    //id
    public String getSid() {return sid;}
    public void setSid(String sid) {this.sid = sid;}
    //name
    public String getSeriesName() {return seriesName;}
    public void setSeriesName(String seriesName) {this.seriesName = seriesName;}
    //type
    public String getSeriesType() {return seriesType;}
    public void setSeriesType(String seriesType) {this.seriesType = seriesType;}
    //type
    public String getSeriesTime() {return seriesTime;}
    public void setSeriesTime(String seriesTime) {this.seriesTime = seriesTime;}
    //langage
    public String getSeriesLangage() {return seriesLangage;}
    public void setSeriesLangage(String seriesLangage) {this.seriesLangage = seriesLangage;}
    //episodeNumber
    public String getSeriesEpisodeNumber() {return seriesEpisodeNumber;}
    public void setSeriesEpisodeNumber(String seriesEpisodeNumber) {this.seriesEpisodeNumber = seriesEpisodeNumber;}
    //to String

    @Override
    public String toString() {
        return "Series{" +
                "sid='" + sid + '\'' +
                ", seriesName='" + seriesName + '\'' +
                ", seriesType='" + seriesType + '\'' +
                ", seriesTime='" + seriesTime + '\'' +
                ", seriesLangage='" + seriesLangage + '\'' +
                ", seriesEpisodeNumber='" + seriesEpisodeNumber + '\'' +
                '}';
    }
}
