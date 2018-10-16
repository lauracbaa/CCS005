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

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText mName, mTelephone, createEmail , createPassword, confirmPassword;
    private Button mBtnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mName= (EditText)findViewById(R.id.txtName);
        createEmail = (EditText)findViewById(R.id.txtAddEmail);
        createPassword= (EditText)findViewById(R.id.txtAddPassword);
        confirmPassword= (EditText)findViewById(R.id.txtPasswordConfirm);
        mBtnCreate = (Button)findViewById(R.id.btnCreate);




        mBtnCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String regEmail = createEmail.getText().toString();
                String regPassword = createPassword.getText().toString();
                String cfPassword = confirmPassword.getText().toString();

                if(!TextUtils.isEmpty(regEmail) && !TextUtils.isEmpty(regPassword) && !TextUtils.isEmpty(cfPassword)){

                    if(regPassword.equals(cfPassword)){

                        mAuth.createUserWithEmailAndPassword(regEmail,regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task){

                                if(task.isSuccessful()){
                                    startMain();

                                }
                                else {

                                    String eMessage = task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this,"Error:"+eMessage, Toast.LENGTH_SHORT);


                                }

                            }
                        });
                    }

                    else{

                        Toast.makeText(RegisterActivity.this,"Confirm Password", Toast.LENGTH_SHORT);
                    }
                }
                else{

                    Toast.makeText(RegisterActivity.this,"There is a bug", Toast.LENGTH_SHORT);

                }
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

        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
