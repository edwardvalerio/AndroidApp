package prizesnob.evmcstudios.com.ZSchema;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by edwardvalerio on 2/3/2018.
 */
public class OfferBucket {

    private final String bucket;

    private Integer OFFER_COUNT;
    private Integer TOTAL_COUNT;

    private JSONObject responseObj;
    private JSONArray offersArray;
    private ArrayList<OfferItem> OfferList;

    public OfferBucket(String bucket) {

        this.bucket = bucket;
        setBucket();
    }


    public void setBucket() {

        OfferList = new ArrayList<OfferItem>();

        try {
            responseObj = new JSONObject(bucket);
            OFFER_COUNT = responseObj.getInt("count");
            TOTAL_COUNT = responseObj.getInt("count_total");

            if(responseObj.has("posts")) {
                // offers
                offersArray = responseObj.getJSONArray("posts");

                // looping through All Contacts
                for (int i = 0; i < offersArray.length(); i++) {
                    OfferItem tempItem = new OfferItem(offersArray.getJSONObject(i));
                    OfferList.add(tempItem);

                }

            }



        }
        catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public Integer getOfferCount(){

        return this.OFFER_COUNT;
    }

    public Integer getTotalOfferCount(){
        return this.TOTAL_COUNT;
    }

    public ArrayList<OfferItem> getOfferList(){
        return this.OfferList;
    }


}
