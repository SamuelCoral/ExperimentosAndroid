package com.example.gravedad;

import android.graphics.PointF;

import java.util.TimerTask;

public class TimerSimulador extends Thread {

    long tiempo;
    long tiempoDibujar;
    public static final int FPS = 30;
    public static final float PERIODO_DIBUJAR = 1000f / FPS;
    PointF centro;
    Vista vista;
    public static final float FACTOR_ACEL_BASE = 500f;
    public float factorAcel;

    public TimerSimulador(Vista vista) {
        this.vista = vista;
        tiempo = System.currentTimeMillis();
        tiempoDibujar = 0;
        factorAcel = FACTOR_ACEL_BASE;
    }

    @Override
    public void run() {
        while(true) {

            long deltat = System.currentTimeMillis() - tiempo;
            float dt = deltat / 1000f;

            if(vista.bolas != null) for(Bola bola : vista.bolas) {

                if(centro != null) {
                    PointF dcentro = new PointF(
                        (centro.x - bola.pos.x) / 1000f,
                        (centro.y - bola.pos.y) / 1000f
                    );
                    float distancia = (float)Math.hypot(dcentro.x, dcentro.y);

                    if(distancia < 0.1) {
                        bola.acel = new PointF();
                        bola.vel = new PointF();
                    } else {
                        bola.acel = new PointF(
                            factorAcel * dcentro.x / (distancia * distancia * distancia),
                            factorAcel * dcentro.y / (distancia * distancia * distancia)
                        );
                    }
                }

                bola.vel = new PointF(
                    bola.vel.x + dt * bola.acel.x,
                    bola.vel.y + dt * bola.acel.y
                );

                bola.pos = new PointF(
                    bola.pos.x + dt * bola.vel.x,
                    bola.pos.y + dt * bola.vel.y
                );
            }

            tiempoDibujar += deltat;
            if(tiempoDibujar >= PERIODO_DIBUJAR) {
                tiempoDibujar %= PERIODO_DIBUJAR;
                vista.invalidate();
            }

            tiempo += deltat;
        }
    }
}
