package com.example.linhtynny.facebookclient;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.linhtynny.facebookclient.fragment.FriendFragment;
import com.example.linhtynny.facebookclient.fragment.InboxFragment;
import com.example.linhtynny.facebookclient.fragment.LoginFragment;
import com.example.linhtynny.facebookclient.fragment.NewsfeedFragment;
import com.example.linhtynny.facebookclient.fragment.NotificationFragment;
import com.example.linhtynny.facebookclient.fragment.ProfileFragment;

public class FacebookActivity extends AppCompatActivity {
    static CharSequence content;

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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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
            return new LoginFragment(); // failsafe

            }

            @Override
            public CharSequence getPageTitle(int page) {
                // returns a tab title corresponding to the specified page
                return location[page];
            }
    }

    final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Context context = getApplicationContext();
            content = msg.getData().getString("server_response");
            Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            toast.show();
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_facebook, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
              Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
// do something heavy in the new thread.
// don't access UI Views here.
                Bundle bundle = new Bundle();
                bundle.putString("server_response", "Searching");
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        });
        t.start();
                break;

            case R.id.action_logout:
                Intent intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                break;

            case R.id.action_setting:
                Intent intent1 = new Intent(this, SettingActivity.class);
                this.startActivity(intent1);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
