package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private TextView mTextView;
    private Button signInBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEmail = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        signInBtn = findViewById(R.id.loginBtn);
        mTextView = findViewById(R.id.textView2);
        mAuth = FirebaseAuth.getInstance();


        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
            }
        });


        signInBtn.setOnClickListener( (v)-> loginUser());
    }
    public void loginUser(){
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!password.isEmpty()){
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignInActivity.this,"Login success",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this, SignOutActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignInActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    }
                });
            }
            else{
                mPassword.setError("Ecrire votre mot de passe !");
            }
            }else if(email.isEmpty()){
            mEmail.setError("Ecrire Votre email !");
            }
            else{
            mEmail.setError("Email incorrect !");
        }
    }
}