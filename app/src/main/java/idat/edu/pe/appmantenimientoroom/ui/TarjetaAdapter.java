package idat.edu.pe.appmantenimientoroom.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import idat.edu.pe.appmantenimientoroom.R;
import idat.edu.pe.appmantenimientoroom.databinding.ItemTarjetaBinding;
import idat.edu.pe.appmantenimientoroom.db.entity.TarjetaEntity;
import idat.edu.pe.appmantenimientoroom.viewmodel.TarjetaDialogViewModel;

public class TarjetaAdapter extends
        RecyclerView.Adapter<TarjetaAdapter.ViewHolder> {

    private List<TarjetaEntity> listatarjeta;
    private Context context;
    private TarjetaDialogViewModel mViewModel;

    public TarjetaAdapter(Context context) {
        this.context = context;
        listatarjeta = new ArrayList<>();
        mViewModel = new ViewModelProvider((AppCompatActivity)this.context).get(TarjetaDialogViewModel.class);
    }

    @NonNull
    @Override
    public TarjetaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTarjetaBinding recyclerBinding = ItemTarjetaBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(recyclerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TarjetaAdapter.ViewHolder holder, final int position) {
        final TarjetaEntity objtarjetaentity = listatarjeta.get(position);
        holder.reclyclerBinding.tvtitulo.setText(objtarjetaentity.getTitulo());
        holder.reclyclerBinding.tvcontenido.setText(objtarjetaentity.getContenido());
        if(objtarjetaentity.isImportante()){
            holder.reclyclerBinding.ivimportante.setImageResource(R.drawable.ic_star_black);
        }else {
            holder.reclyclerBinding.ivimportante.setImageResource(R.drawable.ic_star_border_black);
        }
        holder.reclyclerBinding.ivdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.deleteTarjeta(objtarjetaentity.getId());
            }
        });

        switch (objtarjetaentity.getColor()){
            case "amarillo" : holder.reclyclerBinding.contenedor.setCardBackgroundColor(Color.YELLOW); break;
            case "rojo" : holder.reclyclerBinding.contenedor.setCardBackgroundColor(Color.RED); break;
            case "verde" : holder.reclyclerBinding.contenedor.setCardBackgroundColor(Color.GREEN); break;

        }

        holder.reclyclerBinding.contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle param = new Bundle();
                param.putInt("idtarjeta", listatarjeta.get(position).getId());
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                TarjetaDialogFragment dialogFragment = new TarjetaDialogFragment();
                dialogFragment.setArguments(param);
                dialogFragment.show(fragmentManager, "NuevaTarjetaDialogFragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listatarjeta.size();
    }

    public void setListTarjetas(List<TarjetaEntity> lstnuevastarjetas){
        this.listatarjeta = lstnuevastarjetas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemTarjetaBinding reclyclerBinding;
        public ViewHolder(@NonNull ItemTarjetaBinding itemView) {
            super(itemView.getRoot());
            reclyclerBinding = itemView;
        }
    }
}
