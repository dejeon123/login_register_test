package com.example.version7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.app_menu);
        bottomNav.setOnNavigationItemSelectedListener(this);
        loadFragment(new FragmentSearch());
    }

    private  boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                fragment = new FragmentSearch();
                break;
            case R.id.nav_chat:
                Intent loginIntent = new Intent(MainActivity.this, Chat.class);
                startActivity(loginIntent);
                finish();
                //selectedFragment = new FragmentChat();
                break;
            case R.id.nav_web_search:
                fragment = new FragmentWebSearch();
                break;
            case R.id.nav_profile:
                fragment = new FragmentProfile();
                break;
        }

        return loadFragment(fragment);
    }


}
