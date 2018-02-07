package prizesnob.evmcstudios.com._CONSTANT;

/**
 * Created by edwardvalerio on 2/2/2018.
 */
public class Constant {

    public static final String HOST = "https://evmcstudios.com";
    public static final String API = "/api/";
    public static final String HOSTAPI = HOST + API;
    public static final String NONCE_URL = HOSTAPI + "get_nonce/?controller=user&method=register";
    public static final String REGISTER_URL = HOSTAPI + "user/register/";
    public static final String LOGIN_URL = HOSTAPI + "auth/generate_auth_cookie/";
    public static final String CPA_OFFERS_URL = HOSTAPI + "get_posts/?post_type=cpa-offer&per_page=99";
    public static final String REDEEMABLE_OFFERS_URL = HOSTAPI + "get_posts/?post_type=redeemable-offer&per_page=99";

}
