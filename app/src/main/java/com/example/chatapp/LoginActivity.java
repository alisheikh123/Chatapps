package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    AppCompatEditText Email,pasword;

    FirebaseAuth auth;
    DatabaseReference reference;
    AppCompatButton Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getActionBar().setTitle("Login");
        getActionBar().setDisplayHomeAsUpEnabled(true);*/

        Email = findViewById(R.id.Email);
        pasword = findViewById(R.id.Password);
        Login = findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_Email = Email.getText().toString();
                String txt_Password = pasword.getText().toString();

                if(txt_Email.isEmpty()||txt_Password.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "TextField Is Empty!", Toast.LENGTH_SHORT).show();
                }
                else if(txt_Password.length()<6)
                {
                    Toast.makeText(LoginActivity.this, "maximum character of Password is 6", Toast.LENGTH_SHORT).show();
                }else
                {
                    Login(txt_Email,txt_Password);

                }

            }
        });


    }
    private  void Login(final String email,String Password){
        auth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){


                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                    

                }
                else{

                    Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
