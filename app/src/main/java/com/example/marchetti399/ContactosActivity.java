package com.example.marchetti399;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactosActivity extends AppCompatActivity {

    EditText etBuscar;
    RecyclerView recyclerContactos;
    FloatingActionButton fabAgregar;

    ArrayList<Contacto> contactos = new ArrayList<>();
    ContactosAdapter adapter;

    private ActivityResultLauncher<Intent> launcherNuevoContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        etBuscar = findViewById(R.id.etBuscar);
        recyclerContactos = findViewById(R.id.recyclerContactos);
        fabAgregar = findViewById(R.id.fabAgregar);


        contactos.add(new Contacto("Juan Pérez", "123456789", "juan@email.com"));
        contactos.add(new Contacto("María López", "987654321", "maria@email.com"));
        contactos.add(new Contacto("Pedro Gómez", "456789123", "pedro@email.com"));

        adapter = new ContactosAdapter(contactos);
        recyclerContactos.setLayoutManager(new LinearLayoutManager(this));
        recyclerContactos.setAdapter(adapter);


        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filtrar(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });


        launcherNuevoContacto = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String nombre = data.getStringExtra("nombre");
                        String telefono = data.getStringExtra("telefono");
                        String correo = data.getStringExtra("correo");

                        if (nombre != null && !nombre.isEmpty()) {
                            Contacto nuevo = new Contacto(nombre, telefono, correo);
                            adapter.agregarContacto(nuevo);
                            Toast.makeText(this, "Contacto agregado", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        fabAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(ContactosActivity.this, NuevoContactoActivity.class);
            launcherNuevoContacto.launch(intent);
        });
    }
}
