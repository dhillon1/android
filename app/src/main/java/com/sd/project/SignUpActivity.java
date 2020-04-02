package com.sd.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText signUpEmail, signUpPassword;
    private Button signUp, login;
    private String email, password;
    private FirebaseAuth signUpAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPassword = findViewById(R.id.signUpPassword);
        login = findViewById(R.id.signUpLogin);
        signUp = findViewById(R.id.signUpSignUp);
        signUpAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = signUpEmail.getText().toString().trim();
                password = signUpPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    signUpEmail.setError("Enter Email Id");
                    signUpEmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    signUpEmail.setError("Email Id Incorrect");
                    signUpEmail.requestFocus();
                    return;
                }

                if (password.length()<=6) {
                    signUpPassword.setError("Password should be at least 6 characters long");
                    signUpPassword.requestFocus();
                    return;
                }

                signUpAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));

                                } else {
                                    Toast.makeText(SignUpActivity.this,"SignUp failed",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });

    }
}
