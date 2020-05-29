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
import com.codermonkeys.sampleapp.resources.DBqueries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codermonkeys.sampleapp.resources.DBqueries.lists;
import static com.codermonkeys.sampleapp.resources.DBqueries.loadFragmentData;
import static com.codermonkeys.sampleapp.resources.DBqueries.loadedCategoriesName;

public class CategoryActivity extends AppCompatActivity {

    //Widget Component's
    private RecyclerView categoryRecyclerView;

    //var's
    private  HomePageAdapter adapter;
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


        ///////////////MultiLay RecyclerView
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(RecyclerView.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        int listPosition = 0;
        for(int x = 0; x < loadedCategoriesName.size(); x++) {

            assert title != null;
            if(loadedCategoriesName.get(x).equals(title.toUpperCase())) {
                listPosition = x;
            }
        }

        if(listPosition == 0) {

            loadedCategoriesName.add("HOME");
            lists.add(new ArrayList<HomePageModel>());
            adapter = new HomePageAdapter(lists.get(loadedCategoriesName.size() - 1));
            loadFragmentData(adapter, this, loadedCategoriesName.size() - 1, title);

        } else {

            adapter = new HomePageAdapter(lists.get(listPosition));
        }

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
