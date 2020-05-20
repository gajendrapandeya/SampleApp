package com.codermonkeys.sampleapp.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
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
    private RecyclerView testing;


    //var's
    private CategoryAdapter categoryAdapter;
    private List<CategoryModel> categoryModelList;

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
        testing = view.findViewById(R.id.home_page_recycler_view);
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




}
