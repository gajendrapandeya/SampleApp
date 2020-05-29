package com.codermonkeys.sampleapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codermonkeys.sampleapp.R;
import com.codermonkeys.sampleapp.adapters.CategoryAdapter;
import com.codermonkeys.sampleapp.adapters.HomePageAdapter;
import com.codermonkeys.sampleapp.models.HomePageModel;


import java.util.ArrayList;

import static com.codermonkeys.sampleapp.resources.DBqueries.categoryModelList;
import static com.codermonkeys.sampleapp.resources.DBqueries.lists;
import static com.codermonkeys.sampleapp.resources.DBqueries.loadCategories;
import static com.codermonkeys.sampleapp.resources.DBqueries.loadFragmentData;
import static com.codermonkeys.sampleapp.resources.DBqueries.loadedCategoriesName;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //Widget's
    private RecyclerView categoryRecyclerView;
    private RecyclerView homePageRecyclerView;
    private ImageView noInternetConnection;


    //var's
    private CategoryAdapter categoryAdapter;
    private HomePageAdapter adapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        noInternetConnection = view.findViewById(R.id.no_internet_connection);

        //code to check the internet availability
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //if network is available
        if(networkInfo != null && networkInfo.isConnected()){
            noInternetConnection.setVisibility(View.GONE);

            categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            categoryRecyclerView.setLayoutManager(layoutManager);


            categoryAdapter = new CategoryAdapter(categoryModelList);
            categoryRecyclerView.setAdapter(categoryAdapter);


            if(categoryModelList.size() == 0) {
                loadCategories(categoryAdapter, getContext());
            } else {
                categoryAdapter.notifyDataSetChanged();
            }

            ///////////////MultiLay RecyclerView
            homePageRecyclerView = view.findViewById(R.id.home_page_recycler_view);
            LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
            testingLayoutManager.setOrientation(RecyclerView.VERTICAL);
            homePageRecyclerView.setLayoutManager(testingLayoutManager);

            if(lists.size() == 0) {
                loadedCategoriesName.add("HOME");
                lists.add(new ArrayList<HomePageModel>());
                adapter = new HomePageAdapter(lists.get(0));
                loadFragmentData(adapter, getContext(), 0, "Home");
            } else {
                adapter = new HomePageAdapter(lists.get(0));
                adapter.notifyDataSetChanged();
            }

            homePageRecyclerView.setAdapter(adapter);

            ///////////////MultiLay RecyclerView


        } else {

            Glide.with(requireContext()).load(R.drawable.no_internet).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
        }


        return view;
    }


}
