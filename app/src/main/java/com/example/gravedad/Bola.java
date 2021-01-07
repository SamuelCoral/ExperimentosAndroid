package com.example.gravedad;

import android.graphics.PointF;

public class Bola {

    public int radio;

    public PointF pos;
    public PointF vel;
    public PointF acel;

    public Bola(int radio, PointF pos) {
        this.radio = radio;
        this.pos = pos;
        vel = new PointF();
        acel = new PointF();
    }
}
