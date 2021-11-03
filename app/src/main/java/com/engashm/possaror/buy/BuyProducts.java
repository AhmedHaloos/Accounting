package com.engashm.possaror.buy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.engashm.possaror.NewProductActivity;
import com.engashm.possaror.R;

public class BuyProducts extends AppCompatActivity {

    SearchView searchProducts;
    Button addProduct, srchProduct;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_products);
        initViews();
    }
    private void initViews(){
        searchProducts = findViewById(R.id.buy_product_srch);
        addProduct = findViewById(R.id.buy_add_product_btn);
        srchProduct = findViewById(R.id.buy_barcode_product);

        //listeners
        addProduct.setOnClickListener(addProductListener);
        srchProduct.setOnClickListener(searchProductsListener);
    }
    View.OnClickListener addProductListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(BuyProducts.this, NewProductActivity.class);
            startActivity(intent);

        }
    };
    View.OnClickListener searchProductsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

}
