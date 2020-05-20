package com.codermonkeys.sampleapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codermonkeys.sampleapp.R;
import com.codermonkeys.sampleapp.adapters.WishListAdapter;
import com.codermonkeys.sampleapp.models.WishListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyWishListFragment extends Fragment {

    //Widget Component's
    private RecyclerView wishListRecyclerView;

    public MyWishListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_wish_list, container, false);

        getActivity().setTitle("My WishList");

        wishListRecyclerView = view.findViewById(R.id.my_wishlist_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        wishListRecyclerView.setLayoutManager(layoutManager);

        List<WishListModel> wishListModelList = new ArrayList<>();
        wishListModelList.add(new WishListModel(R.drawable.product_image, "Pixel 2a", 2, "3", 45, "RS.5,999/-", "Rs.6,999/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.a2, "Mi A2 lite", 0, "4", 78, "RS.7,999/-", "Rs.9,999/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.product_image, "Pixel 2a", 1, "3", 45, "RS.5,999/-", "Rs.6,999/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.product_image, "Pixel 2a", 2, "3", 45, "RS.5,999/-", "Rs.6,999/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.product_image, "Pixel 2a", 4, "3", 45, "RS.5,999/-", "Rs.6,999/-", "Cash on delivery"));

        WishListAdapter wishListAdapter = new WishListAdapter(wishListModelList, true);
        wishListRecyclerView.setAdapter(wishListAdapter);
        wishListAdapter.notifyDataSetChanged();
        return view;
    }
}
