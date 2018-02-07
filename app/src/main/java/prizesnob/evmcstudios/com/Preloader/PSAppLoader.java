package prizesnob.evmcstudios.com.Preloader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import prizesnob.evmcstudios.com.MainPages.PSFrontPage;
import prizesnob.evmcstudios.com.R;

/**
 * Created by edwardvalerio on 2/2/2018.
 */
public class PSAppLoader extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_preload_stage);

       // set timeout


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Intent i=new Intent(getApplicationContext(),PSFrontPage.class);
                        startActivity(i);
                        finish();
                    }
                },
                5000);


    }


}
