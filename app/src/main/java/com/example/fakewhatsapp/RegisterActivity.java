package com.example.fakewhatsapp;

import android.app.ProgressDialog;
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

public class RegisterActivity extends AppCompatActivity {
private Button RegisterButton;
    private FirebaseAuth mAuth;
private ProgressDialog loadingBar;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button RegisterButton = (Button) findViewById(R.id.button_register);



        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateNewAccount();


            }
        });

    }

    private void CreateNewAccount() {
        EditText RegisterPassword = (EditText) findViewById(R.id.password_register);
        EditText RegisterEmail = (EditText) findViewById(R.id.email_register);

   String registerPassword = RegisterPassword.getText().toString();
        String registerEmail = RegisterEmail.getText().toString();
loadingBar =new ProgressDialog(this);
        if(TextUtils.isEmpty(registerEmail)){
            Toast.makeText(this,"Please type your email adress.",Toast.LENGTH_LONG);

        }

        else if(TextUtils.isEmpty(registerPassword)){

            Toast.makeText(this,"Please type your password.",Toast.LENGTH_LONG);
        }

   else{
            loadingBar.setTitle("Creating new account.");
            loadingBar.setMessage("Please while we are creating a new account for you.");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

       mAuth.createUserWithEmailAndPassword(registerEmail,registerPassword)
               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful()){

    Toast.makeText(RegisterActivity.this,"Account created sucessfully. ", Toast.LENGTH_LONG).show();

}

        else {

            String message = task.getException().toString();

    Toast.makeText(RegisterActivity.this,"Error: "+message, Toast.LENGTH_LONG).show();

}

                   }
               });


        }


    }
}
