package prizesnob.evmcstudios.com.Utilities;

import android.text.Html;

/**
 * Created by edwardvalerio on 2/4/2018.
 */
public class Utils {


    public static String getPlainText(String html) {

        String temp;
        temp = Html.fromHtml(html).toString();;
        return temp;

    }



}
