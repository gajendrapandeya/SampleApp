package com.codermonkeys.sampleapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codermonkeys.sampleapp.AddAddressActivity;
import com.codermonkeys.sampleapp.R;
import com.codermonkeys.sampleapp.adapters.CartAdapter;
import com.codermonkeys.sampleapp.models.CartItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {

    //Widget's Component's
    private RecyclerView cartItemRecyclerView;
    private Button continueBtn;

    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        cartItemRecyclerView = view.findViewById(R.id.cart_item_recyclerview);
        continueBtn = view.findViewById(R.id.cart_continue_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        cartItemRecyclerView.setLayoutManager(layoutManager);
        getActivity().setTitle("My Cart");
//        getActivity().getSu().setTitle("My Cart");
        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, R.drawable.product_image, 2, 1, "Google Pixel 3a(Black)", "Rs.9,000/-", "Rs.10,000/-", 0, 0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.product_image, 2, 1, "Google Pixel 3a(Black)", "Rs.9,000/-", "Rs.10,000/-", 0, 0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.product_image, 2, 1, "Google Pixel 3a(Black)", "Rs.9,000/-", "Rs.10,000/-", 0, 0));

        cartItemModelList.add(new CartItemModel(1, "Price (3 items)", "Rs.15,999/-", "150/-", "Rs.16,999/-", "Rs.1000/-"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemRecyclerView.setAdapter(cartAdapter);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addAddressIntent = new Intent(getContext(), AddAddressActivity.class);
                requireContext().startActivity(addAddressIntent);
            }
        });
        return view;
    }


}
