package com.engashm.possaror.delegates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.engashm.possaror.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class ManagementDelegateActivity extends AppCompatActivity {
    ExtendedFloatingActionButton addDelegate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_delegates_layout);
        addDelegate = findViewById(R.id.delegate_add_btn);
        addDelegate.setOnClickListener(addDelegateListener);
    }

    View.OnClickListener addDelegateListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementDelegateActivity.this,
                    NewDelegateActivity.class);
            startActivity(intent);
        }
    };
}
