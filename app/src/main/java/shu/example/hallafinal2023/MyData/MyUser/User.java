package shu.example.hallafinal2023.MyData.MyUser;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
// جدول
//عندما نريد ان نتعامل مع هذه الفئة كجدول معطيات

@Entity
public class User {
    @PrimaryKey(autoGenerate = true) //والذي ينتج بشكل تلقائي تحديد الصفة كمفتاح رئيسي
    public long keyid;
    @ColumnInfo(name = "fullName") // اعطاء اسم جديد للعامود في الجدول
    public String fullName;
    public String email; // في حال لم يتم اعطاء اسم جديد للعامود يكون اسم الصفة هو اسم العامود
    public String phone;
    public String passw;

    @Override
    public String toString() {
        return "newuser{" +
                "keyid=" + keyid +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passw='" + passw + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
