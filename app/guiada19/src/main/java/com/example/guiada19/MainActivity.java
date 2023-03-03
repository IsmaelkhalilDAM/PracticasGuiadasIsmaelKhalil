package com.example.guiada19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Sensor> sensores;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensores = manager.getSensorList(Sensor.TYPE_ALL);
    }

    public List<Sensor> getSensores() {
        return Collections.unmodifiableList(sensores);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            FragmentManager manager = getSupportFragmentManager();
            if (manager.getBackStackEntryCount() > 0)
                manager.popBackStack();
            return true;
        }
        return false;
    }

    public SensorManager getSensorManager() {
        return null;
    }
}