package com.engashm.possaror.buy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.engashm.possaror.R;

public class BuyBills extends AppCompatActivity {
    Button buyBills;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_bills);
        buyBills = findViewById(R.id.buy_new_bill);
    }

    View.OnClickListener buyBillListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(BuyBills.this, NewBuyBillActivity.class );
            startActivity(intent);
        }
    };
}
