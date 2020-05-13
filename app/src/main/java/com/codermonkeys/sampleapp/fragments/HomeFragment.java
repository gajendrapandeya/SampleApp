package com.codermonkeys.sampleapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codermonkeys.sampleapp.R;
import com.codermonkeys.sampleapp.adapters.CategoryAdapter;
import com.codermonkeys.sampleapp.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //Widget's
    private RecyclerView categoryRecyclerView;


    //var's
    private CategoryAdapter categoryAdapter;
    private List<CategoryModel> categoryModelList;

    public HomeFragment() {
        // Required empty public constructor
    }


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

        return view;
    }


}
