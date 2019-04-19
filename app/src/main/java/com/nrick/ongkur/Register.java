package com.nrick.ongkur;

import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText name, guardian_name, phone, email, password;
    private Button login, register;
    private ProgressBar progressBar;
    private ImageView logo;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.editText_name_register);
        guardian_name = findViewById(R.id.editText_guardian_name_register);
        email = findViewById(R.id.editText_email_register);
        phone = findViewById(R.id.editText_phone_register);
        password = findViewById(R.id.editText_password_register);
        login = findViewById(R.id.button_login_register);
        register = findViewById(R.id.button_register_register);
        progressBar = findViewById(R.id.progressBar_register);

        logo = findViewById(R.id.topLogo);

        progressBar.setVisibility(View.GONE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String NAME = name.getText().toString().trim(),
                        GUARDIAN_NAME = guardian_name.getText().toString().trim(),
                        EMAIL = email.getText().toString().trim(),
                        PASSWORD = password.getText().toString(),
                        PHONE = phone.getText().toString();

                if(NAME.isEmpty()){
                    name.setError("Please type your name");
                }
                else if(EMAIL.isEmpty()){
                    email.setError("Please give a valid email");
                }
                else if(PASSWORD.isEmpty()){
                    password.setError("Password can't be empty");
                }
                else if(PHONE.isEmpty()){
                    phone.setError("Give a valid mobile number");
                }
                else if(GUARDIAN_NAME.isEmpty()){
                    guardian_name.setError("Give a valid name");
                }
                else {
                    logo.setVisibility(View.GONE);
                    name.setVisibility(View.GONE);
                    email.setVisibility(View.GONE);
                    password.setVisibility(View.GONE);
                    login.setVisibility(View.GONE);
                    register.setVisibility(View.GONE);
                    guardian_name.setVisibility(View.GONE);
                    phone.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    createUser(NAME, EMAIL, PASSWORD, PHONE, GUARDIAN_NAME);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.right_to_left_exit);
                finish();
            }
        });
    }

    private void createUser(final String NAME, final String EMAIL, final String PASSWORD, final String PHONE, final String GUARDIAN_NAME) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            final String UID = user.getUid();

                            mDatabase = FirebaseDatabase.getInstance().getReference().child("USERS").child(UID);
                            mDatabase.child("NAME").setValue(NAME);
                            mDatabase.child("EMAIL").setValue(EMAIL);
                            mDatabase.child("PHONE").setValue(PHONE);
                            mDatabase.child("GUARDIAN_NAME").setValue(GUARDIAN_NAME);

                            Toast.makeText(getApplicationContext(), "Welcome "+NAME,
                                    Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(Register.this, SliderOne.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Authentication failed",
                                    Toast.LENGTH_SHORT).show();

                            logo.setVisibility(View.VISIBLE);
                            email.setVisibility(View.VISIBLE);
                            password.setVisibility(View.VISIBLE);
                            login.setVisibility(View.VISIBLE);
                            register.setVisibility(View.VISIBLE);
                            guardian_name.setVisibility(View.VISIBLE);
                            phone.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
