package com.nrick.ongkur;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Login extends AppCompatActivity {

    private EditText email, password;
    private Button login, register;
    private ProgressBar progressBar;
    private ImageView logo;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, mDatabase1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editText_email);
        password = findViewById(R.id.editText_password);
        login = findViewById(R.id.button_login);
        register = findViewById(R.id.button_register);
        progressBar = findViewById(R.id.progressBar);
        logo = findViewById(R.id.imageView);
        progressBar.setVisibility(View.GONE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EMAIL = email.getText().toString().trim(),
                        PASSWORD = password.getText().toString().trim();

                if(EMAIL.isEmpty()){
                    email.setError("Please give a valid email");
                }else if(PASSWORD.isEmpty()){
                    password.setError("Password can't be empty");
                }else{
                    logo.setVisibility(View.GONE);
                    email.setVisibility(View.GONE);
                    password.setVisibility(View.GONE);
                    login.setVisibility(View.GONE);
                    register.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    signInUser(EMAIL, PASSWORD);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.right_to_left_exit);
                finish();
            }
        });
    }

    private void signInUser(String Email, String Password) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Authentication Complete", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    String UID = user.getUid();
                    divertUser(UID);
                }else{
                    Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                    logo.setVisibility(View.VISIBLE);
                    email.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                    login.setVisibility(View.VISIBLE);
                    register.setVisibility(View.VISIBLE);
                }

                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void divertUser(String uid) {
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left, R.anim.right_to_left_exit);
        finish();
    }
}
