package shu.example.hallafinal2023.MyData.MyFilmTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movei {
    @PrimaryKey(autoGenerate = true) //والذي ينتج بشكل تلقائي تحديد الصفة كمفتاح رئيسي
    //رقم الفيلم
    public String mid;
    //اسم الفيلم
    public String MoveiName;
    //نوع الفيلم
    public String MoveiType;
    //مدة الفيلم
    public String MoveiTime;
    //لغة الفيلم
    public String MoveiLangage;
    //رقم الموسم
    public String MoveiSeosonNuumber;
    //Gitter+Sitter
    //id
    public String getmid() {return mid;}
    public void setMid(String mid) {mid = mid;}
    //Name
    public String getMoveiName() {return MoveiName;}
    public void setMoveiName(String moveiName) {MoveiName = moveiName;}
    //Type
    public String getMoveiType() {return MoveiType;}
    public void setMoveiType(String moveiType) {MoveiType = moveiType;}
    //Time
    public String getMoveiTime() {return MoveiTime;}
    public void setMoveiTime(String moveiTime) {MoveiTime = moveiTime;}
    //Langage
    public String getMoveiLangage() {return MoveiLangage;}
    public void setMoveiLangage(String moveiLangage) {MoveiLangage = moveiLangage;}
    //seosonNumber
    public String getMoveiSeosonNuumber() {return MoveiSeosonNuumber;}
    public void setMoveiSeosonNuumber(String moveiSeosonNuumber) {MoveiSeosonNuumber = moveiSeosonNuumber;}
    //To String
    @Override
    public String toString() {
        return "Movei{" +
                "Mid='" + mid + '\'' +
                ", MoveiName='" + MoveiName + '\'' +
                ", MoveiType='" + MoveiType + '\'' +
                ", MoveiTime='" + MoveiTime + '\'' +
                ", MoveiLangage='" + MoveiLangage + '\'' +
                ", MoveiSeosonNuumber='" + MoveiSeosonNuumber + '\'' +
                '}';
    }
}
