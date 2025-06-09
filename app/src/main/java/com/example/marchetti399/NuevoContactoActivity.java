package com.example.marchetti399;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NuevoContactoActivity extends AppCompatActivity {

    EditText etNombre, etTelefono, etCorreo, etDomicilio;
    Spinner spinnerGenero;
    Button btnGuardar;

    boolean modoEdicion = false;
    String nombreOriginal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etCorreo = findViewById(R.id.etCorreo);
        etDomicilio = findViewById(R.id.etDomicilio);
        spinnerGenero = findViewById(R.id.spinnerGenero);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Spinner con opciones
        ArrayAdapter<String> generoAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"Masculino", "Femenino", "Otro"}
        );
        generoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenero.setAdapter(generoAdapter);

        // Si viene para editar
        Intent intent = getIntent();
        if (intent.hasExtra("modo_edicion")) {
            modoEdicion = true;

            nombreOriginal = intent.getStringExtra("nombre_original");
            etNombre.setText(intent.getStringExtra("nombre"));
            etTelefono.setText(intent.getStringExtra("telefono"));
            etCorreo.setText(intent.getStringExtra("correo"));
            etDomicilio.setText(intent.getStringExtra("domicilio"));

            String genero = intent.getStringExtra("genero");
            if (genero != null) {
                int index = generoAdapter.getPosition(genero);
                spinnerGenero.setSelection(index);
            }

            btnGuardar.setText("Guardar cambios");
        }

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String domicilio = etDomicilio.getText().toString().trim();
            String genero = spinnerGenero.getSelectedItem().toString();

            if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || domicilio.isEmpty()) {
                Toast.makeText(this, "Por favor completá todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!telefono.matches("\\d+")) {
                Toast.makeText(this, "El teléfono debe contener solo números", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent resultado = new Intent();
            resultado.putExtra("nombre", nombre);
            resultado.putExtra("telefono", telefono);
            resultado.putExtra("correo", correo);
            resultado.putExtra("domicilio", domicilio);
            resultado.putExtra("genero", genero);

            if (modoEdicion) {
                resultado.putExtra("modo_edicion", true);
                resultado.putExtra("nombre_original", nombreOriginal);
            }

            setResult(RESULT_OK, resultado);
            finish();
        });
    }
}
