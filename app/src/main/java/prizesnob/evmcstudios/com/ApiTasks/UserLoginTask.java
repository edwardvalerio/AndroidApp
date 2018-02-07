package prizesnob.evmcstudios.com.ApiTasks;

import android.os.AsyncTask;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import prizesnob.evmcstudios.com.MainPages.PSFrontPage;

import prizesnob.evmcstudios.com.Tasks.handler.HttpHandler;
import prizesnob.evmcstudios.com._CONSTANT.Constant;

/**
 * Created by edwardvalerio on 2/3/2018.
 */
public class UserLoginTask extends AsyncTask<Void,Void,String> {

    private final String email;
    private final String password;
    private PSFrontPage activity;

    public UserLoginTask(String email, String password, PSFrontPage activity) {

        this.password = password;
        this.email = email;
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
        String url = Constant.LOGIN_URL +"?username="+email+"&password=" + password;
        finalvalue  = sh.requestRegistrationService(url,"GET");


        return finalvalue;


    }

    @Override
    protected void onPostExecute(String userAccount) {
        super.onPostExecute(userAccount);
        String status = null;
        String errors = null;
        String cookie = null;


        activity.showProgress(false);

        if (userAccount  != null) {

            try {

                JSONObject jsonObj = new JSONObject(userAccount);
                status = jsonObj.getString("status");

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

            activity.successfulLogin(cookie, userAccount);

        }

        if(activity.mAuthTask  != null) {
            activity.mAuthTask.cancel(true);
            activity.mAuthTask = null;

        }



    }
}
