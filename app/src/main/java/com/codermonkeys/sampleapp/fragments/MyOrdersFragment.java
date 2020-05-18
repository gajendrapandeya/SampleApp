package com.codermonkeys.sampleapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codermonkeys.sampleapp.R;
import com.codermonkeys.sampleapp.adapters.MyOrderAdapter;
import com.codermonkeys.sampleapp.models.MyOrderItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {

    //Widget component's
    private RecyclerView myOrderRecyclerView;

    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        myOrderRecyclerView = view.findViewById(R.id.my_orders_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myOrderRecyclerView.setLayoutManager(layoutManager);

        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.product_image, "Pixel 2a", "Delivered On Sun, 16th jan 2020", 2));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.a2, " Mi A2 lit", "Delivered On Sun, 16th jan 2020", 1));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.product_image, "Pixel 2a", "Cancelled", 0));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.oppo, "Oppo f7", "Delivered On Sun, 16th jan 2020", 4));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.j7, "Samsung Galaxy J7 ", "Cancelled", 3));

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrderRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();

        return view;
    }
}
