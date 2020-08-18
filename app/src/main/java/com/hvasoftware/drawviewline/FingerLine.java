package com.hvasoftware.drawviewline;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class FingerLine extends View {
    private final Paint mPaint;
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    public FingerLine(Context context) {
        this(context, null);
    }

    public FingerLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startX, startY, endX, endY, mPaint);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                // Set the end to prevent initial jump (like on the demo recording)
                endX = event.getX();
                endY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                endY = event.getY();
                invalidate();
                break; }
        return true;
    }
}
