package idat.edu.pe.appmantenimientoroom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import idat.edu.pe.appmantenimientoroom.db.IdatRoomDatabase;
import idat.edu.pe.appmantenimientoroom.db.dao.TarjetaDao;
import idat.edu.pe.appmantenimientoroom.db.entity.TarjetaEntity;

public class TarjetaRepository {
    private TarjetaDao tarjetaDao;

    public TarjetaRepository(Application application) {
        IdatRoomDatabase db = IdatRoomDatabase.getDatabase(application);
        tarjetaDao = db.tarjetaDao();
    }

    public LiveData<List<TarjetaEntity>> getAll(){
        return tarjetaDao.getAll();
    }

    public LiveData<TarjetaEntity> get(int idtarjeta){
        return tarjetaDao.getTarjeta(idtarjeta);
    }

    public void insert(TarjetaEntity tarjeta){
        new insertAsyncTask(tarjetaDao).execute(tarjeta);
    }

    private static class insertAsyncTask extends AsyncTask<TarjetaEntity, Void, Void> {
        private TarjetaDao tarjetaDaoAsyncTask;
        insertAsyncTask(TarjetaDao dao){
            tarjetaDaoAsyncTask  = dao;
        }
        @Override
        protected Void doInBackground(TarjetaEntity... tarjetaEntities) {
            tarjetaDaoAsyncTask.insert(tarjetaEntities[0]);
            return null;
        }
    }

    public void update(TarjetaEntity tarjeta){
        new updateAsyncTask(tarjetaDao).execute(tarjeta);
    }

    private static class updateAsyncTask extends AsyncTask<TarjetaEntity, Void, Void>{
        private TarjetaDao tarjetaDaoAsyncTask;
        updateAsyncTask(TarjetaDao dao){
            tarjetaDaoAsyncTask  = dao;
        }
        @Override
        protected Void doInBackground(TarjetaEntity... tarjetaEntities) {
            tarjetaDaoAsyncTask.update(tarjetaEntities[0]);
            return null;
        }
    }

    public void delete(int idtarjeta){
        new deleteAsyncTask(tarjetaDao).execute(idtarjeta);
    }

    private static class deleteAsyncTask extends AsyncTask<Integer, Void, Void>{
        private TarjetaDao tarjetaDaoAsyncTask;
        deleteAsyncTask(TarjetaDao dao){
            tarjetaDaoAsyncTask  = dao;
        }
        @Override
        protected Void doInBackground(Integer... integers) {
            tarjetaDaoAsyncTask.deleteById(integers[0]);
            return null;
        }
    }

}
