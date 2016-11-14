package com.example.linhtynny.facebookclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linhtynny.facebookclient.R;

/**
 * Created by linhtynny on 15/11/2016.
 */
public class InboxFragment extends Fragment {
    private static final String TAG = "InboxFrag";

    public InboxFragment() {

    }

    public static InboxFragment newInstance(String chosen) {
        Bundle args = new Bundle();
        args.putString("chosen", chosen);

        InboxFragment inbfragment = new InboxFragment();
        inbfragment.setArguments(args);


        return inbfragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceStase) {
        View v = new View(getContext());
        v = inflater.inflate(R.layout.fragment_inbox, container2, false);

        return v;
    }
}
