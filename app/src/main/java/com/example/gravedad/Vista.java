package com.example.gravedad;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Vista extends View {

    Paint p;
    Paint fondo;
    public ArrayList<Bola> bolas;

    public Vista(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);

        fondo = new Paint();
        fondo.setStyle(Paint.Style.FILL);
        fondo.setColor(Color.BLACK);

        bolas = new ArrayList<>();
        bolas.add(new Bola(100,  new PointF(200, 200)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Canvas c = canvas;

        c.drawRect(0, 0, getWidth(), getHeight(), fondo);
        for(Bola bola : bolas) {
            c.drawCircle(bola.pos.x, bola.pos.y, bola.radio, p);
        }
    }
}
