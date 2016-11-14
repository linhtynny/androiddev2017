package com.example.linhtynny.facebookclient;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.linhtynny.facebookclient.fragment.InboxFragment;
import com.example.linhtynny.facebookclient.fragment.NotificationFragment;

public class Facebook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_activity);

        PagerAdapter adapter = new HomeFragmentPagerAdapter(
                getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.container2);
        pager.setOffscreenPageLimit(4);
        pager.setAdapter(adapter);


        TabLayout tableLayout = (TabLayout) findViewById(R.id.container1);
        tableLayout.setupWithViewPager(pager);
    }

        class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
            private final int PAGE_COUNT = 4;
            private String location[] = new String[]{"NewsFeed", "Notification", "Message", "Profile"};

            public HomeFragmentPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public int getCount() {
                return (location.length); // number of pages for a ViewPager
            }

            @Override
            public Fragment getItem(int page) {
//                return new NotificationFragment();
//                return new InboxFragment();
//
                switch (page) {
//                    case 0: return NewsfeedFragment.newInstance();
                    case 1: return NotificationFragment.newInstance("Notification");
                    case 2: return InboxFragment.newInstance("Message");
//                    case 3: return ProfileFragment.newInstance();
        }
            return new InboxFragment(); // failsafe

            }

            @Override
            public CharSequence getPageTitle(int page) {
                // returns a tab title corresponding to the specified page
                return location[page];
            }
    }
}
