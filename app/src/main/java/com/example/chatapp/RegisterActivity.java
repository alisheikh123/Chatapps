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

public class RegisterActivity extends AppCompatActivity {
        AppCompatEditText username,email,pass;
        FirebaseAuth auth;
       DatabaseReference  reference;
        AppCompatButton register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getActionBar().setTitle("Register");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);*/

     username =findViewById(R.id.username);
     email = findViewById(R.id.Email);
     pass = findViewById(R.id.Password);
     register = findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = username.getText().toString();
                String txt_Email = email.getText().toString();
                String txt_Password = pass.getText().toString();

                if(txt_username.isEmpty()||txt_Email.isEmpty()||txt_Password.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "TextField Is Empty!", Toast.LENGTH_SHORT).show();
                }
                else if(txt_Password.length()<6)
                {
                    Toast.makeText(RegisterActivity.this, "maximum character of Password is 6", Toast.LENGTH_SHORT).show();
                }else
                    {
                        register(txt_username,txt_Email,txt_Password);

                    }

            }
        });


    }
    private  void register(final String username,String email,String Password){
        auth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    assert firebaseUser!=null;
                    String UserId = firebaseUser.getUid();

                    reference = FirebaseDatabase.getInstance().getReference("Users").child(UserId);
                    HashMap<String,String>hashMap = new HashMap<>();
                    hashMap.put("id",UserId);
                    hashMap.put("username",username);
                    hashMap.put("imageurl","default");

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                            }
                        }
                    });







                }
            }
        });



    }

}
