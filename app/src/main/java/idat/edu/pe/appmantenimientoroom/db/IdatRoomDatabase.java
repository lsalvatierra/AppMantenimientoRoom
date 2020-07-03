package idat.edu.pe.appmantenimientoroom.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import idat.edu.pe.appmantenimientoroom.db.dao.TarjetaDao;
import idat.edu.pe.appmantenimientoroom.db.entity.TarjetaEntity;

@Database(entities = {TarjetaEntity.class}, version = 1)
public abstract class IdatRoomDatabase extends RoomDatabase {

    public abstract TarjetaDao tarjetaDao();
    private static volatile IdatRoomDatabase INSTANCIA;

    public static IdatRoomDatabase getDatabase(final Context context){
        if(INSTANCIA == null){
            synchronized (IdatRoomDatabase.class){
                if(INSTANCIA == null){
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(),
                            IdatRoomDatabase.class, "idatdatabase")
                    .build();
                }
            }
        }
        return INSTANCIA;
    }

}
