package com.example.marchetti399;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactosActivity extends AppCompatActivity {

    EditText etBuscar;
    RecyclerView recyclerContactos;
    FloatingActionButton fabAgregar;

    ContactoDbHelper dbHelper;
    ArrayList<Contacto> contactos;
    ContactosAdapter adapter;

    private ActivityResultLauncher<Intent> launcherNuevoContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Vistas
        etBuscar = findViewById(R.id.etBuscar);
        recyclerContactos = findViewById(R.id.recyclerContactos);
        fabAgregar = findViewById(R.id.fabAgregar);

        // DB y contactos
        dbHelper = new ContactoDbHelper(this);
        contactos = dbHelper.obtenerContactos();

// Contactos por defecto (solo si está vacía la tabla)
        if (contactos.isEmpty()) {
            dbHelper.insertarContacto(new Contacto("Juan Pérez", "123456789", "juan@email.com"));
            dbHelper.insertarContacto(new Contacto("María López", "987654321", "maria@email.com"));
            contactos = dbHelper.obtenerContactos(); // recargar lista
        }
        // Adaptador con listener de eliminación y edición (en este ejemplo solo eliminación)
        adapter = new ContactosAdapter(contactos,
                contacto -> {
                    new androidx.appcompat.app.AlertDialog.Builder(this)
                            .setTitle("Eliminar contacto")
                            .setMessage("¿Seguro que querés eliminar a " + contacto.getNombre() + "?")
                            .setPositiveButton("Sí", (dialog, which) -> {
                                dbHelper.eliminarContacto(contacto.getNombre());
                                contactos = dbHelper.obtenerContactos();
                                adapter.actualizarLista(contactos);
                                Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
                            })
                            .setNegativeButton("Cancelar", null)
                            .show();
                },
                contacto -> {
                    // Listener de edición (si estás usando también edición)
                    Intent intent = new Intent(this, NuevoContactoActivity.class);
                    intent.putExtra("modo_edicion", true);
                    intent.putExtra("nombre_original", contacto.getNombre());
                    intent.putExtra("nombre", contacto.getNombre());
                    intent.putExtra("telefono", contacto.getTelefono());
                    intent.putExtra("correo", contacto.getCorreo());
                    launcherNuevoContacto.launch(intent);
                }
        );

        recyclerContactos.setLayoutManager(new LinearLayoutManager(this));
        recyclerContactos.setAdapter(adapter);

        // Buscador
        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filtrar(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Agregar contacto
        launcherNuevoContacto = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String nombre = result.getData().getStringExtra("nombre");
                        String telefono = result.getData().getStringExtra("telefono");
                        String correo = result.getData().getStringExtra("correo");

                        if (nombre != null && !nombre.isEmpty()) {
                            if (result.getData().hasExtra("modo_edicion")) {
                                String nombreOriginal = result.getData().getStringExtra("nombre_original");
                                Contacto actualizado = new Contacto(nombre, telefono, correo);
                                dbHelper.actualizarContacto(nombreOriginal, actualizado);
                                Toast.makeText(this, "Contacto actualizado", Toast.LENGTH_SHORT).show();
                            } else {
                                Contacto nuevo = new Contacto(nombre, telefono, correo);
                                dbHelper.insertarContacto(nuevo);
                                Toast.makeText(this, "Contacto agregado", Toast.LENGTH_SHORT).show();
                            }

                            contactos = dbHelper.obtenerContactos();
                            adapter.actualizarLista(contactos);
                        }
                    }
                }
        );

        fabAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(ContactosActivity.this, NuevoContactoActivity.class);
            launcherNuevoContacto.launch(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            getSharedPreferences("MisPreferencias", MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
