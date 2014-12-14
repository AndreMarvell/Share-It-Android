package com.andremarvell.shareit.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.andremarvell.shareit.R;
import com.andremarvell.shareit.ShareItApplication;
import com.andremarvell.shareit.imageloader.ImageLoader;


public class Home extends SherlockFragment implements SwipeRefreshLayout.OnRefreshListener {





    SwipeRefreshLayout swipeLayout;


    /**
     * Fragment gérant l'ecran d'accueil
     *
     * @return Une nouvelle instance fragment de type Home.
     */
    public static Home newInstance() {
        Home fragment = new Home();
        Bundle args = new Bundle();
/*        args.putString(ARG_DATE, dateProg);
        args.putInt(ARG_REUNION_NUMBER, reunionNumber);*/
        fragment.setArguments(args);
        return fragment;
    }
    public Home() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //On change le titre de la nav bar
        getSherlockActivity().setTitle(R.string.app_name);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_home, container, false);


        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors (
                R.color.sh_green,
                android.R.color.holo_orange_light,
                R.color.sh_green2,
                R.color.sh_red);

        // On charge le User
        ((TextView)rootView.findViewById(R.id.nom)).setText(ShareItApplication.USER.getPrenom()+" "+ShareItApplication.USER.getNom());
        (new ImageLoader(getSherlockActivity())).DisplayImage(ShareItApplication.USER.getPhoto(),(ImageView) rootView.findViewById(R.id.photo));





        return rootView;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //On change le titre de la nav bar
        getSherlockActivity().setTitle(R.string.app_name);

    }

    @Override
    public void onRefresh() {
        /*programmeReunionAdapter     = new ProgrammeReunionAdapter(getActivity(), date,reunionNumber, listView, true);
        listView.setAdapter(programmeReunionAdapter);
        if(!((BaseSlidingMenu)getSherlockActivity()).isOnline()){
            Toast t = Toast.makeText(getSherlockActivity().getApplicationContext(), "Connexion Insuffisante\nVeuillez verifier l'état de votre réseau et rafraîchir la page.", Toast.LENGTH_LONG);
            TextView v = (TextView) t.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            t.show();
        }*/

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                swipeLayout.setRefreshing(false);

            }
        }, 3000);
    }


}

