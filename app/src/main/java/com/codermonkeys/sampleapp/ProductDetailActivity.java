package com.codermonkeys.sampleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.codermonkeys.sampleapp.adapters.ProductImagesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDetailActivity extends AppCompatActivity {

    //Widget Component's
    private ViewPager productImageViewPager;
    private TabLayout viewPagerIndicator;
    private FloatingActionButton addtoWishListBtn;
    private static boolean ALREADYADDEDTOWISHLIST = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImageViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewpager_indicator);
        addtoWishListBtn = findViewById(R.id.add_to_wish_list_button);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.banner);
        productImages.add(R.drawable.a2);
        productImages.add(R.drawable.oppo);
        productImages.add(R.drawable.j7);

        viewPagerIndicator.setupWithViewPager(productImageViewPager);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImageViewPager.setAdapter(productImagesAdapter);

        addtoWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ALREADYADDEDTOWISHLIST) {
                    ALREADYADDEDTOWISHLIST = false;
                    addtoWishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9c9c9c")));
                } else {
                    ALREADYADDEDTOWISHLIST = true;
                    addtoWishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_search_icon:
                //Todo: search
                break;

            case R.id.main_cart_icon:
                //todo: cart system
                break;

            case android.R.id.home:

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
