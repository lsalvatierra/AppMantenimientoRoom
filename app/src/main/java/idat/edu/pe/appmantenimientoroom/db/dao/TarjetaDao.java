package idat.edu.pe.appmantenimientoroom.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import idat.edu.pe.appmantenimientoroom.db.entity.TarjetaEntity;

@Dao
public interface TarjetaDao {

    @Insert
    void insert(TarjetaEntity tarjeta);

    @Update
    void update(TarjetaEntity tarjeta);


    @Query("DELETE FROM tarjeta WHERE id = :idtarjeta")
    void deleteById(int idtarjeta);

    @Query("SELECT * FROM tarjeta ORDER BY titulo ASC")
    LiveData<List<TarjetaEntity>> getAll();

    @Query("select * from tarjeta WHERE id = :idtarjeta")
    LiveData<TarjetaEntity> getTarjeta(int idtarjeta);
}
