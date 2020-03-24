package com.example.version7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Chat extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        BottomNavigationView chatTabNav = (BottomNavigationView) findViewById(R.id.chat_tab);
        chatTabNav.setOnNavigationItemSelectedListener(this);

        loadFragment(new ChatFragmentContacts());

    }


    private  boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.chat_container,fragment).commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.chat_main:
                fragment = new ChatFragmentContacts();
                break;
            case R.id.chat_search:
                fragment = new ChatFragmentSearch();
                break;
            case R.id.chat_favorites:
                fragment = new ChatFragmentFav();
                break;
        }


        return loadFragment(fragment);
    }
}
