package com.codermonkeys.sampleapp.resources;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

public class DBqueries {

    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<CategoryModel> categoryModelList = new ArrayList<>();
    public static List<HomePageModel> homePageModelList = new ArrayList<>();

    public static void loadCategories(final CategoryAdapter categoryAdapter, final Context context) {

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
                    Toast.makeText(context, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public static void loadFragmentData(final HomePageAdapter adapter, final Context context) {

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

                                    List<HorizontalProductScrollModel> gridLayoutModelList = new ArrayList<>();
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        gridLayoutModelList.add(new HorizontalProductScrollModel(Objects.requireNonNull(documentSnapshot.get("product_ID_" + x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_image_" + x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_title_" + x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle_" + x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_price_" + x)).toString()));
                                    }
                                    homePageModelList.add(new HomePageModel(3
                                            , Objects.requireNonNull(documentSnapshot.get("layout_title")).toString()
                                            , Objects.requireNonNull(documentSnapshot.get("layout_background")).toString(),
                                            gridLayoutModelList));
                                }

                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}