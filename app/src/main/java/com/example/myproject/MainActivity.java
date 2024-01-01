package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnViewobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnViewobj=findViewById(R.id.bnView);

        bnViewobj.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.home){
                    loadFrag(new HomeFFragment(), false);
                }
                else if(id==R.id.score) {
                    loadFrag(new scoreFragment(),false);
                }
                else {
                    loadFrag(new profileFragment(), false);
                }
                return true;
            }
        });


        bnViewobj.setSelectedItemId(R.id.home);

    }
    public void loadFrag(Fragment fragment, boolean flag)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if (flag)
        ft.add(R.id.container,new HomeFFragment());
        else
            ft.replace(R.id.container,fragment);
        ft.commit();
    }
}