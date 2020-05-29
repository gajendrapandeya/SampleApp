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

        WishListAdapter wishListAdapter = new WishListAdapter(wishListModelList, true);
        wishListRecyclerView.setAdapter(wishListAdapter);
        wishListAdapter.notifyDataSetChanged();
        return view;
    }
}
