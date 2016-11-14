package com.example.linhtynny.facebookclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linhtynny.facebookclient.R;

/**
 * Created by linhtynny on 14/11/2016.
 */
public class Pager extends Fragment {
    private static final String TAG = "WeatherForecastFrag";

    public Pager() {

    }

    public static Pager newInstance(String chosen) {
        Bundle args = new Bundle();
        args.putString("chosen", chosen);

        Pager fragment = new Pager();
        fragment.setArguments(args);


        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStase) {
        View v = new View(getContext());
        v = inflater.inflate(R.layout.fragment_notification, container, false);

        return v;
    }
}
