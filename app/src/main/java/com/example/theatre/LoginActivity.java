package com.example.theatre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email_et;
    EditText password_et;

    private FirebaseAuth mauth;

    private static final  String LOG_TAG = LoginActivity.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        email_et= findViewById(R.id.email);
        password_et= findViewById(R.id.password);

        mauth = FirebaseAuth.getInstance();

    }

    public void onLogin(View view) {
        String email = email_et.getText().toString();
        String pass = password_et.getText().toString();

        mauth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
                Log.d(LOG_TAG, "Login Successful");
                toMain();
            }else{
                Log.d(LOG_TAG,"Login Unsuccessful");
                Toast.makeText(LoginActivity.this,"Couldn't login User: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

            }
            }
        });
    }

    public void toRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);

    }

    public void toMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
