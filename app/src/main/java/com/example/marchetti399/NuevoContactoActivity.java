package com.example.marchetti399;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NuevoContactoActivity extends AppCompatActivity {

    EditText etNombre, etTelefono, etCorreo;
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
        btnGuardar = findViewById(R.id.btnGuardar);

        Intent intent = getIntent();
        if (intent.hasExtra("modo_edicion")) {
            modoEdicion = true;

            nombreOriginal = intent.getStringExtra("nombre_original");
            String nombre = intent.getStringExtra("nombre");
            String telefono = intent.getStringExtra("telefono");
            String correo = intent.getStringExtra("correo");

            etNombre.setText(nombre);
            etTelefono.setText(telefono);
            etCorreo.setText(correo);
            btnGuardar.setText("Guardar cambios");
        }

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();

            if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                Intent resultado = new Intent();
                resultado.putExtra("nombre", nombre);
                resultado.putExtra("telefono", telefono);
                resultado.putExtra("correo", correo);

                if (modoEdicion) {
                    resultado.putExtra("modo_edicion", true);
                    resultado.putExtra("nombre_original", nombreOriginal);
                }

                setResult(RESULT_OK, resultado);
                finish();
            }
        });
    }
}
