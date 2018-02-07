package prizesnob.evmcstudios.com.ApiTasks;

import android.os.AsyncTask;
import android.widget.Toast;

import prizesnob.evmcstudios.com.MainPages.PSDashboardPage;
import prizesnob.evmcstudios.com.Tasks.handler.HttpHandler;
import prizesnob.evmcstudios.com._CONSTANT.Constant;

/**
 * Created by edwardvalerio on 2/3/2018.
 */
public class GetOffersTask extends AsyncTask<Void, Void, String>{

    private PSDashboardPage activity;

    public GetOffersTask(PSDashboardPage activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        String offers = null;
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = Constant.CPA_OFFERS_URL;
        offers = sh.requestRegistrationService(url,"GET");
        return offers;
    }


    @Override
    protected void onPostExecute(String offers) {
        super.onPostExecute(offers);

        if(offers != null) {
            activity.setOffers(offers);
                   }
        else {
            Toast.makeText(activity.getApplicationContext(), "Error loading offers", Toast.LENGTH_SHORT).show();
        }


        if(activity.GetOfferTask  != null) {
            activity.GetOfferTask.cancel(true);
            activity.GetOfferTask = null;

        }



    }



}
