package com.andremarvell.shareit;



import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.andremarvell.shareit.webservices.user.Connexion;
import com.andremarvell.shareit.webservices.user.Inscription;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FBLoginFragment extends SherlockFragment {

    // Pour la connexion normale

    EditText identifiant ;
    EditText password;

    // Connexion Facebook

    private static final String TAG = "FBLoginFragment";

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    private UiLifecycleHelper uiHelper;

    public FBLoginFragment() {
        // Required empty public constructor
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Request.newMeRequest(session, new Request.GraphUserCallback()
            {
                @Override
                public void onCompleted(GraphUser user, Response response)
                {
                    if (response != null)
                    {
                        try
                        {
                            String name = user.getName();
                            // If you asked for email permission
                            String email = (String) user.getProperty("email");
                            Log.i(TAG, "Name: " + name + " Email: " + email);
                            Log.i(TAG, "User: " + user.toString());
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            Log.d(TAG, "Exception e");
                        }

                    }
                }
            }).executeAsync();
        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_splash_screen, container, false);

        LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
        authButton.setFragment(this);
        authButton.setReadPermissions(Arrays.asList("email", "user_about_me"));

        // Connexion normale

        identifiant = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);

        ((Button)view.findViewById(R.id.connexion)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getSherlockActivity().getCurrentFocus()!=null){
                    InputMethodManager inputManager =
                            (InputMethodManager) getSherlockActivity().
                                    getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(
                            getSherlockActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

                if(
                    isEmailValid(identifiant.getText().toString()) &&
                            !password.getText().toString().equals("")
                  ){
                    (new Connexion(getSherlockActivity())).execute(
                            identifiant.getText().toString(),
                            password.getText().toString()
                    );
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
                    builder.setMessage("Merci de renseigner correctement tous les champs")
                            .setPositiveButton("Ok",null);
                    AlertDialog dialog1 = builder.create();
                    dialog1.show();
                }


            }
        });
        ((Button)view.findViewById(R.id.inscription)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getSherlockActivity().getCurrentFocus()!=null){
                    InputMethodManager inputManager =
                            (InputMethodManager) getSherlockActivity().
                                    getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(
                            getSherlockActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

                if(
                    isEmailValid(identifiant.getText().toString()) &&
                    !password.getText().toString().equals("")
                  ){
                    (new Inscription(getSherlockActivity())).execute(
                            identifiant.getText().toString(),
                            password.getText().toString()
                    );
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity());
                    builder.setMessage("Merci de renseigner correctement tous les champs")
                            .setPositiveButton("Ok",null);
                    AlertDialog dialog1 = builder.create();
                    dialog1.show();
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // For scenarios where the main activity is launched and user
        // session is not null, the session state change notification
        // may not be triggered. Trigger it if it's open/closed.
        Session session = Session.getActiveSession();
        if (session != null && (session.isOpened() || session.isClosed()) ) {
            onSessionStateChange(session, session.getState(), null);
        }

        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
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


}
