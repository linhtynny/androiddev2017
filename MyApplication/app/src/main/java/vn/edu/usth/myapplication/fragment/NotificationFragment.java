package vn.edu.usth.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.myapplication.R;


/**
 * Created by linhtynny on 14/11/2016.
 */
public class NotificationFragment extends Fragment {
    private static final String TAG = "NotificationFrag";

    public NotificationFragment() {

    }

    public static NotificationFragment newInstance(String chosen) {
        Bundle args = new Bundle();
        args.putString("chosen", chosen);

        NotificationFragment notifragment = new NotificationFragment();
        notifragment.setArguments(args);


        return notifragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceStase) {
        View v = new View(getContext());
        v = inflater.inflate(R.layout.content_main, container2, false);

        return v;
    }
}
