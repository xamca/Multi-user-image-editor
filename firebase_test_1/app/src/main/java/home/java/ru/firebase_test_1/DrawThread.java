package home.java.ru.firebase_test_1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.view.SurfaceHolder;

class DrawThread extends Thread{
    private boolean runFlag = false;
    private SurfaceHolder surfaceHolder;
    private Bitmap picture;
    private Matrix matrix;
    private long prevTime;

    public DrawThread(SurfaceHolder surfaceHolder, Resources resources){
        this.surfaceHolder = surfaceHolder;

        picture = BitmapFactory.decodeResource(resources, R.drawable.exampledrawable);

        matrix = new Matrix();
        matrix.postScale(3.0f, 3.0f);
        matrix.postTranslate(100.0f, 100.0f);

        prevTime = System.currentTimeMillis();
    }

    public void setRunning(boolean run) {
        runFlag = run;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (runFlag) {
            long now = System.currentTimeMillis();
            long elapsedTime = now - prevTime;
            if (elapsedTime > 30){
                prevTime = now;
                matrix.preRotate(2.0f, picture.getWidth() / 2, picture.getHeight() / 2);
            }
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    canvas.drawColor(Color.BLACK);
                    canvas.drawBitmap(picture, matrix, null);
                }
            }
            finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}