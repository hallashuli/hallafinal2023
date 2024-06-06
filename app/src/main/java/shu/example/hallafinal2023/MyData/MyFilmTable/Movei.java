package shu.example.hallafinal2023.MyData.MyFilmTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movei {
    @PrimaryKey(autoGenerate = true) //والذي ينتج بشكل تلقائي تحديد الصفة كمفتاح رئيسي
    //رقم الفيلم
    public String mid;
    //صفة اضافة الفيلم
    public String mUid;
    //اسم الفيلم
    public String moveiName;
    //نوع الفيلم
    public String moveiType;
    //مدة الفيلم
    public String moveiTime;
    //لغة الفيلم
    public String moveiLangage;
    //رقم الموسم
    public String moveiSeosonNuumber;
    //
    public String image;
    //Gitter+Sitter
    //id
    public String getMid() {return mid;}
    public void setMid(String mid) {this.mid = mid;}
    //name
    public String getMoveiName() {return moveiName;}
    public void setMoveiName(String moveiName) {this.moveiName = moveiName;}
    //type
    public String getMoveiType() {return moveiType;}
    public void setMoveiType(String moveiType) {this.moveiType = moveiType;}
    //time
    public String getMoveiTime() {return moveiTime;}
    public void setMoveiTime(String moveiTime) {this.moveiTime = moveiTime;}
    //langage
    public String getMoveiLangage() {return moveiLangage;}
    public void setMoveiLangage(String moveiLangage) {this.moveiLangage = moveiLangage;}
    //seoson number
    public String getMoveiSeosonNuumber() {return moveiSeosonNuumber;}
    public void setMoveiSeosonNuumber(String moveiSeosonNuumber) {this.moveiSeosonNuumber = moveiSeosonNuumber;}
    // mUid
    public String getmUid() {return mUid;}
    public void setmUid(String mUid){this.mUid = mUid;}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Movei{" +
                "mid='" + mid + '\'' +
                ", mUid='" + mUid + '\'' +
                ", moveiName='" + moveiName + '\'' +
                ", moveiType='" + moveiType + '\'' +
                ", moveiTime='" + moveiTime + '\'' +
                ", moveiLangage='" + moveiLangage + '\'' +
                ", moveiSeosonNuumber='" + moveiSeosonNuumber + '\'' +
                '}';
    }

}
