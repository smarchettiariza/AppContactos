package com.example.marchetti399;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsuario, etPassword;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        boolean sesionIniciada = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
                .getBoolean("isLoggedIn", false);

        if (sesionIniciada) {
            startActivity(new Intent(LoginActivity.this, ContactosActivity.class));
            finish();
            return;
        }


        setContentView(R.layout.activity_login);
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString();
            String password = etPassword.getText().toString();

            // Validación simple
            if(usuario.equals("admin") && password.equals("1234")){
                getSharedPreferences("MisPreferencias", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isLoggedIn", true)
                        .apply();
                Intent intent = new Intent(LoginActivity.this, ContactosActivity.class);

                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
