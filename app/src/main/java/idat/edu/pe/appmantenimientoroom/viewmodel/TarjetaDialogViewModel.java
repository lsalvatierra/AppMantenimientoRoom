package idat.edu.pe.appmantenimientoroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import idat.edu.pe.appmantenimientoroom.TarjetaRepository;
import idat.edu.pe.appmantenimientoroom.db.entity.TarjetaEntity;

public class TarjetaDialogViewModel extends AndroidViewModel {

    private LiveData<List<TarjetaEntity>> allTarjetas;
    private TarjetaRepository tarjetaRepository;

    public TarjetaDialogViewModel(@NonNull Application application) {
        super(application);
        tarjetaRepository = new TarjetaRepository(application);
        allTarjetas = tarjetaRepository.getAll();
    }
    // TODO: Implement the ViewModel

    //La actividad que necesita recibir la nueva lista de datos
    public LiveData<List<TarjetaEntity>> getAllTarjetas (){
        return allTarjetas;
    }

    //El fragmento que inserte una nueva tarjeta, debera comunicarlo a este viewmodel
    public void insertTarjeta(TarjetaEntity nuevatarjetaentity){
        tarjetaRepository.insert(nuevatarjetaentity);
    }

    public LiveData<TarjetaEntity> getTarjeta(int idtarjeta){
        return tarjetaRepository.get(idtarjeta);
    }

    //El fragmento que actualice una nueva tarjeta, debera comunicarlo a este viewmodel
    public void updateTarjeta(TarjetaEntity nuevatarjetaentity){
        tarjetaRepository.update(nuevatarjetaentity);
    }


    public void deleteTarjeta(int idtarjeta){
        tarjetaRepository.delete(idtarjeta);
    }
}
