package idat.edu.pe.appmantenimientoroom.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import idat.edu.pe.appmantenimientoroom.R;
import idat.edu.pe.appmantenimientoroom.databinding.ActivityMainBinding;
import idat.edu.pe.appmantenimientoroom.db.entity.TarjetaEntity;
import idat.edu.pe.appmantenimientoroom.viewmodel.TarjetaDialogViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private TarjetaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rvdatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new TarjetaAdapter(MainActivity.this);
        binding.rvdatos.setAdapter(adapter);
        binding.fabagregar.setOnClickListener(this);
        mostrarTarjetas();
    }
    private void mostrarTarjetas() {
        TarjetaDialogViewModel tarjetaDialogViewModel= new ViewModelProvider(this)
                .get(TarjetaDialogViewModel.class);
        tarjetaDialogViewModel.getAllTarjetas().observe(this, new Observer<List<TarjetaEntity>>() {
            @Override
            public void onChanged(List<TarjetaEntity> tarjetaEntities) {
                adapter.setListTarjetas(tarjetaEntities);
            }
        });
    }
    private void mostrarDialogNuevaNota() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        TarjetaDialogFragment dialogFragment = new TarjetaDialogFragment();
        dialogFragment.show(fragmentManager, "NuevaTarjetaDialogFragment");

    }

    @Override
    public void onClick(View v) {
        mostrarDialogNuevaNota();
    }
}
