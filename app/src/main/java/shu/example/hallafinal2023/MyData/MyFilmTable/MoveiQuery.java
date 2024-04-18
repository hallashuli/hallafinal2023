package shu.example.hallafinal2023.MyData.MyFilmTable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao // واجهة استعلامات على جدول المعطيات
public interface MoveiQuery {
    /***اعادة جميع معطيات الجدول**/
    @Query("Select* From Movei")
    List<Movei> getAllFilm();
    /***ادخال مجموعة من الافلام **/
    @Insert
    void insertFilm(Movei... moveis); // القاط يمكن ادخال كائن او مجموعة
    /*** تعديل */
    @Update
    void updateFilm(Movei movei);
}
