package home.java.ru.firebase_test_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private TextView tvUserName;
    private Button btnLogOut;
    private Button btnStartPainting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser curUser = firebaseAuth.getCurrentUser();


        init();

        tvUserName.setText("Welcome, " + curUser.getEmail());

        btnLogOut.setOnClickListener(this);

        btnStartPainting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DrawingActivity.class);
                startActivity(intent);
            }
        });


    }

    public void init(){
        btnLogOut = (Button)findViewById(R.id.btn_log_out);
        tvUserName = (TextView)findViewById(R.id.tv_signed_in);
        btnStartPainting = (Button)findViewById(R.id.btn_start_painting);

    }

    @Override
    public void onClick(View view) {
        if (view == btnLogOut){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}
