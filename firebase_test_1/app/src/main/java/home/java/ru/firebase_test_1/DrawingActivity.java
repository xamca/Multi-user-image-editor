package home.java.ru.firebase_test_1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class DrawingActivity extends Activity {

    private RelativeLayout rlSurfaceview;
    private ImageButton btn_erase;
    private GridLayout grdColors;

    private MySurfaceView2 mySurfaceView2;

    private ArrayList<View> imbtns_color;
    private View colorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        mySurfaceView2 = new MySurfaceView2(this);

        imbtns_color = new ArrayList<View>();

        init();

        //тест метода смены цвета. TODO передавать цвет в функцию параметром
        btn_erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySurfaceView2.changeColor(1);
            }
        });






    }

    public void init(){

        rlSurfaceview = (RelativeLayout)findViewById(R.id.surfaceLauout);
        btn_erase = (ImageButton) findViewById(R.id.erase_btn);
        grdColors = (GridLayout)findViewById(R.id.gridLayoutColors);

        final int[] sColor = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY,
                Color.GRAY, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.TRANSPARENT,
                Color.WHITE, Color.YELLOW };


        for (int i = 1; i <= 12; i++){

            View v = new View(this);
            grdColors.addView(v);
            v.setMinimumWidth(30);
            v.setMinimumHeight(30);
            //TODO как определить бекграунд, как определить, сколько элементов создано
            v.setTag(grdColors.getChildCount() + 1);

            //TODO избавиться от подобного безумия
            final int c = i-1;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mySurfaceView2.changeColor(sColor[c]);
                }
            });

        }




        rlSurfaceview.addView(mySurfaceView2);

    }

}