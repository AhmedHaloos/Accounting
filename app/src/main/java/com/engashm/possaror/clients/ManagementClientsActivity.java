package com.engashm.possaror.clients;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.engashm.possaror.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class ManagementClientsActivity extends AppCompatActivity {

   ExtendedFloatingActionButton addClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_clients_layout);
        addClient = findViewById(R.id.client_add_btn);
        addClient.setOnClickListener(addNewClientListener);

    }


    View.OnClickListener addNewClientListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementClientsActivity.this, NewClientActivity.class);
            startActivity(intent);
        }
    };
}
