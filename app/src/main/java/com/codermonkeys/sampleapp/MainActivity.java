package com.codermonkeys.sampleapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.codermonkeys.sampleapp.fragments.HomeFragment;
import com.codermonkeys.sampleapp.fragments.MyCartFragment;
import com.codermonkeys.sampleapp.fragments.MyOrdersFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Widget component's
    private DrawerLayout drawer;
    private FrameLayout frameLayout;
    private ImageView actionBarLogo;

    //var's
   private NavigationView navigationView;
   public static final int HOME_FRAGMENT = 0;
   public static final int CART_FRAGMENT = 1;
   public static final int ORDER_FRAGMENT = 2;
   private static int currentFragment = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                break;

            case R.id.nav_my_cart:
                gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
                break;

            case R.id.nav_my_wishlist:
                break;

            case R.id.nav_my_profile:
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
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }
}
