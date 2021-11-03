package com.engashm.possaror.stock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.engashm.possaror.R;

public class ManagementStockActivity extends AppCompatActivity {

    Button productMove, reports, stocktaking;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_stock_layout);
        intiViews();
    }
    private void intiViews(){
        productMove = findViewById(R.id.offer_add_new);
        stocktaking = findViewById(R.id.stocktaking);
        reports = findViewById(R.id.offer_clients);

        productMove.setOnClickListener(productsMoveListener);
        stocktaking.setOnClickListener(stocktakingListener);
        reports.setOnClickListener(reportsListener);
    }
    View.OnClickListener productsMoveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        startActivity(new Intent(ManagementStockActivity.this, StockProductActivity.class));
        }
    };
    View.OnClickListener stocktakingListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(ManagementStockActivity.this, StocktakingActivity.class));

        }
    };
    View.OnClickListener reportsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(ManagementStockActivity.this, StockReports.class));

        }
    };

}
