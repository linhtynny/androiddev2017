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

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFrag";

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance(String chosen) {
        Bundle args = new Bundle();
        args.putString("chosen", chosen);

        ProfileFragment profilefragment = new ProfileFragment();
        profilefragment.setArguments(args);


        return profilefragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceStase) {
        View v = new View(getContext());
        v = inflater.inflate(R.layout.test, container2, false);

        return v;
    }
}
