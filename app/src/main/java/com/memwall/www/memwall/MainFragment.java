package com.memwall.www.memwall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by james_000 on 4/24/2016.
 */
public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_main, container, false);

        return rootView;

    }


    public static MainFragment newInstance(String text) {

        MainFragment main = new MainFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        main.setArguments(b);

        return main;
    }

}
