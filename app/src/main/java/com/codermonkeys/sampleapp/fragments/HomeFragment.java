package com.codermonkeys.sampleapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codermonkeys.sampleapp.R;
import com.codermonkeys.sampleapp.adapters.CategoryAdapter;
import com.codermonkeys.sampleapp.adapters.HomePageAdapter;
import com.codermonkeys.sampleapp.models.CategoryModel;
import com.codermonkeys.sampleapp.models.HomePageModel;
import com.codermonkeys.sampleapp.models.HorizontalProductScrollModel;
import com.codermonkeys.sampleapp.models.SliderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //Widget's
    private RecyclerView categoryRecyclerView;
    private RecyclerView homePageRecyclerView;


    //var's
    private CategoryAdapter categoryAdapter;
    private List<CategoryModel> categoryModelList;
    private HomePageAdapter adapter;
    private FirebaseFirestore firebaseFirestore;


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
        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        //We have created collection called categories in firebase, so we are fetching that collection and fetching in an order that is by the index
        //of the product
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        categoryModelList.add(new CategoryModel(Objects.requireNonNull(documentSnapshot.get("icon")).toString(), Objects.requireNonNull(documentSnapshot.get("categoryName")).toString()));
                    }
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


        /////Horizontal Product Layout

//        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mbl, "Redmi Note 5A", "SD 625 Processor", "Rs.5,999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.oppo, "Oppo F9", "SD 760 Processor", "Rs.25,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.a2, "Mi A2 Lite", "SD 631 Processor", "Rs.8,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.j7, "Samsung Galaxy j7", "SD 625 Processor", "Rs.5,999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.htc, "HTC", "SD 625 Processor", "Rs.5,999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.windows, "Windows", "SD 625 Processor", "Rs.5,999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.a2, "Mi A2", "SD 625 Processor", "Rs.5,999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mbl, "Redmi Note 5A", "SD 625 Processor", "Rs.5,999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mbl, "Redmi Note 5A", "SD 625 Processor", "Rs.5,999"));

        /////Horizontal Product Layout


        ///////////////MultiLay RecyclerView
        homePageRecyclerView = view.findViewById(R.id.home_page_recycler_view);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(RecyclerView.VERTICAL);
        homePageRecyclerView.setLayoutManager(testingLayoutManager);
        final List<HomePageModel> homePageModelList = new ArrayList<>();
        adapter = new HomePageAdapter(homePageModelList);
        homePageRecyclerView.setAdapter(adapter);


        firebaseFirestore.collection("CATEGORIES")
                .document("HOME")
                .collection("TOP_DEALS")
                .orderBy("index")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {


                                if (Objects.requireNonNull((long) documentSnapshot.get("view_type")) == 0) {
                                    //if view_type is 0, then we should set banner slider
                                    List<SliderModel> sliderModelList = new ArrayList<>();
                                    long no_of_banner = (long) documentSnapshot.get("no_of_banners");
                                    for (long x = 1; x < no_of_banner + 1; x++) {
                                        sliderModelList.add(new SliderModel(Objects.requireNonNull(documentSnapshot.get("banner_" + x)).toString(),
                                                Objects.requireNonNull(documentSnapshot.get("banner_" + x + "_background")).toString()));
                                    }
                                    homePageModelList.add(new HomePageModel(0, sliderModelList));


                                } else if (Objects.requireNonNull((long) documentSnapshot.get("view_type")) == 1) {
                                    //if view_type is 1, then we should set strip ad
                                    homePageModelList.add(new HomePageModel(1, Objects.requireNonNull(documentSnapshot.get("strip_ad_banner")).toString(),
                                            Objects.requireNonNull(documentSnapshot.get("background")).toString()));


                                } else if (Objects.requireNonNull((long) documentSnapshot.get("view_type")) == 2) {
                                    //if view_type is 2, then we should set horizontal recycler view
                                    List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(Objects.requireNonNull(documentSnapshot.get("product_ID_" + x)).toString()
                                        , Objects.requireNonNull(documentSnapshot.get("product_image_" + x)).toString()
                                        , Objects.requireNonNull(documentSnapshot.get("product_title_" + x)).toString()
                                        , Objects.requireNonNull(documentSnapshot.get("product_subtitle_" + x)).toString()
                                        , Objects.requireNonNull(documentSnapshot.get("product_price_" + x)).toString()));
                                    }
                                   homePageModelList.add(new HomePageModel(2
                                           , Objects.requireNonNull(documentSnapshot.get("layout_title")).toString()
                                           , Objects.requireNonNull(documentSnapshot.get("layout_background")).toString(),
                                           horizontalProductScrollModelList));

                                } else if (Objects.requireNonNull((long) documentSnapshot.get("view_type")) == 3) {
                                    //if view_type is 3, then we should set grid view
                                }

                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

        ///////////////MultiLay RecyclerView

        return view;
    }


}
