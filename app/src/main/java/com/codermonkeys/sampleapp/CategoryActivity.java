package com.codermonkeys.sampleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.codermonkeys.sampleapp.adapters.HomePageAdapter;
import com.codermonkeys.sampleapp.models.HomePageModel;
import com.codermonkeys.sampleapp.models.HorizontalProductScrollModel;
import com.codermonkeys.sampleapp.models.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryActivity extends AppCompatActivity {

    //Widget Component's
    private RecyclerView categoryRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String title = getIntent().getStringExtra("CategoryName");
        setTitle(title);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recycleview);

        /////////Banner Slider

        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.ic_sign_out));
        sliderModelList.add(new SliderModel(R.drawable.ic_mall));
        sliderModelList.add(new SliderModel(R.drawable.banner));

        sliderModelList.add(new SliderModel(R.drawable.ic_logo));
        sliderModelList.add(new SliderModel(R.drawable.ic_account));
        sliderModelList.add(new SliderModel(R.drawable.ic_cart));
        sliderModelList.add(new SliderModel(R.drawable.ic_favorite));
        sliderModelList.add(new SliderModel(R.drawable.ic_home));
        sliderModelList.add(new SliderModel(R.drawable.ic_orders));
        sliderModelList.add(new SliderModel(R.drawable.ic_profile));
        sliderModelList.add(new SliderModel(R.drawable.ic_rewards));
        sliderModelList.add(new SliderModel(R.drawable.ic_sign_out));

        sliderModelList.add(new SliderModel(R.drawable.ic_mall));
        sliderModelList.add(new SliderModel(R.drawable.banner));
        sliderModelList.add(new SliderModel(R.drawable.ic_logo));

        ///////Banner Slider

        /////Horizontal Product Layout

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mbl, "Redmi Note 5A", "SD 625 Processor", "Rs.5,999"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.oppo, "Oppo F9", "SD 760 Processor", "Rs.25,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.a2, "Mi A2 Lite", "SD 631 Processor", "Rs.8,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.j7, "Samsung Galaxy j7", "SD 625 Processor", "Rs.5,999"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.htc, "HTC", "SD 625 Processor", "Rs.5,999"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.windows, "Windows", "SD 625 Processor", "Rs.5,999"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.a2, "Mi A2", "SD 625 Processor", "Rs.5,999"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mbl, "Redmi Note 5A", "SD 625 Processor", "Rs.5,999"));

        /////Horizontal Product Layout


        ///////////////MultiLay RecyclerView
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(RecyclerView.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.mbl, "#000000"));
        homePageModelList.add(new HomePageModel(2,"Deals of the day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.mbl, "#000000"));
        homePageModelList.add(new HomePageModel(3,"Deals of the day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deals of the day", horizontalProductScrollModelList));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ///////////////MultiLay RecyclerView
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.main_search_icon) {
            //Todo: search
        } else if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
