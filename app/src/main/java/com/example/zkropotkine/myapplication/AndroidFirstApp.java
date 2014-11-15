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

    List<Fragment> tabFragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (int i=1; i <= 4; i++) {
            Tab tab = actionBar.newTab();
            tab.setText("Tab " + i);
            tab.setTabListener(this);
            actionBar.addTab(tab);
        }
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Fragment fragment = null;
        TabFragment tabFragment = null;

        if (tabFragmentList.size() > tab.getPosition()) {
            fragment = tabFragmentList.get(tab.getPosition());
        }

        if (fragment == null) {
            tabFragment = new TabFragment();
            Bundle bundle = new Bundle();
            int imgResId = 0;
            int colorResId = 0;
            if(tab.getPosition()==0){
                TextView myText = (TextView) findViewById(R.id.textView);

                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(50); //You can manage the time of the blink with this parameter
                anim.setStartOffset(20);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);
                myText.startAnimation(anim);
                //imgResId = getResources().getIdentifier("duck", "drawable","com.javapapers.android.androidtablayout.app");
                colorResId = R.color.color1;
            } else if (tab.getPosition()==1){
                //imgResId = getResources().getIdentifier("parrot", "drawable", "com.javapapers.android.androidtablayout.app");
                colorResId = R.color.color2;
            } else if(tab.getPosition()==2){
                //imgResId = getResources().getIdentifier("cock", "drawable", "com.javapapers.android.androidtablayout.app");
                colorResId = R.color.color3;
            }
            //bundle.putInt("image", imgResId);
            bundle.putInt("color", colorResId);
            tabFragment.setArguments(bundle);
            tabFragmentList.add(tabFragment);
        }
        else {
            tabFragment = (TabFragment) fragment;
        }
        ft.replace(android.R.id.content, tabFragment);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        if (tabFragmentList.size() > tab.getPosition()) {
            ft.remove(tabFragmentList.get(tab.getPosition()));
        }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {  }
}