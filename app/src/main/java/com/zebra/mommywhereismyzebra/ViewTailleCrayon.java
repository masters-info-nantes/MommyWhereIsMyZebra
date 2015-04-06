package com.zebra.mommywhereismyzebra;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

/**
 * Permet d'afficher un trait pour montrer a l'utilisateur de quelle taille est son crayon
 */
public class ViewTailleCrayon extends View{

    private Bitmap  mBitmap;
    private Canvas  mCanvas;
    private Path    mPath;
    private Paint   mBitmapPaint;
    Context context;

    private Paint       mPaint;


    public ViewTailleCrayon(Context c){
        super(c);
        context=c;

        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
    }

    public ViewTailleCrayon(Context c, AttributeSet attrs){
        super (c, attrs);
        context=c;
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
    }
    public ViewTailleCrayon(Context c, AttributeSet attrs, int defStyle){
        super (c, attrs, defStyle);
        context=c;
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        drawLine();

    }
    @Override

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, mPaint);
        mCanvas.drawPath(mPath,  mPaint);
    }



    public void setColor(int color) {
        mPaint.setXfermode(null);
        mPaint.setColor(color);
    }

    public void changeStrokeWidth(int taille){
        mPaint.setStrokeWidth(taille);
    }

    public void drawLine() {
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        mCanvas.drawLine(this.getWidth()/2, mPaint.getStrokeWidth()/2, this.getWidth()/2 , this.getHeight()- mPaint.getStrokeWidth()/2, mPaint);
        invalidate();
    }
}
