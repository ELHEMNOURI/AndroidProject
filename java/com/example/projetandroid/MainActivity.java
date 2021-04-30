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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private TextView mTextView;
    private Button signUpBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.emailZone);
        mPassword = findViewById(R.id.passwordZone);
        mTextView = findViewById(R.id.text1);
        signUpBtn = findViewById(R.id.signUp);

        mAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignInActivity.class));
            }
        });
    }
    private void addUser(){
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!password.isEmpty()){
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(MainActivity.this, "Compte Ajouté avec success !", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Erreur !!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
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