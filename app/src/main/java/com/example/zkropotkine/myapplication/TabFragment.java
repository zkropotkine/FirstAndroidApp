package com.example.zkropotkine.myapplication;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class TabFragment extends Fragment {

    private String name;
    private String lastName;
    private String age;
    private int color;
    private int tabNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        name = bundle.getString("name");
        lastName = bundle.getString("lastName");
        age = bundle.getString("age");
        color = bundle.getInt("color");
        tabNum = bundle.getInt("tabNum");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, null);
        //ImageView tabImage = (ImageView) view.findViewById(R.id.imageView);


        TextView myText = (TextView) view.findViewById(R.id.textView);

        String[] texts = {name, lastName, age};


        for (String text : texts) {
            if (text != null && tabNum != 3) {
                myText.setText(text);

                if (tabNum == 1) {
                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(1000); //You can manage the time of the blink with this parameter
                    //anim.setStartOffset(20);
                    anim.setBackgroundColor(Color.BLUE);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);

                    myText.startAnimation(anim);
                } else if (tabNum == 2) {

                    final Property<TextView, Integer> property = new Property<TextView, Integer>(int.class, "textColor") {
                        @Override
                        public Integer get(TextView object) {
                            return object.getCurrentTextColor();
                        }

                        @Override
                        public void set(TextView object, Integer value) {
                            object.setTextColor(value);
                        }
                    };

                    myText.setTextColor(Color.BLUE);

                    final ObjectAnimator animator = ObjectAnimator.ofInt(myText, property, Color.RED);
                    animator.setDuration(666L);
                    animator.setEvaluator(new ArgbEvaluator());
                    animator.setInterpolator(new DecelerateInterpolator(2));
                    animator.setRepeatCount(ObjectAnimator.INFINITE);
                    animator.setRepeatMode(ObjectAnimator.REVERSE);
                    animator.start();

                }  else if(tabNum == 4) {
                    myText.setText("");
                }

                break;
            }
            else if (tabNum == 3) {
                ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.ic_launcher);
                myText.setText("");
                break;
            }
        }

        view.setBackgroundResource(color);
        return view;
    }
}