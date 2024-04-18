package shu.example.hallafinal2023.MyData.MySeries;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import shu.example.hallafinal2023.MyData.MyFilmTable.Movei;
@Dao // واجهة استعلامات على جدول المعطيات
public interface SeriesQuery {
    /***اعادة جميع معطيات الجدول**/
    @Query("Select* From Series")
    List<Series> getAllSeries();
    /***ادخال مجموعة من الافلام **/
    @Insert
    void insertSeries(Series... series); // القاط يمكن ادخال كائن او مجموعة
    /*** تعديل */
    @Update
    void updateSeries(Series series);
}
