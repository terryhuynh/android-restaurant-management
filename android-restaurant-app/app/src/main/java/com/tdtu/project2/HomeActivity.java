package com.tdtu.project2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.tdtu.project2.fragments.MenuFoodFragment;
import com.tdtu.project2.fragments.MenuManageFragment;
import com.tdtu.project2.fragments.TableFoodFragment;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    private static final String EMPLOYEE_NAME = "username";

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homepage);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navMenu);
        toolbar = findViewById(R.id.toolbar);

        View view = navigationView.inflateHeaderView(R.layout.layout_header_navigation);
        TextView txtNavUser = view.findViewById(R.id.txtNavUsername);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra(EMPLOYEE_NAME);
        txtNavUser.setText(username);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        TableFoodFragment fragment = new TableFoodFragment();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.mnHome:
                FragmentTransaction homeTrans = fragmentManager.beginTransaction();
                TableFoodFragment tableFoodFragment = new TableFoodFragment();
                homeTrans.replace(R.id.content, tableFoodFragment);
                homeTrans.commit();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.mnManagement:
                FragmentTransaction mgrTrans = fragmentManager.beginTransaction();
                MenuManageFragment menuFragment = new MenuManageFragment();
                mgrTrans.replace(R.id.content, menuFragment);
                mgrTrans.commit();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.mnMenus:
                FragmentTransaction menuTrans = fragmentManager.beginTransaction();
                MenuFoodFragment foodFragment = new MenuFoodFragment();
                menuTrans.replace(R.id.content, foodFragment);
                menuTrans.commit();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:
                break;
        }

        return false;
    }
}