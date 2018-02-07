package prizesnob.evmcstudios.com.MainPages;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import prizesnob.evmcstudios.com.Adapters.OfferListAdapter;
import prizesnob.evmcstudios.com.ApiTasks.GetOffersTask;
import prizesnob.evmcstudios.com.R;
import prizesnob.evmcstudios.com.ZSchema.OfferBucket;
import prizesnob.evmcstudios.com.ZSchema.OfferItem;
import prizesnob.evmcstudios.com.ZSchema.UserProfile;
import prizesnob.evmcstudios.com._CONSTANT.Storage;

/**
 * Created by edwardvalerio on 2/3/2018.
 */
public class PSDashboardPage extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    UserProfile userProfile;
    private Storage appStorage;
    public GetOffersTask GetOfferTask = null;
    OfferBucket offerBucket;
    TextView username;
    TextView email;


    // offer list
    private static OfferListAdapter OfferAdapter;
    ListView offerView;
    SwipeRefreshLayout offerLayoutRefresh;
    ProgressBar offerProgress;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prize_snob_dashboard);

        // progress
        offerProgress = (ProgressBar) findViewById( R.id.offer_list_progress);

        // list view
        offerLayoutRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        offerView = (ListView) findViewById(R.id.offerListView);

        // listen for refresh
        offerLayoutRefresh.setOnRefreshListener(this);


        // get Elements
        username = (TextView) findViewById(R.id.username);
        email = (TextView) findViewById(R.id.email);

        // get Data From Activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String userAccount = extras.getString("USERACCOUNT");
            userProfile = new UserProfile(userAccount);
       }
        else  {
            appStorage = new Storage(getApplicationContext());
            userProfile = new UserProfile(appStorage.getUserAccount());
        }

        // display User Profile
        username.setText("Welcome, " + userProfile.getUsername() + "!");
        email.setText(userProfile.getEmail());


        // load offers

        getOffers();

    }


    @Override
    public void onResume(){
        super.onResume();
        getOffers();

    }

    @Override
    public void onRefresh() {
        offerLayoutRefresh.setRefreshing(false);
        getOffers();
    }

    public void getOffers(){

        showProgress(true);


        if(GetOfferTask == null) {
            GetOfferTask = new GetOffersTask(PSDashboardPage.this);
            GetOfferTask.execute((Void) null);
        }

    }

    public void setOffers(String offers) {

        offerBucket = new OfferBucket(offers);
        OfferAdapter = new OfferListAdapter(offerBucket.getOfferList(), PSDashboardPage.this.getApplicationContext());
        offerView.setAdapter(OfferAdapter);

        showProgress(false);


        offerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                OfferItem offer = offerBucket.getOfferList().get(position);

                // start activity
                Intent ViewOfferOnWeb = new Intent(getApplicationContext(), PSOfferActionWebPage.class);

                // pass URL
                Bundle bundle = new Bundle();
                bundle.putString("OFFERURL", offer.getUrl());
                ViewOfferOnWeb.putExtras(bundle);
                startActivity(ViewOfferOnWeb);


            }
        });

    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.

            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
        offerProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        offerLayoutRefresh.setVisibility(show ? View.GONE : View.VISIBLE);

    }


}
