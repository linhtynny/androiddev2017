package vn.edu.usth.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.myapplication.R;


/**
 * Created by linhtynny on 15/11/2016.
 */

public class NewsfeedFragment extends Fragment {
    private static final String TAG = "NewsfeedFrag";

    public NewsfeedFragment() {

    }

    public static NewsfeedFragment newInstance(String chosen) {
        Bundle args = new Bundle();
        args.putString("chosen", chosen);

        NewsfeedFragment newsfeedfragment = new NewsfeedFragment();
        newsfeedfragment.setArguments(args);


        return newsfeedfragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceStase) {
        View v = new View(getContext());
        v = inflater.inflate(R.layout.test, container2, false);

        return v;
    }
}
