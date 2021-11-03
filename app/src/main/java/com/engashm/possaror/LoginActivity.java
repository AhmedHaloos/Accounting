package com.engashm.possaror;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class LoginActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        login = findViewById(R.id.login_btn);
        login.setOnClickListener(loginListener);
    }

    View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, ManagementActivity.class);
            startActivity(intent);
        }
    };
}
