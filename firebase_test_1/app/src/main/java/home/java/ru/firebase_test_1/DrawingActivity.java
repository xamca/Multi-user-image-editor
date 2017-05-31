package home.java.ru.firebase_test_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class DrawingActivity extends Activity {

    private RelativeLayout rlSurfaceview;
    private ImageButton btn_erase;

    private MySurfaceView2 mySurfaceView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        mySurfaceView2 = new MySurfaceView2(this);

        init();

        //тест метода смены цвета. TODO передавать цвет в функцию параметром
        btn_erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySurfaceView2.changeToBlack();
            }
        });

    }

    public void init(){

        rlSurfaceview = (RelativeLayout)findViewById(R.id.surfaceLauout);
        btn_erase = (ImageButton) findViewById(R.id.erase_btn);

        rlSurfaceview.addView(mySurfaceView2);

    }
}