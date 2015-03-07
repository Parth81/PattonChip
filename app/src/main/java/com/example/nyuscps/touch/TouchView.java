package com.example.nyuscps.touch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by nyuscps on 3/7/15.
 */
public class TouchView extends View {
    private PointF p = new PointF();	//holds 2 floats
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean first = true;

    public TouchView(Context context) {
        super(context);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(135f);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //Put center of circle where finger touched.
                        p.set(event.getX(), event.getY());
                        invalidate();    //call onDraw method of TouchView
                        return true;

                    default:
                        return false;
                }
            }
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int width = getWidth();
        final int height = getHeight();
        float radius = .3f * Math.min(width/2, height/2);

        if (first)
        {
            p.set(width/2, height/2);
            first = false;

        }

        canvas.drawColor(Color.WHITE);	//background
        //canvas.drawCircle(p.x, p.y, radius, paint);

        // Draws patton and then a circle with a thick white line that covers
        // part of the image.
        Resources resources = getResources();
        Bitmap bitMap = BitmapFactory.decodeResource(resources, R.mipmap.patton);
        int w = bitMap.getWidth();
        int h = bitMap.getHeight();
        //coordinates of upper left corner of BitMap, paint is null
        canvas.drawBitmap(bitMap, p.x - w/2, p.y - h/2, null);
        canvas.drawCircle(p.x, p.y, radius, paint);
    }
}
