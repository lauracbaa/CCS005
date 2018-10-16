package com.example.laura.ccs005;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AccountActivity extends AppCompatActivity {


    private Toolbar mTbAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mTbAccount = (Toolbar)findViewById(R.id.tbMain);
        setSupportActionBar(mTbAccount);

        getSupportActionBar().setTitle("Account Information");
    }
}
