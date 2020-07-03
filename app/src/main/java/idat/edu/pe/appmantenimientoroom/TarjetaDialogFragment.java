package idat.edu.pe.appmantenimientoroom;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import idat.edu.pe.appmantenimientoroom.db.entity.TarjetaEntity;
import idat.edu.pe.appmantenimientoroom.viewmodel.TarjetaDialogViewModel;

public class TarjetaDialogFragment extends DialogFragment {

    private View view;
    private EditText ettitulo, etcontenido;
    private RadioGroup rgcolor;
    private RadioButton rbtnamarillo, rbtnrojo, rbtnverde;
    private Switch swimportante;
    private Integer idtarjeta = 0;
    private TarjetaDialogViewModel mViewModel;


    public static TarjetaDialogFragment newInstance() {
        return new TarjetaDialogFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            idtarjeta = getArguments().getInt("idtarjeta");
            Log.i("PARAMFRAG", idtarjeta.toString());
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(getActivity()).get(TarjetaDialogViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nueva nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String titulo = ettitulo.getText().toString();
                        String contenido = etcontenido.getText().toString();
                        String color = "amarillo";
                        switch (rgcolor.getCheckedRadioButtonId()){
                            case R.id.rbtnrojo : color = "rojo"; break;
                            case R.id.rbtnverde: color = "verde"; break;
                        }
                        boolean esimportante = swimportante.isChecked();
                        //Comunicar el viewmodel el nuevo dato.
                        if(idtarjeta > 0){
                            mViewModel.updateTarjeta(new TarjetaEntity(idtarjeta, titulo, contenido, esimportante, color));
                        }else{
                            mViewModel.insertTarjeta(new TarjetaEntity(0, titulo, contenido, esimportante, color));
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.tarjeta_dialog_fragment, null);
        etcontenido = view.findViewById(R.id.etcontenido);
        ettitulo = view.findViewById(R.id.ettitulo);
        rgcolor = view.findViewById(R.id.radiogroupcolor);
        rbtnamarillo = view.findViewById(R.id.rbtnamarillo);
        rbtnrojo = view.findViewById(R.id.rbtnrojo);
        rbtnverde = view.findViewById(R.id.rbtnverde);
        swimportante = view.findViewById(R.id.swfavorito);
        if(idtarjeta > 0){
            mViewModel
                    .getTarjeta(idtarjeta).observe(getActivity(),
                    new Observer<TarjetaEntity>() {
                        @Override
                        public void onChanged(TarjetaEntity tarjetaEntity) {
                            CargarInfoTarjeta(tarjetaEntity);
                        }
                    });
        }
        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void CargarInfoTarjeta(TarjetaEntity tarjetaEntity){
        etcontenido.setText(tarjetaEntity.getContenido());
        ettitulo.setText(tarjetaEntity.getTitulo());
        if(tarjetaEntity.isImportante()){
            swimportante.setChecked(true);
        }else{
            swimportante.setChecked(false);
        }
        switch (tarjetaEntity.getColor()){
            case "amarillo": rbtnamarillo.setChecked(true); break;
            case "rojo": rbtnrojo.setChecked(true); break;
            case "verde": rbtnverde.setChecked(true); break;
        }
    }

}
