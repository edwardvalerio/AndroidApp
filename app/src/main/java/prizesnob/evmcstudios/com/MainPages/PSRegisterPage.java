package prizesnob.evmcstudios.com.MainPages;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import prizesnob.evmcstudios.com.ApiTasks.GetNonceTask;
import prizesnob.evmcstudios.com.ApiTasks.UserRegisterTask;
import prizesnob.evmcstudios.com.R;
import prizesnob.evmcstudios.com._CONSTANT.Storage;

/**
 * Created by edwardvalerio on 2/3/2018.
 */



public class PSRegisterPage extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mUserNameView;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    private View mRegisterProgressView;
    private View mRegisterFormView;
    private String username;
    private String email;
    private String password;
    private String passwordconfirm;
    private Storage appStorage;


    public UserRegisterTask mRegisterTask = null;
    public GetNonceTask mNonceTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prize_snob_register);

        // shared preferences class
        appStorage = new Storage(getApplicationContext());

        // Set up the login form.
        mUserNameView  = (AutoCompleteTextView) findViewById(R.id.user_register);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_register);
        mPasswordView = (EditText) findViewById(R.id.password_register);
        mConfirmPasswordView = (EditText) findViewById(R.id.password_register_confirm);

        mRegisterFormView  = findViewById(R.id.register_form);
        mRegisterProgressView = findViewById(R.id.register_progress);


        mConfirmPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.register || id == EditorInfo.IME_NULL) {
                    attemptCreation();
                    return true;
                }
                return false;
            }
        });



        Button mCreateAccountButton = (Button) findViewById(R.id.create_account_button);
        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptCreation();
            }
        });




    }

    private boolean isUsernameValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@") && email.contains(".com") || email.contains(".net") ;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    private boolean isPasswordSame(String password1, String password2) {
        //TODO: Replace this with your own logic

        if(password1.equals( password2)) {
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * Shows the progress UI and hides the form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegisterFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mRegisterProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegisterProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegisterProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mRegisterProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void attemptCreation() {

        if (mNonceTask != null) {
            return;
        }

        // Reset errors.
        mUserNameView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mConfirmPasswordView.setError(null);


        // Store values at the time of the login attempt.

        username = mUserNameView.getText().toString();
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        passwordconfirm = mConfirmPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid email address. and pass
        if(TextUtils.isEmpty(username)) {
            mUserNameView.setError(getString(R.string.error_field_required));
            focusView = mUserNameView;
            cancel = true;

        }

        else if (!isUsernameValid(username)) {
            mUserNameView.setError(getString(R.string.error_invalid_username));
            focusView = mUserNameView;
            cancel = true;
        }
        else if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        else if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }
        else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        else if (!isPasswordSame(password , passwordconfirm)) {


            mPasswordView.setError(getString(R.string.error_same_password));
            focusView = mPasswordView;
            cancel = true;


        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            mNonceTask = new GetNonceTask("register", "user", null, PSRegisterPage.this);
            mNonceTask.execute((String) null);

        }
    }


    public void attemptRegistration(String noncevalue) {

        if(mRegisterTask == null) {
            mRegisterTask = new UserRegisterTask(username, email, password, noncevalue, PSRegisterPage.this);
            mRegisterTask.execute((Void) null);
        }

    }

    public void obtainNonceResultForRegistration(String nonce) {

        attemptRegistration(nonce);

    }



    public void cleanFields() {

        mUserNameView.setText("");
        mEmailView.setText("");
        mPasswordView.setText("");
        mConfirmPasswordView.setText("");

    }

    public void successfulRegistration(String cookie) {

        appStorage.saveCookie(cookie);

        // start activity
        Intent i=new Intent(getApplicationContext(), PSDashboardPage.class);
        startActivity(i);
        finish();


    }



}
