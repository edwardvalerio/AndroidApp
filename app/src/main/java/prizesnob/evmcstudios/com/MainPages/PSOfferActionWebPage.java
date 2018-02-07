package prizesnob.evmcstudios.com.MainPages;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import prizesnob.evmcstudios.com.R;
import prizesnob.evmcstudios.com.ZSchema.UserProfile;

/**
 * Created by edwardvalerio on 2/4/2018.
 */
public class PSOfferActionWebPage extends AppCompatActivity {

    WebView OfferWebView;
    ProgressBar WebProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.prize_snob_offer_web_view);

        WebProgress = (ProgressBar) findViewById(R.id.web_progress);

        OfferWebView = (WebView) findViewById(R.id.OfferWebView);
        OfferWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.startsWith("market://")||url.startsWith("vnd:youtube")||url.startsWith("tel:")||url.startsWith("mailto:")||url.startsWith("intent://"))  {
                     Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://" + url));
                    startActivity(intent);
                    return true;
                }
                else{
                    view.loadUrl(url);
                    return true;
                }
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                showProgress(false);
                super.onPageFinished(view, url);
            }


        } );








        WebSettings webSettings = OfferWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(true);


        OfferWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {
                WebView newWebView = new WebView(view.getContext());
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(newWebView);
                resultMsg.sendToTarget();
                return true;
            }
        });



        // get Data From Activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String URL = extras.getString("OFFERURL");
            OfferWebView.loadUrl(URL);
        }
        else {
            Toast.makeText(getApplicationContext(), "Offer is no longer available", Toast.LENGTH_SHORT).show();
            OfferWebView.loadUrl("http://www.google.com");
        }




    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.

        WebProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        OfferWebView.setVisibility(show ? View.GONE : View.VISIBLE);

    }


}
