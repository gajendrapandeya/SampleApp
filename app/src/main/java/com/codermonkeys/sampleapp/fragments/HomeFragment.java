package com.codermonkeys.sampleapp.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.codermonkeys.sampleapp.R;
import com.codermonkeys.sampleapp.adapters.CategoryAdapter;
import com.codermonkeys.sampleapp.adapters.GridProductLayoutAdapter;
import com.codermonkeys.sampleapp.adapters.HomePageAdapter;
import com.codermonkeys.sampleapp.adapters.HorizontalProductScrollAdapter;
import com.codermonkeys.sampleapp.adapters.SliderAdapter;
import com.codermonkeys.sampleapp.models.CategoryModel;
import com.codermonkeys.sampleapp.models.HomePageModel;
import com.codermonkeys.sampleapp.models.HorizontalProductScrollModel;
import com.codermonkeys.sampleapp.models.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //Widget's
    private RecyclerView categoryRecyclerView;


    //var's
    private CategoryAdapter categoryAdapter;
    private List<CategoryModel> categoryModelList;

    /////////Banner Slider
    private ViewPager bannerSliderViewPager;
    private SliderAdapter sliderAdapter;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    private final long DELAY_TIME = 3000;
    private final long PERIOD_TIME = 3000;

    ///////Banner Slider

    //////Strip Ad

    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;
    //////Strip Ad

    /////Horizontal Product Layout

    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllButton;
    private RecyclerView horizontalRecyclerView;

    /////Horizontal Product Layout


    public HomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryModelList = new ArrayList<>();
        categoryModelList.add(new CategoryModel("Link", "Home"));
        categoryModelList.add(new CategoryModel("Link", "Electronics"));
        categoryModelList.add(new CategoryModel("Link", "Appliances"));
        categoryModelList.add(new CategoryModel("Link", "Furniture"));
        categoryModelList.add(new CategoryModel("Link", "Fashion"));
        categoryModelList.add(new CategoryModel("Link", "Toys"));
        categoryModelList.add(new CategoryModel("Link", "Sports"));
        categoryModelList.add(new CategoryModel("Link", "Wall Arts"));
        categoryModelList.add(new CategoryModel("Link", "Books"));
        categoryModelList.add(new CategoryModel("Link", "Shoes"));


        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        /////////Banner Slider

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_viewpager);
        sliderModelList = new ArrayList<SliderModel>();

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

        sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);
        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if(state == ViewPager.SCROLL_STATE_IDLE) {
                    pageLooper();
                }
            }
        };

        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideShow();
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    startBannerSlideShow();
                }

                return false;
            }
        });
        ///////Banner Slider

        //////Strip Ad

        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.banner);
        stripAdContainer.setBackgroundColor(Color.parseColor("#ffffff"));

        //////Strip Ad


        /////Horizontal Product Layout

        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllButton = view.findViewById(R.id.horizontal_scroll_layout_button);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recycler_view);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mbl, "Redmi Note 5A", "SD 625 Processor", "Rs.5,999"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.oppo, "Oppo F9", "SD 760 Processor", "Rs.25,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.a2, "Mi A2 Lite", "SD 631 Processor", "Rs.8,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.j7, "Samsung Galaxy j7", "SD 625 Processor", "Rs.5,999"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.htc, "HTC", "SD 625 Processor", "Rs.5,999"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.windows, "Windows", "SD 625 Processor", "Rs.5,999"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.a2, "Mi A2", "SD 625 Processor", "Rs.5,999"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mbl, "Redmi Note 5A", "SD 625 Processor", "Rs.5,999"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);
        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();

        /////Horizontal Product Layout


        /////Grid Product Layout

        TextView gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        Button gridLayoutViewAllButton = view.findViewById(R.id.grid_product_layout_view_all_button);
        GridView gridView = view.findViewById(R.id.grid_product_layout_grid_view);

        gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));
        /////Grid Product Layout


        ///////////////MultiLay RecyclerView
        RecyclerView testing = view.findViewById(R.id.testing);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(RecyclerView.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.mbl, "#000000"));
        homePageModelList.add(new HomePageModel(2,"Deals of the day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.mbl, "#000000"));
        homePageModelList.add(new HomePageModel(3,"Deals of the day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deals of the day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(0, sliderModelList));


        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ///////////////MultiLay RecyclerView

        return view;
    }

    /////////Banner Slider
    private void pageLooper() {

        //forward banner
        if(currentPage == sliderModelList.size() - 2) {
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }

        //backward banner scroll
        if(currentPage == 1) {
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }

    }

    private void startBannerSlideShow() {

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {

                if(currentPage >= sliderModelList.size()) {
                    currentPage = 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    private void stopBannerSlideShow() {
        timer.cancel();
    }

    /////Banner Slider


}
