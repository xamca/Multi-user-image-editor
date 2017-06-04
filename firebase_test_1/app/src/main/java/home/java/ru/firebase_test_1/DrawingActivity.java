package home.java.ru.firebase_test_1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import java.util.ArrayList;

public class DrawingActivity extends Activity implements OnClickListener {

    final int[] sColor = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY,
            Color.GRAY, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED,
            Color.WHITE, Color.YELLOW };

    private RelativeLayout mRlSurfaceview;
    private GridLayout mGrdColors;
    private MySurfaceView2 mySurfaceView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        mySurfaceView2 = new MySurfaceView2(this);

        init();

    }

    public void init(){

        mRlSurfaceview = (RelativeLayout)findViewById(R.id.surfaceLauout);
        mRlSurfaceview.addView(mySurfaceView2);

        mGrdColors = (GridLayout)findViewById(R.id.gridLayoutColors);
        mGrdColors.setColumnCount(4);
        mGrdColors.setRowCount(3);

        ArrayList<Button> viewArrayList = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            Button v = new Button(this);

            v.setBackgroundColor(sColor[i]);
            v.setHeight(30);
            v.setWidth(30);
            v.setTag(i);
            v.setOnClickListener(this);
            viewArrayList.add(v);
        }

        for (Button v: viewArrayList ){
            mGrdColors.addView(v);
        }
    }
    @Override
    public void onClick(View v) {
        int mTemp = (int)v.getTag();
        mySurfaceView2.changeColor(sColor[mTemp]);
    }

}