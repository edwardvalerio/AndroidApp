package prizesnob.evmcstudios.com.ZSchema;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import prizesnob.evmcstudios.com.Utilities.Utils;

/**
 * Created by edwardvalerio on 2/3/2018.
 */
public class OfferItem {

    private final JSONObject offer;
    private String OFFER_ID;
    private String TITLE;
    private String AMOUNT;
    private String DESCRIPTION;
    private String IMAGE = "-";
    private String URL;

    public OfferItem(JSONObject offer){
        this.offer = offer;
        setOfferDetail();
    }

    public void setOfferDetail(){

        try {

            OFFER_ID = offer.getString("id");
            TITLE = Utils.getPlainText(offer.getString("title_plain"));
            DESCRIPTION = Utils.getPlainText(offer.getString("content"));

            // custom fields

            JSONObject customFields = offer.getJSONObject("custom_fields");

            // extract info from array

            if(customFields.has("wpcf-snob_reward")) {
                JSONArray reward = customFields.getJSONArray("wpcf-snob_reward");
                AMOUNT = reward.get(0).toString();
            }

            if(customFields.has("wpcf-offer_image")) {
                JSONArray image = customFields.getJSONArray("wpcf-offer_image");
                IMAGE = image.get(0).toString();
            }

            if(customFields.has("wpcf-offer_link")) {
                JSONArray link = customFields.getJSONArray("wpcf-offer_link");
                URL = link.get(0).toString();
            }

            Log.e("new id ", " " + DESCRIPTION);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getID(){
        return this.OFFER_ID;
    }

    public String getTitle(){
        return this.TITLE;
    }

    public String getAmount(){
        return this.AMOUNT;
    }

    public String getDescription(){
        return this.DESCRIPTION;
    }

    public String getImageUrl(){
        return this.IMAGE;
    }

    public String getUrl(){
        return this.URL;
    }



}
