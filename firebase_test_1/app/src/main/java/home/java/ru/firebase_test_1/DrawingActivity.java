package home.java.ru.firebase_test_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

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
    private ImageButton mBtnLoad;
    private DatabaseReference mDatabaseReference;
    private Bitmap bitmap = null;
    BitmapClass mBitmapExample;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        mySurfaceView2 = new MySurfaceView2(this);
        mBitmapExample = new BitmapClass();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();


        init();

        mBtnBrushSize.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnLoad.setOnClickListener(this);


    }

    public void init(){
        mBtnBrushSize = (ImageButton) findViewById(R.id.brush_btn);
        mBtnSave = (ImageButton) findViewById(R.id.save_btn);
        mBtnLoad = (ImageButton) findViewById(R.id.load_btn);
        mRlSurfaceview = (RelativeLayout)findViewById(R.id.surfaceLauout);
        mRlSurfaceview.addView(mySurfaceView2);

        mGrdColors = (GridLayout)findViewById(R.id.gridLayoutColors);
        mGrdColors.setColumnCount(4);
        mGrdColors.setRowCount(3);

        ArrayList<Button> viewArrayList = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            Button v = new Button(this);

            v.setBackgroundColor(sColor[i]);
            v.setHeight(25);
            v.setWidth(25);
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
                case R.id.save_btn: {
                    saveToFirebase();
                    Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show();
                } break;
                case R.id.load_btn: {
                    loadToFirebase();
                    Toast.makeText(this, "Image loaded", Toast.LENGTH_SHORT).show();

                }
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
    public void saveToFirebase(){
        bitmap = Bitmap.createBitmap(mySurfaceView2.getBitmap());
        bitmap = mySurfaceView2.getBitmap();
        mBitmapExample = new BitmapClass(bitmap);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference.child(user.getUid()).setValue(mBitmapExample);
    }

    public void loadToFirebase(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<BitmapClass> t = new GenericTypeIndicator<BitmapClass>() {};
                BitmapClass bitmapClass = dataSnapshot.child("mBitmap").getValue(BitmapClass.class);
                mBitmapExample = bitmapClass;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        bitmap = Bitmap.createBitmap(mBitmapExample.mBitmap);
    }

}