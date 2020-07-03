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
import idat.edu.pe.appmantenimientoroom.TarjetaDialogFragment;
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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarjeta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarjetaAdapter.ViewHolder holder, final int position) {
        final TarjetaEntity objtarjetaentity = listatarjeta.get(position);
        holder.tvtitulo.setText(objtarjetaentity.getTitulo());
        holder.tvcontenido.setText(objtarjetaentity.getContenido());
        if(objtarjetaentity.isImportante()){
            holder.ivimportante.setImageResource(R.drawable.ic_star_black);
        }else {
            holder.ivimportante.setImageResource(R.drawable.ic_star_border_black);
        }
        holder.ivdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.deleteTarjeta(objtarjetaentity.getId());
            }
        });

        switch (objtarjetaentity.getColor()){
            case "amarillo" : holder.contenedor.setCardBackgroundColor(Color.YELLOW); break;
            case "rojo" : holder.contenedor.setCardBackgroundColor(Color.RED); break;
            case "verde" : holder.contenedor.setCardBackgroundColor(Color.GREEN); break;

        }

        holder.contenedor.setOnClickListener(new View.OnClickListener() {
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
        public final TextView tvtitulo;
        public final TextView tvcontenido;
        public final ImageView ivimportante, ivdelete;
        public final CardView contenedor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitulo = itemView.findViewById(R.id.tvtitulo);
            tvcontenido =  itemView.findViewById(R.id.tvcontenido);
            ivimportante = itemView.findViewById(R.id.ivimportante);
            ivdelete = itemView.findViewById(R.id.ivdelete);
            contenedor = itemView.findViewById(R.id.contenedor);
        }
    }
}
