package com.example.zkropotkine.myapplication;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AndroidFirstApp extends Activity implements TabListener {
    private String[] tabNames = {"Name", "Last Name", "Age","Image",  "Main"};
    private String name = null;
    private String lastName = null;
    private String age = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (int i = 1; i <= 5; i++) {
            Tab tab = actionBar.newTab();
            tab.setText(tabNames[i - 1]);
            tab.setTabListener(this);
            actionBar.addTab(tab);
        }

        actionBar.setSelectedNavigationItem(4);
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Fragment fragment = null;
        TabFragment tabFragment = null;

        tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        int colorResId = 0;

        if (tab.getPosition() == 0) {
            colorResId = R.color.color1;
            lastName = null;
            age = null;
        } else if (tab.getPosition() == 1) {
            name = null;
            age = null;
            //imgResId = getResources().getIdentifier("parrot", "drawable", "com.javapapers.android.androidtablayout.app");
            colorResId = R.color.color2;
        } else if (tab.getPosition() == 2) {
            name = null;
            lastName = null;
            //imgResId = getResources().getIdentifier("cock", "drawable", "com.javapapers.android.androidtablayout.app");
            colorResId = R.color.color3;
        } else if (tab.getPosition() == 3) {
            name = null;
            lastName = null;
            age = null;
            //imgResId = getResources().getIdentifier("cock", "drawable", "com.javapapers.android.androidtablayout.app");
            colorResId = R.color.color1;
        }

        bundle.putString("name", name);
        bundle.putString("lastName", lastName);
        bundle.putString("age", age);
        bundle.putInt("color", colorResId);
        bundle.putInt("tabNum", tab.getPosition());

        tabFragment.setArguments(bundle);

        ft.replace(android.R.id.content, tabFragment);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        TextView nameText = (TextView) findViewById(R.id.editText);
        TextView lastNameText = (TextView) findViewById(R.id.editText2);
        TextView ageText = (TextView) findViewById(R.id.editText3);


        name = nameText.getText().toString();
        lastName = lastNameText.getText().toString();
        age = ageText.getText().toString();
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
}