package home.java.ru.firebase_test_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

import java.util.Random;


public class MySurfaceView extends SurfaceView implements
        SurfaceHolder.Callback {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private DrawThread thread;
    private float initX, initY, x, y;
    private boolean drawing = false;
    Path path;
    private  boolean mTouchFlag = false;

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        // super.onDraw(canvas);
        path = new Path();
        if (drawing) {
            path.moveTo(initX, initY);
            path.lineTo(x, y);
            path.moveTo(x, y);

            canvas.drawPath(path, paint);
        }
        path = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        // return super.onTouchEvent(event);
        int action = event.getAction();
        if (action == MotionEvent.ACTION_MOVE) {
            x = event.getX();
            y = event.getY();
        } else if (action == MotionEvent.ACTION_DOWN) {
            initX = event.getX();
            initY = event.getY();
            drawing = true;
        } else if (action == MotionEvent.ACTION_UP) {
            drawing = false;
        }

        return true;
    }

    public MySurfaceView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init();
    }

    private void init() {
        getHolder().addCallback(this);

        setFocusable(true); // make sure we get key events

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.WHITE);
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
                               int arg3) {
        // TODO Auto-generated method stub
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        thread = new DrawThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public void changeColor(int i){
        paint.setColor(i);

    }

    public void changeBrushSize(int i){
        paint.setStrokeWidth(i);
    }

}