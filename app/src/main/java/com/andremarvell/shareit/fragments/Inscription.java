package com.andremarvell.shareit.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.andremarvell.shareit.R;
import com.andremarvell.shareit.ShareItApplication;
import com.andremarvell.shareit.imageloader.ImageLoader;
import com.andremarvell.shareit.webservices.user.Connexion;
import com.andremarvell.shareit.webservices.user.Update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Inscription extends SherlockFragment implements SwipeRefreshLayout.OnRefreshListener {


    EditText nom;
    EditText prenom;
    EditText adresse;
    EditText email;
    EditText pin;

    Button valider;


    /**
     * Fragment gérant l'ecran d'inscription
     *
     * @return Une nouvelle instance fragment de type Home.
     */
    public static Inscription newInstance() {
        Inscription fragment = new Inscription();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public Inscription() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_inscription, container, false);


        nom = (EditText) rootView.findViewById(R.id.nomInscription);
        prenom = (EditText) rootView.findViewById(R.id.prenomInscription);
        adresse = (EditText) rootView.findViewById(R.id.adresseInscription);
        email = (EditText) rootView.findViewById(R.id.emailInscription);
        pin = (EditText) rootView.findViewById(R.id.pin);

        valider = (Button) rootView.findViewById(R.id.valider);

        email.setText(ShareItApplication.USER.getEmail());

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                majUser();
            }
        });







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

    }

    public boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public void majUser(){

            if(getSherlockActivity().getCurrentFocus()!=null){
                InputMethodManager inputManager =
                        (InputMethodManager) getSherlockActivity().
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(
                        getSherlockActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }

            if(

                    !nom.getText().toString().equals("") &&
                    !prenom.getText().toString().equals("") &&
                    !adresse.getText().toString().equals("") &&
                    isEmailValid(email.getText().toString()) &&
                    !pin.getText().toString().equals("") &&
                    isInteger(pin.getText().toString())

              ){

                ShareItApplication.USER.setNom(nom.getText().toString());
                ShareItApplication.USER.setPrenom(prenom.getText().toString());
                ShareItApplication.USER.setAdresseText(adresse.getText().toString());
                ShareItApplication.USER.setEmail(email.getText().toString());
                ShareItApplication.USER.setPin(Integer.parseInt(pin.getText().toString()));

                (new Update(getSherlockActivity())).execute();
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
                builder.setMessage("Merci de renseigner correctement tous les champs")
                        .setPositiveButton("Ok",null);
                AlertDialog dialog1 = builder.create();
                dialog1.show();
            }

    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }


}

