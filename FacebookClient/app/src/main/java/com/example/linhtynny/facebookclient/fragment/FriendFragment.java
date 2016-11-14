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

public class FriendFragment extends Fragment {
    private static final String TAG = "NotificationFrag";

    public FriendFragment() {

    }

    public static FriendFragment newInstance(String chosen) {
        Bundle args = new Bundle();
        args.putString("chosen", chosen);

        FriendFragment friendfragment = new FriendFragment();
        friendfragment.setArguments(args);


        return friendfragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceStase) {
        View v = new View(getContext());
        v = inflater.inflate(R.layout.fragment_friend, container2, false);

        return v;
    }
}
