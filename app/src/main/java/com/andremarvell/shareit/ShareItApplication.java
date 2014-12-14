package com.andremarvell.shareit;



import com.andremarvell.shareit.classe.user.User;

/**
 * Created by ikounga_marvel on 05/08/2014.
 */
public class ShareItApplication extends android.app.Application {

    public static User USER = null;

    public static boolean prod = false;

    public static String devWebServiceUrl = "/ShareIt/web/api/";
    public static String webServiceUrl = "/api/";
    public static final String IP = "http://192.168.1.45";
    public static final String HOTE = "http://shareit-app.com";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ShareItApplication() {
        super();
    }

    public static String getWebServiceUrl() {
        if(prod)
            return HOTE+webServiceUrl;
        else
            return IP+devWebServiceUrl;
    }
}