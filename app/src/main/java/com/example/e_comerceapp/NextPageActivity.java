package com.example.e_comerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

public class NextPageActivity extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_page);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Welcome To our App");
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
       drawerLayout.addDrawerListener(toggle);
       toggle.syncState();
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @SuppressLint("NonConstantResourceId")
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int itemid = item.getItemId();
               if (itemid == R.id.home) {
                   Intent intenth = new Intent(getApplicationContext(), Home.class);
                   startActivity(intenth);
                   finish();
               } else if (itemid == R.id.accountinfo) {
                   Intent intenta = new Intent(getApplicationContext(), AccountInfo.class);
                   startActivity(intenta);
                   finish();
               } else if (itemid == R.id.cart) {
                   Intent intentc = new Intent(getApplicationContext(), Cart.class);
                   startActivity(intentc);
                   finish();
               } else if (itemid == R.id.signout) {
                   Intent intents = new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intents);
                   finish();
               }

               return true;
           }
       });

    }
}