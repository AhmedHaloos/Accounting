package com.engashm.possaror.offers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import com.engashm.possaror.Data.Offer;
import com.engashm.possaror.Data.Product;
import com.engashm.possaror.R;

public class OffersAddNewActivity extends AppCompatActivity {

    Button addOffer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers_add_new);
       //  getProduct((ViewGroup) getWindow().getDecorView().getParent(), 100, 100);

    }

  private Product getProduct(   ){


     return null;
  }
  private void addOfferToDB(Offer offer){}

}