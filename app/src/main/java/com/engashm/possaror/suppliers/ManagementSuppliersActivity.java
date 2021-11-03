package com.engashm.possaror.suppliers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.engashm.possaror.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class ManagementSuppliersActivity extends AppCompatActivity {

    ExtendedFloatingActionButton addSupplier;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_suppliers_layout);
        addSupplier = findViewById(R.id.supplier_add_btn);
        addSupplier.setOnClickListener(addSupplierListener);
    }

    View.OnClickListener addSupplierListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementSuppliersActivity.this,
                    NewSupplierActivity.class);
            startActivity(intent);
        }
    };
}
