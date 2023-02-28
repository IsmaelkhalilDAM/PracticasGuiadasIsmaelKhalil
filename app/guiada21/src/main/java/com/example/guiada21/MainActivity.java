package com.example.guiada21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonVV).setOnClickListener(this::buttonClick);
        findViewById(R.id.buttonMPV).setOnClickListener(this::buttonClick);
        findViewById(R.id.buttonMRA).setOnClickListener(this::buttonClick);
        findViewById(R.id.buttonGV).setOnClickListener(this::buttonClick);
        comprobarSolicitarPermisos(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
        );
    }
    public void comprobarSolicitarPermisos(String... permisos) {
        List<String> lista = new ArrayList<>();
        for (String permiso: permisos)
            if (checkSelfPermission(permiso) != PackageManager.PERMISSION_GRANTED) {
                lista.add(permiso);
            }
        if (!lista.isEmpty())
            requestPermissions(lista.toArray(new String[lista.size()]), 1);
    }
    private void buttonClick(View v) {
        Class<?> clase = null;
        switch (v.getId()) {
            case R.id.buttonVV:
                clase = VideoViewActivity.class;
                break;
            case R.id.buttonMP:
                clase = MediaPlayerVideoActivity.class;
                break;
            case R.id.buttonMRA:
                clase = MediaRecorderAudioActivity.class;
                break;
            case R.id.buttonMRV:
                clase = GrabarVideoActivity.class;
        }
        startActivity(new Intent(this, clase));
    }
}
