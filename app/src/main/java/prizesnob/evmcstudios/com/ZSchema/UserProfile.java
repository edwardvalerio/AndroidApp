package prizesnob.evmcstudios.com.ZSchema;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by edwardvalerio on 2/3/2018.
 */
public class UserProfile {

    private final String userAccount;
    private String STATUS;
    private String COOKIE;
    private String COOKIE_NAME;
    private String USER;
    private String ID;
    private String USERNAME;
    private String EMAIL;
    private JSONObject responseObj;
    private JSONObject userObj;


    public UserProfile(String userAccount){
        this.userAccount = userAccount;
        setUserAccount();
    }


    public void setUserAccount() {

        try {
            responseObj = new JSONObject(userAccount);
            COOKIE = responseObj.getString("cookie");
            USER = responseObj.getString("user");
            userObj = new JSONObject(USER);
            ID = userObj.getString("id");
            USERNAME = userObj.getString("username");
            EMAIL = userObj.getString("email");

        }
        catch (JSONException e) {
            e.printStackTrace();
         }

    }

    public String getID() {

        return this.ID;
    }

    public String getUser() {

        return this.USER;
    }
    public String getUsername() {

        return this.USERNAME;

    }

    public String getEmail(){

        return this.EMAIL;
    }


}
