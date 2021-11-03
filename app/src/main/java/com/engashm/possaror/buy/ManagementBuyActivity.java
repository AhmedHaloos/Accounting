package com.engashm.possaror.buy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.engashm.possaror.R;

public class ManagementBuyActivity extends AppCompatActivity {

    Button products, bills, reports, addGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_buy_layout);
        products = findViewById(R.id.buy_product);
        bills = findViewById(R.id.buy_bill);
        reports = findViewById(R.id.buy_reports);
        addGroup = findViewById(R.id.buy_group);

        // set listeners
        products.setOnClickListener(productsListener);
        bills.setOnClickListener(billsListener);
        reports.setOnClickListener(reportsListener);
        addGroup.setOnClickListener(addGroupListener);
    }

    //listeners
    View.OnClickListener productsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementBuyActivity.this,
                    BuyProducts.class );
            startActivity(intent);
        }
    };

    View.OnClickListener addGroupListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementBuyActivity.this, BuyGroups.class );
            startActivity(intent);
        }
    };

    View.OnClickListener reportsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementBuyActivity.this, BuyReports.class );
            startActivity(intent);
        }
    };

    View.OnClickListener billsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementBuyActivity.this,
                    BuyBills.class );
            startActivity(intent);
        }
    };


}
