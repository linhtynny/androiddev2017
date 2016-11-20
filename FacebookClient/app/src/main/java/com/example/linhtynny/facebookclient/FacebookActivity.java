package com.example.linhtynny.facebookclient;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.linhtynny.facebookclient.fragment.FriendFragment;
import com.example.linhtynny.facebookclient.fragment.InboxFragment;
import com.example.linhtynny.facebookclient.fragment.NewsfeedFragment;
import com.example.linhtynny.facebookclient.fragment.NotificationFragment;
import com.example.linhtynny.facebookclient.fragment.ProfileFragment;

public class FacebookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_activity);

        PagerAdapter adapter = new HomeFragmentPagerAdapter(
                getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.container2);
        pager.setOffscreenPageLimit(5);
        pager.setAdapter(adapter);


        TabLayout tableLayout = (TabLayout) findViewById(R.id.container1);
        tableLayout.setupWithViewPager(pager);
    }

        class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
            private final int PAGE_COUNT = 5;
            private String location[] = new String[]{"NewsFeed", "Friend", "Notification", "Message", "Profile"};

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
//omeFrag
                switch (page) {
                    case 0: return NewsfeedFragment.newInstance("Newsfeed");
                    case 1: return FriendFragment.newInstance("Friend");
                    case 2: return NotificationFragment.newInstance("Notification");
                    case 3: return InboxFragment.newInstance("Message");
                    case 4: return ProfileFragment.newInstance("Profile");
        }
            return new NewsfeedFragment(); // failsafe

            }

            @Override
            public CharSequence getPageTitle(int page) {
                // returns a tab title corresponding to the specified page
                return location[page];
            }
    }
}
