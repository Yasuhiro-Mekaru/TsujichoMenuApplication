package com.example.tsujichomenuapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            EnterFragment enterFragment = new EnterFragment();
            fragmentTransaction.replace(R.id.mainFragment, enterFragment);
            fragmentTransaction.commit();
        }
    }


    //Todo Asynctasc class
    //Get DB data

    //Intent Bundle
}
