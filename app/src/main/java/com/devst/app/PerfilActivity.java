package com.devst.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        Button btnAbrirGit = findViewById(R.id.btn_acercade);

        btnAbrirGit.setOnClickListener(View -> {
            Uri url = Uri.parse("https://github.com/ElDefySiensia");
            Intent ViewWeb = new Intent(Intent.ACTION_VIEW, url);
            startActivity(ViewWeb);
        });
    }
}