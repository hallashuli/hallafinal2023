package shu.example.hallafinal2023.MyData.MyFilmTable;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao // واجهة استعلامات على جدول المعطيات
public interface FilmQuery {
    @Query("Select* From Film")
    List<Film> getAllFilm
}
