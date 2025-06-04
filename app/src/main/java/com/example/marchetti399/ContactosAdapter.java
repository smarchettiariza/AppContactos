package com.example.marchetti399;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ContactoViewHolder> {

    private List<Contacto> listaOriginal;
    private List<Contacto> listaFiltrada;
    private OnContactoLongClick listener;
    private OnContactoClick clickListener;

    public ContactosAdapter(List<Contacto> listaContactos, OnContactoLongClick longClickListener, OnContactoClick clickListener) {
        this.listaOriginal = new ArrayList<>(listaContactos);
        this.listaFiltrada = new ArrayList<>(listaContactos);
        this.listener = longClickListener;
        this.clickListener = clickListener;
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
        holder.bind(contacto, listener, clickListener);
    }

    @Override
    public int getItemCount() {
        return listaFiltrada.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvTelefono, tvCorreo;
        Button btnEditar, btnEliminar;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }

        public void bind(Contacto contacto, OnContactoLongClick longClickListener, OnContactoClick clickListener) {
            tvNombre.setText(contacto.getNombre());
            tvTelefono.setText("📞 " + contacto.getTelefono());
            tvCorreo.setText("📧 " + contacto.getCorreo());

            btnEliminar.setOnClickListener(v -> longClickListener.onLongClick(contacto));
            btnEditar.setOnClickListener(v -> clickListener.onClick(contacto));
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

    public void actualizarLista(List<Contacto> nuevos) {
        listaOriginal.clear();
        listaOriginal.addAll(nuevos);
        listaFiltrada.clear();
        listaFiltrada.addAll(nuevos);
        notifyDataSetChanged();
    }
}
