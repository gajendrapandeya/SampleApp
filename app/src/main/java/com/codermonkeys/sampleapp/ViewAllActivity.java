package com.codermonkeys.sampleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.codermonkeys.sampleapp.adapters.GridProductLayoutAdapter;
import com.codermonkeys.sampleapp.adapters.WishListAdapter;
import com.codermonkeys.sampleapp.models.HorizontalProductScrollModel;
import com.codermonkeys.sampleapp.models.WishListModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewAllActivity extends AppCompatActivity {

    ///Ui component's
    private RecyclerView recyclerView;
    private GridView gridView;

    //var's
    public static List<HorizontalProductScrollModel> horizontalProductScrollModelList;
    public static List<WishListModel> wishListModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        recyclerView = findViewById(R.id.recycler_view);
        gridView = findViewById(R.id.grid_view);

        int layoutCode = getIntent().getIntExtra("layout_code", -1);

        if(layoutCode == 0) {
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);




            WishListAdapter adapter = new WishListAdapter(wishListModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else if(layoutCode == 1) {

            gridView.setVisibility(View.VISIBLE);

            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(gridProductLayoutAdapter);

        }
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
