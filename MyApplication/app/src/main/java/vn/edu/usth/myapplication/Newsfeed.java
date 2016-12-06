package vn.edu.usth.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Newsfeed extends AppCompatActivity {
    private ShareDialog mShareDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("jsondata");

        JSONArray timelinedata;
        ArrayList<String> newsfeed = new ArrayList<String>();
        List<ThreeStrings> threeStringsList = new ArrayList<>();

        mShareDialog = new ShareDialog(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent content = new ShareLinkContent.Builder().build();
                mShareDialog.show(content);
            }
        });
        try {
            timelinedata = new JSONArray(jsondata);
            for (int l = 0; l < timelinedata.length(); l++) {
//                feed.add(timelinedata.getJSONObject(l).getString("story"));
//                feed.add(timelinedata.getJSONObject(l).getString("message"));
//                feed.add(timelinedata.getJSONObject(l).getString("created_time"));

                ThreeStrings threeStrings = new ThreeStrings(timelinedata.getJSONObject(l).getString("story"), timelinedata.getJSONObject(l).getString("message"), timelinedata.getJSONObject(l).getString("created_time"));
                threeStringsList.add(threeStrings);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ThreeVerticalTextViewsAdapter threeTextViewsAdapter = new ThreeVerticalTextViewsAdapter(this, R.layout.three_vertical_text_views_layout, threeStringsList);
        ListView llistView = (ListView) findViewById(R.id.linearListview);
        llistView.setAdapter(threeTextViewsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        if (id == R.id.newsfeedButton) {
            getNewsfeed();
            return true;
        }
        if (id == R.id.action_settings) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        LoginManager.getInstance().logOut();
        Intent login = new Intent(Newsfeed.this, LoginActivity.class);
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
                        Intent intent = new Intent(Newsfeed.this,FriendsList.class);
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
                        Intent intent = new Intent(Newsfeed.this,Timeline.class);
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

    public void getNewsfeed(){
        GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/feed",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Intent intent = new Intent(Newsfeed.this,Newsfeed.class);
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


}
