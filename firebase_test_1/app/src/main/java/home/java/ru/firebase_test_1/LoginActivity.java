package home.java.ru.firebase_test_1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnRegistration;
    private Button mBtnSignIn;
    private EditText mEtEmail;
    private EditText mEtPassword;

    private ProgressDialog mProgressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }


    }

    public void init(){

        firebaseAuth = FirebaseAuth.getInstance();

        mProgressDialog = new ProgressDialog(this);

        mBtnRegistration = (Button)findViewById(R.id.btn_registration);
        mBtnSignIn = (Button)findViewById(R.id.btn_sign_in);
        mEtEmail = (EditText) findViewById(R.id.et_email);
        mEtPassword = (EditText) findViewById(R.id.et_password);

        mBtnRegistration.setOnClickListener(this);
        mBtnSignIn.setOnClickListener(this);


    }

    public void registerUser(){
        String email = mEtEmail.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();

        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();


        }

        mProgressDialog.setMessage("Registering user...");
        mProgressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Registered unsuccessfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void userSighIn(){
        String email = mEtEmail.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();

        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();


        }

        mProgressDialog.setMessage("Registering user...");
        mProgressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Signed in unsuccessfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


    @Override
    public void onClick(View view){
        if (view == mBtnRegistration){
            registerUser();
        }
        if (view == mBtnSignIn){
            userSighIn();
        }

    }
}
