package prizesnob.evmcstudios.com.ApiTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import prizesnob.evmcstudios.com.MainPages.PSFrontPage;
import prizesnob.evmcstudios.com.MainPages.PSRegisterPage;
import prizesnob.evmcstudios.com.Tasks.handler.HttpHandler;
import prizesnob.evmcstudios.com._CONSTANT.Constant;

/**
 * Created by edwardvalerio on 2/3/2018.
 */
public class GetNonceTask extends AsyncTask<String, String , String> {

    private final String method;
    private final String controller;
    private final PSFrontPage loginActivity;
    private final PSRegisterPage registerActivity;
    private String nonce;


   public GetNonceTask(String method, String controller, PSFrontPage loginActivity, PSRegisterPage registerActivity) {
        this.method = method;
        this.loginActivity = loginActivity;
        this.registerActivity = registerActivity;
        this.controller = controller;


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e("Active", "Getting Nonce for " + method + " task ");

    }

    @Override
    protected String doInBackground(String... params) {

        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = Constant.NONCE_URL+"?controller="+controller+"&method="+method;
        String jsonStr  = sh.makeServiceCall(url,"GET");

        if (jsonStr != null) {

            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                nonce = jsonObj.getString("nonce");
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else {
            Log.e("Api ERROR", "Couldn't get json from server.");
            nonce = "error";
        }

        return nonce;

    }

    @Override
    protected void onPostExecute(String results) {
        super.onPostExecute(results);




        if(method == "register") {

            registerActivity.obtainNonceResultForRegistration(results);

        }

        if(method == "auth") {


        }


    }


}
