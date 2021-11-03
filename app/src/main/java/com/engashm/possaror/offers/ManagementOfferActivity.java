package com.engashm.possaror.offers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.engashm.possaror.R;

public class ManagementOfferActivity extends AppCompatActivity {

    Button addOffer, clientsOffer, delegateOffer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_offers_layout);
        addOffer = findViewById(R.id.offer_add_new);
        clientsOffer = findViewById(R.id.offer_clients);
        delegateOffer = findViewById(R.id.offer_delegates);
        //listeners register
        addOffer.setOnClickListener(addOfferListener);
        delegateOffer.setOnClickListener(delegatesOfferListener);
        clientsOffer.setOnClickListener(clientsOfferListener);
        ;
    }
    // Listeners
    View.OnClickListener addOfferListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(ManagementOfferActivity.this, OffersAddNewActivity.class));
        }
    };
    View.OnClickListener clientsOfferListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(ManagementOfferActivity.this, OffersClientsActivity.class));
        }
    };
    View.OnClickListener delegatesOfferListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(ManagementOfferActivity.this, OffersDelegatesActivity.class));
        }
    };


}
