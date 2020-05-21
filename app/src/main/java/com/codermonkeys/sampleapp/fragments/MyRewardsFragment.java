package com.codermonkeys.sampleapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codermonkeys.sampleapp.R;
import com.codermonkeys.sampleapp.adapters.MyRewardsAdapter;
import com.codermonkeys.sampleapp.models.RewardsModel;

import java.util.ArrayList;
import java.util.List;


public class MyRewardsFragment extends Fragment {

    private RecyclerView rewardsRecyclerView;
    private View mainView;

    public MyRewardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_my_rewards, container, false);


        rewardsRecyclerView = mainView.findViewById(R.id.my_rewards_recycler_view);

        getActivity().setTitle("My Reward");

        LinearLayoutManager layoutManager = new LinearLayoutManager(mainView.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rewardsRecyclerView.setLayoutManager(layoutManager);

        List<RewardsModel> rewardsModelList = new ArrayList<>();
        rewardsModelList.add(new RewardsModel("Cashback", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Discount", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Buy 1 get 1 free", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Cashback", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Discount", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Cashback", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));
        rewardsModelList.add(new RewardsModel("Buy 1 get 3 free", "Till 2nd, June 2020", "GET 20% CASHBACK ON ANY PRODUCT ABOVE RS.2000/-"));

        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardsModelList, false);
        rewardsRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        return mainView;
    }
}
