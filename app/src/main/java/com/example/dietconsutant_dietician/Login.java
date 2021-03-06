package com.example.dietconsutant_dietician;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Login extends AppCompatActivity {
    EditText user_id, pass;
    Button login;
    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // toolbar add here
        toolbar =findViewById(R.id.bar);
        setSupportActionBar(toolbar);

        user_id = findViewById(R.id.mail);
        pass = findViewById(R.id.pwd);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(user_id.getText().toString())) {
                    Toast.makeText(Login.this, "Please Enter Email.address",
                            Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(user_id.getText().toString()).matches()) {
                    Toast.makeText(Login.this, "Please Enter Valid Email.address",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass.getText().toString())) {
                    Toast.makeText(Login.this, "Please Enter Password",
                            Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().length() < 8) {
                    Toast.makeText(Login.this, "Please Enter 8 & more then 8 Digit Password",
                            Toast.LENGTH_SHORT).show();
                } else {
                    registeration();
                }

            }
        });
    }

    private void registeration() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(user_id.getText
                ().toString(), pass.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent;
                        intent = new Intent(Login.this, MainActivity.class );
                        startActivity(intent);


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "Failed Wrong Password and Email :/" ,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}