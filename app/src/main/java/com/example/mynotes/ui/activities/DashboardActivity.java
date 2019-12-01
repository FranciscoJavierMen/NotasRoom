package com.example.mynotes.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mynotes.R;
import com.example.mynotes.ui.fragments.FragmentNota;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class DashboardActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private FragmentNota fragmentNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        initFragments();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    private void initFragments(){
        fragmentNota = new FragmentNota();
        addFragment(fragmentNota);
    }

    private void setFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    private void addFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navigation_notes:
                getSupportActionBar().setTitle("Notas");
                setFragment(fragmentNota);
                return true;
            case R.id.navigation_favorites:
                return true;
            case R.id.navigation_account:
                return true;
        }
        return false;
    }
}
