package shu.example.hallafinal2023.MyData;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import shu.example.hallafinal2023.MyData.myuser.Myuser;
import shu.example.hallafinal2023.MyData.myuser.MyUserQuery;


    /*** تعريف الجداول و رقم النسخة version عند تغيير اي شيء يخص جدول او جداول علينا تغيير رقم الاصدار ليتم بناء قاعدة البيانات من جديد */
    @Database(entities = {Myuser.class}, version = 2)
    /*** الفئة المسؤلة عن بناء قاعدة بيانات و توفر لناكائن للتعامل مع قاعدة البيانات*/
    public  abstract  class AppDatabase extends RoomDatabase {
        /*** كائن للتعامل مع قاعدة البيانات*/
        private static AppDatabase db;
        /***يعيد كائن جدول المستعملبن*/
        public abstract MyUserQuery getUserQuaery ();
        public static AppDatabase getDB(Context context)
        {
            if (db == null) {
                db = Room.databaseBuilder(context,AppDatabase.class, "database-name")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
            return db;
        }
    }



