package com.example.linhtynny.facebookclient;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static com.example.linhtynny.facebookclient.FacebookActivity.content;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_facebook, menu);
        return true;
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

