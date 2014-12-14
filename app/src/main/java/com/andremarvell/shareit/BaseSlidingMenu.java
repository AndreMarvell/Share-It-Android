package com.andremarvell.shareit;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.andremarvell.shareit.adapter.MenuAdapter;
import com.andremarvell.shareit.adapter.SlidingMenuAdapter;
import com.andremarvell.shareit.fragments.Home;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Marvell
 */
public class BaseSlidingMenu extends SherlockFragmentActivity {
    protected SlidingMenu sMenu;
    protected boolean layoutIsLoaded;

    Bundle bdle;

    private FragmentTabHost mTabHost;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bdle = savedInstanceState;

        layoutIsLoaded = false;

        //On configure le slidingMenu
        sMenu = new SlidingMenu(this);
        sMenu.setMode(SlidingMenu.LEFT);
        sMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        sMenu.setShadowWidthRes(R.dimen.shadow_width);
        sMenu.setShadowDrawable(R.drawable.shadow);
        sMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sMenu.setFadeDegree(0.35f);
        sMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        sMenu.setMenu(R.layout.activity_menu);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowTitleEnabled(false);
        bar.setDisplayShowCustomEnabled(true);
        bar.setCustomView(R.layout.custom_actionbar);

        ((TextView) bar.getCustomView().findViewById(R.id.actionBarTitle)).setText(R.string.app_name);


        this.loadActivity();


    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            sMenu.toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public void toggleSlidingMenu() {
        sMenu.toggle();
    }

    private Timer timer;

    private TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            if(isOnline()){
                stopCheckingConnexion();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadActivity();
                    }
                });
            }

        }
    };

    public void startCheckingConnexion() {
        if(timer != null) {
            return;
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 5000, 10000);
    }

    public void stopCheckingConnexion() {
        timer.cancel();
        timer = null;
    }

    public void loadActivity(){
        if(!isOnline()){
            layoutIsLoaded = false;
            setContentView(R.layout.activity_offline);

            sMenu.getMenu().findViewById(R.id.imageViewConnexionLostLoader).setVisibility(View.VISIBLE);
            sMenu.getMenu().findViewById(R.id.textViewConnexionLostMessage).setVisibility(View.VISIBLE);
            startCheckingConnexion();

        }else {

            setContentView(R.layout.fragment_container);
            if (bdle == null) {
                Intent intent = getIntent();
                if(intent.getBooleanExtra("alerte", false)){
                    /*getSupportFragmentManager().beginTransaction()
                            .add(R.id.container, Race.newInstance(intent))
                            .commit();*/
                }
                else{
                    try{
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.container, new Home())
                                .commitAllowingStateLoss();
                    }catch (IllegalStateException e){
                        e.printStackTrace();
                    }
                }


            }
            layoutIsLoaded = true;


            ((ListView)sMenu.getMenu().findViewById(R.id.menu_listview)).setAdapter(new SlidingMenuAdapter(this));


        }


    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        try {
            ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.actionBarTitle)).setText(title);
        } catch (Exception e) {
            System.err.println("Exception Sur le titre du fragment "+title+": "+ e.getMessage());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }



}
