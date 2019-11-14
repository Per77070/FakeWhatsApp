package com.example.fakewhatsapp;

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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
private FirebaseUser currentUser;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button loginButton = (Button) findViewById(R.id.button_login);

    loginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AllowUserToLogin();

       mAuth = FirebaseAuth.getInstance();

        }
    });

    }

    private void AllowUserToLogin() {

        EditText UserEmail = (EditText) findViewById(R.id.email_login);
        EditText UserPassword = (EditText) findViewById(R.id.password_login);


        String userEmail = UserEmail.getText().toString();
        String userPassword = UserPassword.getText().toString();

   if(TextUtils.isEmpty(userEmail)){
       Toast.makeText(this,"Please type your email adress.",Toast.LENGTH_LONG);

   }

       else if(TextUtils.isEmpty(userPassword)){

       Toast.makeText(this,"Please type your password.",Toast.LENGTH_LONG);
        }
   else{

mAuth.signInWithEmailAndPassword(userEmail,userPassword)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful()){

    sendUserToMainActivity();
}
            }
        });

   }

    }

    private void sendUserToMainActivity() {

        Intent intent = new Intent( LoginActivity.this,MainActivity.class);
startActivity(intent);
    }


}
