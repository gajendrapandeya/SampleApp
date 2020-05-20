package com.codermonkeys.sampleapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.codermonkeys.sampleapp.fragments.HomeFragment;
import com.codermonkeys.sampleapp.fragments.MyAccountFragment;
import com.codermonkeys.sampleapp.fragments.MyCartFragment;
import com.codermonkeys.sampleapp.fragments.MyOrdersFragment;
import com.codermonkeys.sampleapp.fragments.MyRewardsFragment;
import com.codermonkeys.sampleapp.fragments.MyWishListFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Widget component's
    private DrawerLayout drawer;
    private FrameLayout frameLayout;
    private ImageView actionBarLogo;
    private Toolbar toolbar;

    //var's
   private NavigationView navigationView;
   public static final int HOME_FRAGMENT = 0;
   public static final int CART_FRAGMENT = 1;
   public static final int ORDER_FRAGMENT = 2;
   public static final int WISH_LIST_FRAGMENT = 3;
   public static final int REWARDS_FRAGMENT = 4;
   public static final int ACCOUNT_FRAGMENT = 5;
   private static int currentFragment = -1;
   private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        frameLayout = findViewById(R.id.main_frame_layout);
        actionBarLogo = findViewById(R.id.actionbar_logo);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.open_drawer,R.string.close_drawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        setFragment(new HomeFragment(), HOME_FRAGMENT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(currentFragment == HOME_FRAGMENT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_search_icon:
                //Todo: search
                break;

            case R.id.main_cart_icon:
                //todo: notification system
                gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
                break;

            case R.id.main_notification_icon:
                //todo: cart system
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_my_mall:
                actionBarLogo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                setFragment(new HomeFragment(), HOME_FRAGMENT);
                break;

            case R.id.nav_my_orders:
                gotoFragment("My Orders", new MyOrdersFragment(), ORDER_FRAGMENT);
                break;

            case R.id.nav_my_rewards:
                gotoFragment("My Rewards", new MyRewardsFragment(), REWARDS_FRAGMENT);
                break;

            case R.id.nav_my_cart:
                gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
                break;

            case R.id.nav_my_wishlist:
                gotoFragment("My WishList", new MyWishListFragment(), WISH_LIST_FRAGMENT);
                break;

            case R.id.nav_my_profile:
                gotoFragment("My Account", new MyAccountFragment(), ACCOUNT_FRAGMENT);
                break;

            case R.id.nav_sign_out:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {

        actionBarLogo.setVisibility(View.GONE);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment,fragmentNo);
        if(fragmentNo == CART_FRAGMENT) {
            navigationView.getMenu().getItem(3).setChecked(true);
        }

    }
    private void setFragment(Fragment fragment, int fragmentNo) {

        if(fragmentNo != currentFragment) {

            if(fragmentNo == REWARDS_FRAGMENT) {
                window.setStatusBarColor(getResources().getColor(R.color.colorViolet));
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorViolet));

            } else {
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }

            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(currentFragment == HOME_FRAGMENT) {
                currentFragment = -1;
                super.onBackPressed();
            } else {
                actionBarLogo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                setFragment(new HomeFragment(), HOME_FRAGMENT);
                navigationView.getMenu().getItem(0).setChecked(true);
            }
        }
    }


}
