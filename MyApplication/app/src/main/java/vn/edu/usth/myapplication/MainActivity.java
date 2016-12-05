package vn.edu.usth.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;


import vn.edu.usth.myapplication.fragment.FriendFragment;
//import vn.edu.usth.myapplication.fragment.LoginFragment;
import vn.edu.usth.myapplication.fragment.NewsfeedFragment;
import vn.edu.usth.myapplication.fragment.NotificationFragment;
import vn.edu.usth.myapplication.fragment.ProfileFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ShareDialog mShareDialog;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        PagerAdapter adapter = new HomeFragmentPagerAdapter(
//        getSupportFragmentManager());


//        ViewPager pager = (ViewPager) findViewById(R.id.container2);
//        pager.setOffscreenPageLimit(4);
//        pager.setAdapter(adapter);
//
//        TabLayout tableLayout = (TabLayout) findViewById(R.id.container1);
//        tableLayout.setupWithViewPager(pager);

//        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerList = (ListView) findViewById(R.id.left_drawer);
//
//        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[3];
//
//        drawerItem[0] = new ObjectDrawerItem(R.drawable.acc, "Timeline");
//        drawerItem[1] = new ObjectDrawerItem(R.drawable.nf, "Newsfeed");
//        drawerItem[2] = new ObjectDrawerItem(R.drawable.follow, "Friendlist");
//
//        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
//        mDrawerList.setAdapter(adapter);
//        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        Bundle inBundle = getIntent().getExtras();
        String name = inBundle.get("name").toString();
        String surname = inBundle.get("surname").toString();
        String imageUrl = inBundle.get("imageUrl").toString();


        TextView nameView = (TextView)findViewById(R.id.nameAndSurname);
        nameView.setText(""+name+" "+surname);

        mShareDialog = new ShareDialog(this);
        new DownloadImage((ImageView) findViewById(R.id.profileImage)).execute(imageUrl);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent content = new ShareLinkContent.Builder().build();
                mShareDialog.show(content);
            }
        });
        getProfileInformation();
    }


//    class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
//        private final int PAGE_COUNT = 4;
//        private String location[] = new String[]{"NewsFeed", "Friend", "Notification", "Message", "Profile"};
//
//        public HomeFragmentPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public int getCount() {
//            return (location.length); // number of pages for a ViewPager
//        }
//
//        @Override
//        public Fragment getItem(int page) {
////                return new NotificationFragment();
////                return new InboxFragment();
////omeFrag
//            switch (page) {
//                case 0:
////                    return NewsfeedFragment.newInstance("Newsfeed");
//                    return new NewsfeedFragment();
//                case 1:
////                    return FriendFragment.newInstance("Friend");
//                    return new FriendFragment();
//                case 2:
////                    return NotificationFragment.newInstance("Notification");
//                    return new NotificationFragment();
//                case 3:
////                    return ProfileFragment.newInstance("Profile");
//                    return new ProfileFragment();
//            }
//            return new ProfileFragment(); // failsafe
//
//        }
//
//        @Override
//        public CharSequence getPageTitle(int page) {
//            // returns a tab title corresponding to the specified page
//            return location[page];
//        }
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.friendlistbutton) {
            getFriendlist();
            return true;
        }
        if (id == R.id.timelinebutton) {
            getTimelinePosts();
            return true;
        }
        if (id == R.id.action_settings) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private class DrawerItemClickListener implements ListView.OnItemClickListener {
//
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            selectItem(position);
//        }
//
//    }
//
//    private void selectItem(int position) {
//
//
//        switch (position) {
//            case 0:
//                logout();
//                break;
//            case 1:
//                logout();
//                break  ;
//            case 2:
//                getFriendlist();
//                break;
//
//            default:
//                break;
//        }
//
//
//        mDrawerList.setItemChecked(position, true);
//        mDrawerList.setSelection(position);
//        getActionBar().setTitle(mNavigationDrawerItemTitles[position]);
//        mDrawerLayout.closeDrawer(mDrawerList);
//
//    }

    public void logout(){
        LoginManager.getInstance().logOut();
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }




    public void getFriendlist() {
        GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Intent intent = new Intent(MainActivity.this,FriendsList.class);
                        try {
                            JSONArray rawName = response.getJSONObject().getJSONArray("data");
                            intent.putExtra("jsondata", rawName.toString());
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();

    }

    public void getTimelinePosts() {
        GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/feed",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Intent intent = new Intent(MainActivity.this,Timeline.class);
                        try {
                            JSONArray data = response.getJSONObject().getJSONArray("data");
                            intent.putExtra("jsondata", data.toString());
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();

    }

    public void getProfileInformation() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        final JSONObject jsonObject = response.getJSONObject();
                        try{
                            String relationship_status = jsonObject.getString("relationship_status");
                            TextView relationship_statusText = (TextView)findViewById(R.id.relationship_status);
                            relationship_statusText.setText(""+relationship_status);

                            JSONObject hometownobject = jsonObject.getJSONObject("hometown");
                            String hometown = hometownobject.getString("name");
                            TextView hometownText = (TextView)findViewById(R.id.hometown);
                            hometownText.setText(""+hometown);

                            String birthday = jsonObject.getString("birthday");
                            TextView birthdayText = (TextView)findViewById(R.id.birthday);
                            birthdayText.setText(""+birthday);
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "relationship_status,hometown{name},birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }


}
