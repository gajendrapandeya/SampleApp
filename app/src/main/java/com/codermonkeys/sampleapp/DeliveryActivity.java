package com.codermonkeys.sampleapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codermonkeys.sampleapp.adapters.CartAdapter;
import com.codermonkeys.sampleapp.models.CartItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeliveryActivity extends AppCompatActivity {

    //Ui component's
    private RecyclerView deliveryRecyclerView;
    private Button changeOrAddNewAddressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Delivery");

        deliveryRecyclerView = findViewById(R.id.delivery_recycler_view);
        changeOrAddNewAddressButton = findViewById(R.id.change_or_add_address_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        deliveryRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, R.drawable.product_image, 2, 1, "Google Pixel 3a(Black)", "Rs.9,000/-", "Rs.10,000/-" , 0, 0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.product_image, 2, 1, "Google Pixel 3a(Black)", "Rs.9,000/-", "Rs.10,000/-" , 0, 0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.product_image, 2, 1, "Google Pixel 3a(Black)", "Rs.9,000/-", "Rs.10,000/-" , 0, 0));

        cartItemModelList.add(new CartItemModel(1, "Price (3 items)", "Rs.15,999/-", "150/-", "Rs.16,999/-", "Rs.1000/-"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        deliveryRecyclerView.setAdapter(cartAdapter);

        changeOrAddNewAddressButton.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
