package com.memwall.www.memwall;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by james_000 on 4/24/2016.
 */
public class MainFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_main, container, false);

        FloatingActionButton myFab = (FloatingActionButton) rootView.findViewById(R.id.picture);
        myFab.setOnClickListener(this);
        FloatingActionButton myFab2 = (FloatingActionButton) rootView.findViewById(R.id.video);
        myFab2.setOnClickListener(this);
        FloatingActionButton myFab3 = (FloatingActionButton) rootView.findViewById(R.id.text);
        myFab3.setOnClickListener(this);
        return rootView;
    }

            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.video:
                        ;
                        break;
                    case R.id.text:
                        ;
                        break;
                    case R.id.picture:
                        ;
                        break;
                }
            }



    public static MainFragment newInstance(String text) {

        MainFragment main = new MainFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        main.setArguments(b);

        return main;
    }

}
