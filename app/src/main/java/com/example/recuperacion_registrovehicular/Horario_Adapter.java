package com.example.recuperacion_registrovehicular;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recuperacion_registrovehicular.Clases.Horario;

import java.util.List;

public class Horario_Adapter extends RecyclerView.Adapter<Horario_Adapter.ViewHolderHorario> implements View.OnClickListener{
    List<Horario> lista;
    public View.OnClickListener cliclk;

    public Horario_Adapter(List<Horario> lista) {
        this.lista = lista;
    }
    @Override
    public ViewHolderHorario onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_horario, null);
        view.setOnClickListener(this);
        return new ViewHolderHorario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHorario viewHolderUsuario, int pos) {
        viewHolderUsuario.datoFR.setText(lista.get(pos).getFecha_recogida());
        viewHolderUsuario.datoFD.setText(lista.get(pos).getFecha_devolucion());
        viewHolderUsuario.datodias.setText(lista.get(pos).getDias()+"");
        viewHolderUsuario.nro.setText(lista.get(pos).getNro_registro()+"");
        viewHolderUsuario.datoP.setText(lista.get(pos).getPrecio_total()+"");
        viewHolderUsuario.datoPlaca.setText(lista.get(pos).getPlaca());
        viewHolderUsuario.marca.setText(lista.get(pos).getMarca());
        viewHolderUsuario.modelo.setText(lista.get(pos).getModelo());
        viewHolderUsuario.tipoV.setText(lista.get(pos).getTipo_vehiculo());
        //viewHolderUsuario.datoImagen.setText(lista.get(pos).getImagen()+"");

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClick(View v) {
        if(cliclk != null){
            cliclk.onClick(v);
        }

    }
    public void setOnClick(View.OnClickListener listener){
        this.cliclk = listener;


    }



    public class ViewHolderHorario extends RecyclerView.ViewHolder {
        TextView datoFR;
        TextView datoFD;
        TextView datodias;
        TextView datoP;
        TextView datoPlaca;
        TextView marca;
        TextView modelo;
        TextView tipoV;
        TextView nro;
        public ViewHolderHorario(View itemView) {
            super(itemView);
            datoFR= itemView.findViewById(R.id.lblfRecogida);
            datoFD = itemView.findViewById(R.id.lblfDevolucion);
            datodias = itemView.findViewById(R.id.lblDias);
            datoP = itemView.findViewById(R.id.lblPrecio);
            datoPlaca = itemView.findViewById(R.id.lblplaca);
            marca = itemView.findViewById(R.id.lblMarca);
            modelo = itemView.findViewById(R.id.lblModelo);
            tipoV = itemView.findViewById(R.id.lblTipoVehiculo);
            nro = itemView.findViewById(R.id.lblnro);
            //datoImagen = itemView.findViewById(R.id.lblUsuarioImagen);
        }
    }
}