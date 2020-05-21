package com.codermonkeys.sampleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codermonkeys.sampleapp.adapters.MyRewardsAdapter;
import com.codermonkeys.sampleapp.adapters.ProductDetailsAdapter;
import com.codermonkeys.sampleapp.adapters.ProductImagesAdapter;
import com.codermonkeys.sampleapp.models.RewardsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codermonkeys.sampleapp.MainActivity.showCart;

public class ProductDetailActivity extends AppCompatActivity {

    //Widget Component's
    private ViewPager productImageViewPager;
    private TabLayout viewPagerIndicator;
    private FloatingActionButton addToWishListBtn;
    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;
    private Button buyNowBtn;
    private Button coupenRedemBtn;



    //Var's
    private static boolean ALREADYADDEDTOWISHLIST = false;

    ///coupen dialog var's
    public static TextView coupenTitle;
    public static TextView coupenBody;
    public static TextView coupenExpiryDate;
    private static RecyclerView coupensRecyclerView;
    private static LinearLayout selectedCoupen;

    /////Rating Star
    private LinearLayout rateNowContainer;
    /////Rating Star

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initWidgets();

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.banner);
        productImages.add(R.drawable.a2);
        productImages.add(R.drawable.oppo);
        productImages.add(R.drawable.j7);

        viewPagerIndicator.setupWithViewPager(productImageViewPager);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImageViewPager.setAdapter(productImagesAdapter);

        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ALREADYADDEDTOWISHLIST) {
                    ALREADYADDEDTOWISHLIST = false;
                    addToWishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9c9c9c")));
                } else {
                    ALREADYADDEDTOWISHLIST = true;
                    addToWishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                }
            }
        });

        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount()));
        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));

        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        ///////Rating Star

        for(int x = 0; x < rateNowContainer.getChildCount(); x++) {

            final int starPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                }
            });
        }
        ///////Rating Star

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(ProductDetailActivity.this, DeliveryActivity.class);
                startActivity(deliveryIntent);
            }
        });

        /////////Coupen Dialog

        final Dialog checkCoupenPriceDialog = new Dialog(ProductDetailActivity.this);
        checkCoupenPriceDialog.setContentView(R.layout.coupen_redem_dialog);
        checkCoupenPriceDialog.setCancelable(true);

        checkCoupenPriceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView toggleRecyclerView = checkCoupenPriceDialog.findViewById(R.id.toggle_recycler_view);
        coupensRecyclerView = checkCoupenPriceDialog.findViewById(R.id.coupens_recycler_view);
        selectedCoupen = checkCoupenPriceDialog.findViewById(R.id.selected_coupen);
        TextView originalPrice = checkCoupenPriceDialog.findViewById(R.id.original_price);
        TextView discountedPrice = checkCoupenPriceDialog.findViewById(R.id.discounted_price);
        coupenTitle = checkCoupenPriceDialog.findViewById(R.id.coupen_title);
        coupenExpiryDate = checkCoupenPriceDialog.findViewById(R.id.coupen_validity);
        coupenBody = checkCoupenPriceDialog.findViewById(R.id.coupen_body);


        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        coupensRecyclerView.setLayoutManager(layoutManager);

        List<RewardsModel> rewardsModelList = new ArrayList<>();
        rewardsModelList.add(new RewardsModel("Cashback", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Discount", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Buy 1 get 1 free", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Cashback", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Discount", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Cashback", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Buy 1 get 3 free", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));

        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardsModelList, true);
        coupensRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        toggleRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogRecyclerView();

            }
        });

        /////////Coupen Dialog
        coupenRedemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkCoupenPriceDialog.show();

            }
        });


    }

    public static void showDialogRecyclerView() {

        if(coupensRecyclerView.getVisibility() == View.GONE) {
            coupensRecyclerView.setVisibility(View.VISIBLE);
            selectedCoupen.setVisibility(View.GONE);
        } else {
            coupensRecyclerView.setVisibility(View.GONE);
            selectedCoupen.setVisibility(View.VISIBLE);
        }

    }

    ////////rating star
    private void setRating(int starPosition) {
        for(int x = 0; x < rateNowContainer.getChildCount(); x++) {
            ImageView starBtn = (ImageView) rateNowContainer.getChildAt(x);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if(x <= starPosition) {
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }

    }
    ////////rating star

    private void initWidgets() {
        productImageViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewpager_indicator);
        addToWishListBtn = findViewById(R.id.add_to_wish_list_button);
        productDetailsViewPager = findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = findViewById(R.id.product_detail_tab_layout);
        rateNowContainer = findViewById(R.id.rate_now_container);
        buyNowBtn = findViewById(R.id.buy_now_btn);
        coupenRedemBtn = findViewById(R.id.coupon_redemption_button);
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
                Intent cartIntent = new Intent(ProductDetailActivity.this, MainActivity.class);
                showCart = true;
                startActivity(cartIntent);
                break;

            case android.R.id.home:

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
