package home.java.ru.firebase_test_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;

public class DrawingActivity extends Activity implements OnClickListener {

    final int[] sColor = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY,
            Color.GRAY, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED,
            Color.WHITE, Color.YELLOW };

    private RelativeLayout mRlSurfaceview;
    private GridLayout mGrdColors;
    private MySurfaceView2 mySurfaceView2;
    private ImageButton mBtnBrushSize;
    private ImageButton mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        mySurfaceView2 = new MySurfaceView2(this);

        init();

        mBtnBrushSize.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);


    }

    public void init(){
        mBtnBrushSize = (ImageButton) findViewById(R.id.brush_btn);
        mBtnSave = (ImageButton) findViewById(R.id.save_btn);
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
        if (v.getTag() != null) {
            int mTemp = (int) v.getTag();
            mySurfaceView2.changeColor(sColor[mTemp]);
        } else
        {
            switch (v.getId()) {
                case R.id.brush_btn: {
                    brushSizeAlert();
                } break;
                case R.id.save_btn: Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show(); break;
            }
        }
    }

    public void brushSizeAlert(){
        final CharSequence[] items = {
                "small", "medium", "big"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Make your selection");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // пока что по-простому - три варианта без картинок
                mySurfaceView2.changeBrushSize(item*10);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}