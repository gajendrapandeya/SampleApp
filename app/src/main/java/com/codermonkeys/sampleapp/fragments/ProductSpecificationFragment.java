package com.codermonkeys.sampleapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codermonkeys.sampleapp.R;
import com.codermonkeys.sampleapp.adapters.ProductSpecificationAdapter;
import com.codermonkeys.sampleapp.models.ProductSpecificationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductSpecificationFragment extends Fragment {

    //Widget Components
    private RecyclerView productSpecificationRecyclerView;

    public ProductSpecificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_specification, container, false);

        productSpecificationRecyclerView = view.findViewById(R.id.product_specification_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        productSpecificationRecyclerView.setLayoutManager(linearLayoutManager);

        List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();
        productSpecificationModelList.add(new ProductSpecificationModel("Ram", "8GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("Camera", "32MP"));
        productSpecificationModelList.add(new ProductSpecificationModel("Storage", "64GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("Processor", "SD-625"));
        productSpecificationModelList.add(new ProductSpecificationModel("Ram", "8GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("Camera", "32MP"));
        productSpecificationModelList.add(new ProductSpecificationModel("Storage", "64GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("Processor", "SD-625"));

        ProductSpecificationAdapter productSpecificationAdapter = new ProductSpecificationAdapter(productSpecificationModelList);
        productSpecificationRecyclerView.setAdapter(productSpecificationAdapter);
        productSpecificationAdapter.notifyDataSetChanged();

        return view;
    }
}
