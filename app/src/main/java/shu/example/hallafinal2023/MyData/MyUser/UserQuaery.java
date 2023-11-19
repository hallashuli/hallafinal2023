package shu.example.hallafinal2023.MyData.MyUser;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import shu.example.hallafinal2023.Data.Data.Data.User;
import shu.example.hallafinal2023.MyData.MyUser.User;

/**
 * الواجهة تحتوي استعلامات على قاعدة البيانات
 */

@Dao
public interface UserQuaery {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE keyid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM User WHERE email = :myEmail AND " +
            "passw = :myPassw LIMIT 1")
    User checkEmailPassw(String myEmail, String myPassw);

    @Query("SELECT * FROM User WHERE email = :myEmail ")
    User checkEmail(String myEmail);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("Delete From User WHERE keyid=:id ")
    void delete(int id);

    @Insert
    void insert(User myUser);

    @Update
    void update(User... values);
}