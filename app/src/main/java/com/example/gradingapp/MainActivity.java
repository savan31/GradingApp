package com.example.gradingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gradingapp.fragments.FragmentGradeEntry;
import com.example.gradingapp.fragments.FragmentSearch;
import com.example.gradingapp.fragments.FragmentViewGrade;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//binding and set up fragment first
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,0,0);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpNavigationDrawer();
//set up fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout, new FragmentGradeEntry());
        fragmentTransaction.commit();
    }


//set up navigation drawer
    private void setUpNavigationDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                int studentId = menuItem.getItemId();
                //shows Grade entry page if selected
                if(studentId == R.id.nav_gradeEntry)
                {
                    fragment = new FragmentGradeEntry();
                }
                //shows View grade page if selected
                else if (studentId == R.id.nav_viewGrades)
                {
                    fragment = new FragmentViewGrade();
                }
                //shows Search page if selected
                else if (studentId == R.id.nav_search)
                {
                    fragment = new FragmentSearch();
                }

                Toast.makeText(getApplicationContext(),menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                if (fragment != null)
                {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace (R.id.frameLayout, fragment);
                    transaction.commit();
                    drawerLayout.closeDrawers();
                    return true;
                }

                return false;
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}