package com.codermonkeys.sampleapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.codermonkeys.sampleapp.adapters.AddressesAdapter;
import com.codermonkeys.sampleapp.models.AddressesModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyAddressActivity extends AppCompatActivity {

    //Ui component's
    private RecyclerView myAddressesRecyclerView;
    private Button deliverHereBtn;

    //var's
    private static AddressesAdapter addressesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Addresses");

        myAddressesRecyclerView = findViewById(R.id.addresses_recycler_view);
        deliverHereBtn = findViewById(R.id.delivered_here_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myAddressesRecyclerView.setLayoutManager(layoutManager);

        List<AddressesModel> addressesModelList = new ArrayList<>();
        addressesModelList.add(new AddressesModel("John Doe", "Dhangadhi, Kailali", "22446", true));
        addressesModelList.add(new AddressesModel("John Doe", "Dhangadhi, Kailali", "22446", false));
        addressesModelList.add(new AddressesModel("John Doe", "Dhangadhi, Kailali", "22446", false));
        addressesModelList.add(new AddressesModel("Gajendra Pandeya", "Suryabinyak, Bhaktapur", "12312", false));
        addressesModelList.add(new AddressesModel("Gajendra Pandeya", "Suryabinyak, Bhaktapur", "12312", false));
        addressesModelList.add(new AddressesModel("Gajendra Pandeya", "Suryabinyak, Bhaktapur", "12312", false));
        addressesModelList.add(new AddressesModel("Niraj Doe", "Banglore", "22446", false));
        addressesModelList.add(new AddressesModel("Niraj Doe", "Banglore", "22446", false));
        addressesModelList.add(new AddressesModel("Niraj Doe", "Banglore", "22446", false));
        addressesModelList.add(new AddressesModel("John Doe", "Dhangadhi, Kailali", "22446", false));
        addressesModelList.add(new AddressesModel("John Doe", "Dhangadhi, Kailali", "22446", false));

        int mode = getIntent().getIntExtra("MODE", -1);
        if(mode == DeliveryActivity.SELECT_ADDRESS) {
            deliverHereBtn.setVisibility(View.VISIBLE);
        } else {
            deliverHereBtn.setVisibility(View.GONE);
            //deliverHereBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        }
        addressesAdapter = new AddressesAdapter(addressesModelList, mode);
        ((SimpleItemAnimator) Objects.requireNonNull(myAddressesRecyclerView.getItemAnimator())).setSupportsChangeAnimations(false);
        myAddressesRecyclerView.setAdapter(addressesAdapter);
        addressesAdapter.notifyDataSetChanged();
    }

    public static void refreshItem(int deselect, int select) {

        addressesAdapter.notifyItemChanged(deselect);
        addressesAdapter.notifyItemChanged(select);
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
