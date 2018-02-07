package prizesnob.evmcstudios.com.ApiTasks;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import prizesnob.evmcstudios.com.MainPages.PSDashboardPage;
import prizesnob.evmcstudios.com.MainPages.PSFrontPage;
import prizesnob.evmcstudios.com.MainPages.PSRegisterPage;
import prizesnob.evmcstudios.com.Tasks.handler.HttpHandler;
import prizesnob.evmcstudios.com._CONSTANT.Constant;
import prizesnob.evmcstudios.com._CONSTANT.Storage;

/**
 * Created by edwardvalerio on 2/3/2018.
 */
public class UserRegisterTask extends AsyncTask<Void, Void, String> {


    private final String username;
    private final String email;
    private final String password;
    private final String nonce;
    private PSRegisterPage activity;



   public UserRegisterTask(String username, String email, String password, String nonce, PSRegisterPage activity) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nonce = nonce;
        this.activity = activity;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        String finalvalue = null;
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = Constant.REGISTER_URL +"?username="+username+"&email="+email+"&nonce="+nonce+"&display_name=" + username
                + "&notify=no" + "&user_pass=" + password;
        finalvalue  = sh.requestRegistrationService(url,"GET");


        return finalvalue;


    }



    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        String status = null;
        String errors = null;
        String cookie = null;


        Log.e("RESULT", ". " + result);

        activity.showProgress(false);

        if (result  != null) {

            try {

                JSONObject jsonObj = new JSONObject(result);
                status = jsonObj.getString("status");

                Log.e("STATUS", " - " + status);

                if(status.equals("ok")) {
                    cookie = jsonObj.getString("cookie");
                 }
                else if(status.equals("error")) {
                    errors = jsonObj.getString("error");
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else {


            Toast.makeText(activity.getApplicationContext(), "Error, please try again later.", Toast.LENGTH_SHORT).show();
            activity.cleanFields();

        }



        if(errors != null) {

            Toast.makeText(activity.getApplicationContext(), errors, Toast.LENGTH_SHORT).show();

        }


        if(cookie != null) {

            activity.successfulRegistration(cookie);

        }

        if(activity.mNonceTask != null) {
            activity.mNonceTask.cancel(true);
            activity.mNonceTask = null;
                    }

        if(activity.mRegisterTask != null) {
            activity.mRegisterTask.cancel(true);
            activity.mRegisterTask = null;

        }




     }

}
