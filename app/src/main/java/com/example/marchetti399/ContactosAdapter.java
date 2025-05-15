package com.example.marchetti399;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ContactoViewHolder> {

    private List<Contacto> listaOriginal;
    private List<Contacto> listaFiltrada;

    public ContactosAdapter(List<Contacto> listaContactos) {
        this.listaOriginal = new ArrayList<>(listaContactos);
        this.listaFiltrada = new ArrayList<>(listaContactos);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ContactoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto contacto = listaFiltrada.get(position);
        holder.tvNombre.setText(contacto.getNombre());
        holder.tvTelefono.setText("📞 " + contacto.getTelefono());
        holder.tvCorreo.setText("📧 " + contacto.getCorreo());
    }

    @Override
    public int getItemCount() {
        return listaFiltrada.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvTelefono, tvCorreo;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
        }
    }

    public void filtrar(String texto) {
        listaFiltrada.clear();
        if (texto.isEmpty()) {
            listaFiltrada.addAll(listaOriginal);
        } else {
            for (Contacto contacto : listaOriginal) {
                if (contacto.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                    listaFiltrada.add(contacto);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void agregarContacto(Contacto nuevo) {
        listaOriginal.add(nuevo);
        listaFiltrada.add(nuevo);
        notifyDataSetChanged();
    }
}
