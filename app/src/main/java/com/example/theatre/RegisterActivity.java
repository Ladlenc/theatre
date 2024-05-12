package com.example.theatre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private static final  String LOG_TAG = RegisterActivity.class.getName();
    private FirebaseAuth mauth;

    EditText username_et;
    EditText email_et;
    EditText password_et;
    EditText confirm_pass_et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);

        username_et = findViewById(R.id.username);
        email_et = findViewById(R.id.email);
        password_et = findViewById(R.id.password);
        confirm_pass_et = findViewById(R.id.confirm_pass);


        mauth = FirebaseAuth.getInstance();

    }


    public void toLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    public void toMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void onRegister(View view) {

        String username = username_et.getText().toString();
        String email= email_et.getText().toString();
        String pass= password_et.getText().toString();
        String con_pass= confirm_pass_et.getText().toString();

        if(!pass.equals(con_pass)){
            Log.e(LOG_TAG,"The passwords do not match.");
            return;
        }

        mauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this,task -> {
           if (task.isSuccessful()){
               Log.d(LOG_TAG,"Registration succesfull");
               toMain();
           } else {
               Log.d(LOG_TAG,"Registration unseccesfull");
               Toast.makeText(RegisterActivity.this,"Couldn't create a User: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
           }
        });
    }
}
