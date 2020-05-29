package com.codermonkeys.sampleapp.models;

import java.util.List;

public class HomePageModel {

    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;

    private int type;
    private String backGroundColor;

    ///////Banner Slider
    private List<SliderModel> sliderModelList;

    public HomePageModel(int type, List<SliderModel> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }

    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

    ///////Banner Slider


    /////String Ad Banner

    private String resource;

    public HomePageModel(int type, String resource, String backGroundColor) {
        this.type = type;
        this.resource = resource;
        this.backGroundColor = backGroundColor;
    }

    public String  getResource() {
        return resource;
    }

    public void setResource(String  resource) {
        this.resource = resource;
    }

    public String getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    /////String Ad Banner



    private String title;
    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    /////Horizontal Product Layout
    private List<WishListModel> viewAllProductList;

    public HomePageModel(int type, String title, String backGroundColor, List<HorizontalProductScrollModel> horizontalProductScrollModelList, List<WishListModel> viewAllProductList) {
        this.type = type;
        this.title = title;
        this.backGroundColor = backGroundColor;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
        this.viewAllProductList = viewAllProductList;
    }

    public List<WishListModel> getViewAllProductList() {
        return viewAllProductList;
    }

    public void setViewAllProductList(List<WishListModel> viewAllProductList) {
        this.viewAllProductList = viewAllProductList;
    }

    /////Horizontal Product Layout


    /////Grid Product Layout
    public HomePageModel(int type, String title, String backGroundColor, List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.type = type;
        this.title = title;
        this.backGroundColor = backGroundColor;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    /////Grid Product Layout

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalProductScrollModel> getHorizontalProductScrollModelList() {
        return horizontalProductScrollModelList;
    }

    public void setHorizontalProductScrollModelList(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }




}
