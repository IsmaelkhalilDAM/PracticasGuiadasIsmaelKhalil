package com.example.guiada19;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorFragment extends Fragment implements SensorEventListener {
    private static final String PARAM_POSICION_SENSOR = "posicion_sensor";
    private TextView sensorInfo;
    private Sensor sensor;
    private SensorManager manager;
    private ActionBar actionBar;

    public static SensorFragment newInstance(int posicionSensor) {
        SensorFragment fragment = new SensorFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_POSICION_SENSOR, posicionSensor);
        fragment.setArguments(args);
        return fragment;
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SensorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorFragment newInstance(String param1, String param2) {
        SensorFragment fragment = new SensorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            MainActivity activity = (MainActivity) getActivity();
            List<Sensor> sensores = activity.getSensores();
            int posicion = getArguments().getInt(PARAM_POSICION_SENSOR);
            sensor = sensores.get(posicion);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sensor, container, false);
        sensorInfo = v.findViewById(R.id.textViewInfoSensor);
        MainActivity activity = (MainActivity) getActivity();
        actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        manager = activity.getSensorManager();
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        return v;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorInfo.setText(sensor.getName());
        sensorInfo.append("\n\n");
        for (int i=0; i<event.values.length; i++) {
            sensorInfo.append(String.valueOf(event.values[i]));
            sensorInfo.append("\n");
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        actionBar.setDisplayHomeAsUpEnabled(false);
        manager.unregisterListener(this);
    }

}