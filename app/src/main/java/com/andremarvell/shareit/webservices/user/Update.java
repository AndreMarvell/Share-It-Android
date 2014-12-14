package com.andremarvell.shareit.webservices.user;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragment;
import com.andremarvell.shareit.BaseSlidingMenu;
import com.andremarvell.shareit.R;
import com.andremarvell.shareit.ShareItApplication;
import com.andremarvell.shareit.SplashScreen;
import com.andremarvell.shareit.classe.user.User;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by Marvell.
 */
public class Update extends AsyncTask<String, Void, User> {

    private String URL = "users/";
    private String TAG = "Webservice mis à jour user";

    Context context;


    ProgressDialog progressDialog;

    protected User doInBackground(String... params) {
        return update();
    }

    public Update(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            progressDialog = new ProgressDialog(context, R.style.CustomDialog);
            progressDialog.setProgressStyle(android.R.attr.indeterminateProgressStyle);
        }else{

            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Chargement en cours...");
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        }
        progressDialog.show();
    }

    protected void onPostExecute(User result) {

        progressDialog.dismiss();
        if(result==null){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Erreur lors de la tentative de mise à jour")
                    .setPositiveButton("Ok",null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{

            ShareItApplication.USER = result;

            Intent i = new Intent(context, BaseSlidingMenu.class);
            context.startActivity(i);
            ((SplashScreen)context).finish();

        }

    }

    public User update() {

        HttpClient httpClient = new DefaultHttpClient();
        HttpPut put = new HttpPut(ShareItApplication.getWebServiceUrl()+URL+ShareItApplication.USER.getId());

        User user = null;

        try {

            JSONObject jsonParams = ShareItApplication.USER.getUserJson();
            StringEntity se = new StringEntity(jsonParams.toString());

            //se.setContentType("application/json;charset=UTF-8");
            //se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
            put.addHeader("Accept", "application/json");
            put.addHeader("Content-type", "application/json");
            put.setEntity(se);

            HttpResponse response = httpClient.execute(put);
            StatusLine statusLine = response.getStatusLine();

            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line = reader.readLine();

                //boolean error = (new JSONObject(line)).getBoolean("error");

                user = new User(new JSONObject(line));



                inputStream.close();
            }else {
                Log.d(TAG,statusLine.toString());
                Log.d(TAG," Requete : "+jsonParams.toString());
                Log.d(TAG," URL : "+put.getURI().toString());
            }
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }

        return user;
    }



}
