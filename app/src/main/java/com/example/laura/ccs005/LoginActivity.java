package com.example.laura.ccs005;

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

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mtxtEmail, mtxtPassword;
    private Button mBtnLogin, mBtnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        mtxtEmail = (EditText)findViewById(R.id.txtEmail);
        mtxtPassword = (EditText)findViewById(R.id.txtPassword);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnRegister = (Button)findViewById(R.id.btnRegister);

        mBtnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String LogEmail = mtxtEmail.getText().toString();
                String LogPassword = mtxtPassword.getText().toString();

                if(!TextUtils.isEmpty(LogEmail)&& !TextUtils.isEmpty(LogPassword)){

                    mAuth.signInWithEmailAndPassword(LogEmail, LogPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                startMain();

                            }
                            else {
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "ErrorL"+ errorMessage, Toast.LENGTH_LONG);

                            }
                        }
                    });
                }
                else{

                    Toast.makeText(LoginActivity.this,"There is a bug", Toast.LENGTH_SHORT);

                }
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regView = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regView);
            }
        });
    }



    @Override
    protected void onStart (){
        super.onStart();

        //checking if user is logged on
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null){
         startMain();
        }


    }

    private void startMain() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
