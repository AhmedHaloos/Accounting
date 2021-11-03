package com.engashm.possaror.sell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.engashm.possaror.R;

public class ManagementSellActivity extends AppCompatActivity {

    Button sellBillEdit, sellReports, sellEarnings, sellBillDelegate, sellBillClients, sellOrders;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_sell_layout);
        sellBillEdit = findViewById(R.id.sell_bill_edit_btn);
        sellReports = findViewById(R.id.sell_reports_btn);
        sellEarnings = findViewById(R.id.sell_earnings_btn);
        sellBillDelegate = findViewById(R.id.sell_delegate_bill_btn);
        sellBillClients = findViewById(R.id.sell_client_bill_btn);
        sellOrders = findViewById(R.id.sell_orders_btn);

        // setListeners
        sellBillEdit.setOnClickListener(billEditListener);
        sellReports.setOnClickListener(reportsListener);
        sellOrders.setOnClickListener(orderListener);
        sellEarnings.setOnClickListener(earningsListener);
        sellBillClients.setOnClickListener(clientListener);
        sellBillDelegate.setOnClickListener(delegateListener);

    }
    // listeners
    View.OnClickListener billEditListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementSellActivity.this
                    , SellBillsEditActivity.class );
            startActivity(intent);
        }
    };
    View.OnClickListener reportsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementSellActivity.this,
                    SellReportsActvity.class);

           startActivity(intent);
        }
    };
    View.OnClickListener earningsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementSellActivity.this,
                    SellEarningsActivity.class);

            startActivity(intent);
        }
    };
    View.OnClickListener delegateListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementSellActivity.this,
                    SellDelegateActivity.class);

            startActivity(intent);
        }
    };
    View.OnClickListener clientListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementSellActivity.this,
                    SellClientActivity.class);

            startActivity(intent);
        }
    };

    View.OnClickListener orderListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementSellActivity.this,
                    SellOrderActivity.class);
            startActivity(intent);
        }
    };
}
