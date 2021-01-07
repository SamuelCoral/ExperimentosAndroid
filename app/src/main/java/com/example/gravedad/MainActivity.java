package com.example.gravedad;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PointF;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Vista vista;
    TimerSimulador tiempo;
    long tiempoTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vista = findViewById(R.id.view);

        tiempoTouch = System.currentTimeMillis();
        tiempo = new TimerSimulador(vista);
        tiempo.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                long nuevoTiempoTouch = System.currentTimeMillis();
                tiempo.factorAcel = (nuevoTiempoTouch - tiempoTouch) < 200 ?
                    10 * TimerSimulador.FACTOR_ACEL_BASE : TimerSimulador.FACTOR_ACEL_BASE;
                tiempoTouch = nuevoTiempoTouch;
            case MotionEvent.ACTION_MOVE:
                tiempo.centro = new PointF(event.getX(), event.getY());

                break;

            case MotionEvent.ACTION_UP:
                tiempo.centro = null;
        }

        return super.onTouchEvent(event);
    }
}