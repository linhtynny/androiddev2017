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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Timeline extends AppCompatActivity {
    private ShareDialog mShareDialog;
    private String left;
    private String right;
    private String centre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("jsondata");

        JSONArray timelinedata;
        ArrayList<String> feed = new ArrayList<String>();


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
            for (int l=0; l < timelinedata.length(); l++) {
                feed.add(timelinedata.getJSONObject(l).getString("story"));
                feed.add(timelinedata.getJSONObject(l).getString("message"));
                feed.add(timelinedata.getJSONObject(l).getString("created_time"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, feed); // simple textview for list item
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

//        List<ThreeStrings> threeStringsList = new ArrayList<>();
//        ThreeStrings threeStrings = new ThreeStrings("a", "b", "c");
//        threeStringsList.add(threeStrings);
//
//
//        ListView llistView = (ListView)findViewById(R.id.listView);
//        ThreeVerticalTextViewsAdapter threeHorizontalTextViewsAdapter = new ThreeVerticalTextViewsAdapter(this, R.layout.three_vertical_text_views_layout, threeStringsList);
//        llistView.setAdapter(threeHorizontalTextViewsAdapter);


//
// get data from the table by the ListAdapter

    }


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
        Intent login = new Intent(Timeline.this, LoginActivity.class);
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
                        Intent intent = new Intent(Timeline.this,FriendsList.class);
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
                        Intent intent = new Intent(Timeline.this,Timeline.class);
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
                        Intent intent = new Intent(Timeline.this,Newsfeed.class);
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
