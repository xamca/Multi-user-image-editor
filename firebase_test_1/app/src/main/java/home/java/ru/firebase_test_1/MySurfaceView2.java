package home.java.ru.firebase_test_1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.FileOutputStream;


class MySurfaceView2 extends SurfaceView {

    Path path;
    SurfaceHolder surfaceHolder;
    Bitmap mBitmap = Bitmap.createBitmap(350, 350, Bitmap.Config.RGB_565);
    Canvas canvas = new Canvas(mBitmap);

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MySurfaceView2(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.WHITE);
        path = new Path();
        mBitmap = Bitmap.createBitmap(350, 350, Bitmap.Config.RGB_565);
        canvas = new Canvas(mBitmap);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            path.moveTo(event.getX(), event.getY());
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            path.lineTo(event.getX(), event.getY());
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            path.lineTo(event.getX(), event.getY());
        }

        if(path != null){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawPath(path, paint);
            canvas.setBitmap(this.mBitmap);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }

        return true;
    }

    public void changeColor(int i){
        path = new Path();
        paint.setColor(i);

    }

    public void surfaceChanged(){
        if (path != null) {
            surfaceHolder = getHolder();
            canvas = surfaceHolder.lockCanvas();
            canvas.drawPath(path, paint);
            canvas.drawBitmap(mBitmap, 0, 0, paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void changeBrushSize(int i){
        path = new Path();
        paint.setStrokeWidth(i);
    }

    public Bitmap getBitmap(){
        return mBitmap;
    }

}