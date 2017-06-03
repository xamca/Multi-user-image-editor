package home.java.ru.firebase_test_1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class DrawingActivity extends Activity {

    final int[] sColor = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY,
            Color.GRAY, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.TRANSPARENT,
            Color.WHITE, Color.YELLOW };

    private RelativeLayout mRlSurfaceview;
    private ImageButton mBtnErase;
    private GridLayout mGrdColors;

    private MySurfaceView2 mySurfaceView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        mySurfaceView2 = new MySurfaceView2(this);

        init();

        //тест метода смены цвета. TODO передавать цвет в функцию параметром
        mBtnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySurfaceView2.changeColor(1);
            }
        });






    }

    public void init(){

        mRlSurfaceview = (RelativeLayout)findViewById(R.id.surfaceLauout);
        mBtnErase = (ImageButton) findViewById(R.id.erase_btn);
        mGrdColors = (GridLayout)findViewById(R.id.gridLayoutColors);

        mRlSurfaceview.addView(mySurfaceView2);

        mGrdColors.setColumnCount(6);
        mGrdColors.setRowCount(2);

        GridLayout.LayoutParams grdColorParams = new GridLayout.LayoutParams();
        GridLayout.LayoutParams grdColorParams2 = new GridLayout.LayoutParams();
        grdColorParams.height = 50;
        grdColorParams.width = 50;
        grdColorParams.setGravity(Gravity.LEFT);
        grdColorParams2.height = 50;
        grdColorParams2.width = 50;
        grdColorParams2.setGravity(Gravity.LEFT);

        grdColorParams.leftMargin = 50;

        // первая вью
        for (int i = 1; i <= 1; i++){
            View v = new View(this);

            v.setBackgroundColor(sColor[i-1]);

            mGrdColors.addView(v, grdColorParams);

            //TODO как определить бекграунд, как определить, сколько элементов создано
            v.setTag(mGrdColors.getChildCount() + 1);
            //TODO избавиться от подобного безумия
            final int c = i-1;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mySurfaceView2.changeColor(sColor[c]);
                }
            });

        }

        // второе вью
        grdColorParams2.leftMargin = 100;
        View v2 = new View(this);
        v2.setBackgroundColor(sColor[5]);
        mGrdColors.addView(v2, grdColorParams2);
        v2.setTag(mGrdColors.getChildCount() + 1);

        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySurfaceView2.changeColor(sColor[5]);
            }
        });



    }



}