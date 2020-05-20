package com.codermonkeys.sampleapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.codermonkeys.sampleapp.DeliveryActivity;
import com.codermonkeys.sampleapp.MyAddressActivity;
import com.codermonkeys.sampleapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    //Ui Component's
    private Button viewAllAddressBtn;

    //var's
    public static final int MANAGE_ADDRESS = 1;

    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        viewAllAddressBtn = view.findViewById(R.id.view_all_addresses_btn);
        viewAllAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAddressIntent = new Intent(view.getContext(), MyAddressActivity.class);
                myAddressIntent.putExtra("MODE", MANAGE_ADDRESS);
                startActivity(myAddressIntent);
            }
        });

        return view;
    }
}
