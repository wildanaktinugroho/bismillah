package com.example.asuspc.bismillah;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by AsusPC on 03/04/2018.
 */

public class LihatGambar extends View{

    Context context;
    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private float aA, bB;
    private static final float TOLERANCE = 5;

    public LihatGambar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        mPath = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath, mPaint);
    }

    private void onStartTouch (float a, float b){
        mPath.moveTo(a, b);
        aA = a;
        bB = b;
    }

    private void moveTouch (float a, float b){
        float da = Math.abs(a - aA);
        float db = Math.abs(b - bB);
        if(da >= TOLERANCE || db >= TOLERANCE){
            mPath.quadTo(aA, bB,(a + aA) / 2, (b + bB) / 2);
            aA = a;
            bB = b;
        }
    }

    public void hapusGambar (){
        mPath.reset();
        invalidate();
    }

    private void upTouch(){
        mPath.lineTo(aA, bB);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float a = event.getX();
        float b = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                onStartTouch(a, b);
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                moveTouch(a, b);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }
}
