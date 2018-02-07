package prizesnob.evmcstudios.com._CONSTANT;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by edwardvalerio on 2/3/2018.
 */
public class Storage {

    public static final String STORAGEPS = "MyStorage";
    public static final String COOKIE = "cookieKey";
    public static final String USERACCOUNT = "accountKey";
    public String savedCookie = "";
    public String savedAccount = "";
    private Context context;
    SharedPreferences sharedpreferences;

    public Storage(Context context) {
        this.context = context;
    }





    public void saveCookie(String mCookie) {

        sharedpreferences = context.getSharedPreferences(STORAGEPS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(COOKIE, mCookie);
        editor.apply();


    }

    public void saveUserAccount(String mAccount) {

        sharedpreferences = context.getSharedPreferences(STORAGEPS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USERACCOUNT, mAccount);
        editor.apply();


    }

    public void clearCookie(){

        sharedpreferences = context.getSharedPreferences(STORAGEPS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();

    }

    public String getCookie() {

        sharedpreferences = context.getSharedPreferences(STORAGEPS, Context.MODE_PRIVATE);
        savedCookie = sharedpreferences.getString(COOKIE,"");

        return savedCookie;

    }

    public String getUserAccount() {

        sharedpreferences = context.getSharedPreferences(STORAGEPS, Context.MODE_PRIVATE);
        savedAccount = sharedpreferences.getString(USERACCOUNT,"");

        return savedAccount;

    }


}
