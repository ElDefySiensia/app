package com.devst.app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;


public class HomeActivity extends AppCompatActivity {

    //Encapsulamiento
    private String emailUsuario = "";
    private TextView tvBienvenida;

    //Activity para capturar resultados
    private final ActivityResultLauncher<Intent> editarPerfilLauncher = 
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getResultCode() == RESULT_OK && result.getData() != null){
                    String nombre = result.getData().getStringExtra("nombre_editado");
                    if(nombre != null){
                        tvBienvenida.setText("Hola, " + nombre);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        //Referencias
        tvBienvenida = findViewById(R.id.tvBienvenida);
        Button btnIrPerfil = findViewById(R.id.btnIrPerfil);
        Button btnAbrirWeb = findViewById(R.id.btnAbrirWeb);
        Button btnEnviarCorreo = findViewById(R.id.btnEnviarCorreo);
        Button btnCompartir = findViewById(R.id.btnCompartir);

        //Recibir datos desde el login
        emailUsuario = getIntent().getStringExtra("email_usuario");
        if(emailUsuario == null) emailUsuario = "";
        tvBienvenida.setText("Bienvenida: " + emailUsuario);

        //Evento explicito iniciar vista perfil
        btnIrPerfil.setOnClickListener(View -> {
            Intent perfil = new Intent(HomeActivity.this, PerfilActivity.class);
            perfil.putExtra("email_usuario", emailUsuario);
            editarPerfilLauncher.launch(perfil);
        });

        //Eventos implicitos web
        btnAbrirWeb.setOnClickListener(View -> {
            Uri url = Uri.parse("https://github.com/ElDefySiensia/ProjectKnowShare");
            Intent ViewWeb = new Intent(Intent.ACTION_VIEW, url);
            startActivity(ViewWeb);
        });

        //Evento implicito
        btnEnviarCorreo.setOnClickListener(View -> {
            Intent correo = new Intent(Intent.ACTION_SENDTO);
            correo.setData(Uri.parse("mailto:")); //Solo para correo
            correo.putExtra(Intent.EXTRA_EMAIL, new String[]{emailUsuario});
            correo.putExtra(Intent.EXTRA_SUBJECT, "Prueba de correo");
            correo.putExtra(Intent.EXTRA_TEXT, "Hola Mundo desde el boton correo");
            startActivity(Intent.createChooser(correo, "Enviar correo a: "));
        });

        //Evento implicito compartir
        btnCompartir.setOnClickListener(View -> {
            Intent compartir = new Intent(Intent.ACTION_SEND);
            compartir.setType("text/plain");
            compartir.putExtra(Intent.EXTRA_TEXT, "Hola Mundo desde android");
            startActivity(Intent.createChooser(compartir, "Compartiendo: "));
        });
    }
}