package home.java.ru.firebase_test_1;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

class DrawThread extends Thread{

    private SurfaceHolder mSurfaceHolder;
    private boolean mRunFlag = false;
    private MySurfaceView mySurfaceView;

    public DrawThread(SurfaceHolder surfaceHolder, MySurfaceView surfaceView){
        mSurfaceHolder = surfaceHolder;
        mySurfaceView = surfaceView;

    }

    public void setRunning(boolean run) {
        mRunFlag = run;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (mRunFlag) {
            canvas = null;
            try {
                canvas = mSurfaceHolder.lockCanvas(null);
                synchronized (mSurfaceHolder) {
                    mySurfaceView.onDraw(canvas);
                }
            }
            finally {
                if (canvas != null) {
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}