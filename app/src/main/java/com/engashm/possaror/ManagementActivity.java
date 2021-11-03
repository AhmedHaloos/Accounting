package com.engashm.possaror;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.engashm.possaror.box.ManagementBoxActivity;
import com.engashm.possaror.buy.ManagementBuyActivity;
import com.engashm.possaror.clients.ManagementClientsActivity;
import com.engashm.possaror.delegates.ManagementDelegateActivity;
import com.engashm.possaror.offers.ManagementOfferActivity;
import com.engashm.possaror.sell.ManagementSellActivity;
import com.engashm.possaror.stock.ManagementStockActivity;
import com.engashm.possaror.suppliers.ManagementSuppliersActivity;

public class ManagementActivity extends AppCompatActivity {


    private CardView store, buy, sell, clients, delegates, box, offer, suppliers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_layout);
        store = findViewById(R.id.management_store);
        buy   = findViewById(R.id.management_buy);
        sell  = findViewById(R.id.management_sell);
        clients = findViewById(R.id.management_clients);
        delegates = findViewById(R.id.management_delegates);
        offer = findViewById(R.id.management_offer);
        suppliers = findViewById(R.id.management_suppliers);
        box = findViewById(R.id.management_box);

        // setListeners
        store.setOnClickListener(storeListener);
        buy.setOnClickListener(buyListener);
        sell.setOnClickListener(sellListener);
        clients.setOnClickListener(clientListener);
        delegates.setOnClickListener(delegateListener);
        offer.setOnClickListener(offerListener);
        suppliers.setOnClickListener(supplierListener);
        box.setOnClickListener(boxListener);

    }

    //listeners
    View.OnClickListener storeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementActivity.this,
                    ManagementStockActivity.class );
            startActivity(intent);
        }
    };

    View.OnClickListener buyListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementActivity.this,
                    ManagementBuyActivity.class );
            startActivity(intent);
        }
    };

    View.OnClickListener sellListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementActivity.this,
                    ManagementSellActivity.class );
            startActivity(intent);
        }
    };

    View.OnClickListener offerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementActivity.this,
                    ManagementOfferActivity.class );
            startActivity(intent);
        }
    };

    View.OnClickListener clientListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementActivity.this,
                    ManagementClientsActivity.class );
            startActivity(intent);
        }
    };

    View.OnClickListener delegateListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementActivity.this,
                    ManagementDelegateActivity.class );
            startActivity(intent);
        }
    };

    View.OnClickListener supplierListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementActivity.this,
                    ManagementSuppliersActivity.class );
            startActivity(intent);
        }
    };

    View.OnClickListener boxListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ManagementActivity.this,
                    ManagementBoxActivity.class );
            startActivity(intent);
        }
    };

}
