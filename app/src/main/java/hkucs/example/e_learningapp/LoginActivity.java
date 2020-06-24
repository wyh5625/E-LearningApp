package hkucs.example.e_learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.client.UserStateListener;

public class LoginActivity extends AppCompatActivity {

    String TAG = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);


        AWSMobileClient.getInstance().initialize(this, new Callback<UserStateDetails>() {
                    @Override
                    public void onResult(UserStateDetails userStateDetails) {
                        //Log.i("INIT", "onResult: " + userStateDetails.getUserState());
                    }
                    @Override
                    public void onError(Exception e) {
                        Log.e("INIT", "Initialization error.", e);
                    }
                }
        );

        AWSMobileClient.getInstance().addUserStateListener(new UserStateListener() {
            @Override
            public void onUserStateChanged(UserStateDetails details) {
                Log.i("INIT", "onResult2: " + details.getUserState());
                switch(details.getUserState()){
                    case SIGNED_IN:
                        Log.d(TAG,"Signin successful with signin status: " + AWSMobileClient.getInstance().isSignedIn());
                        // navigate to main activity
                        /*
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        finish();
                        startActivity(intent);

                         */
                        break;
                    case SIGNED_OUT:
                        showSignIn();
                        break;
                    default:
                        Log.d(TAG,"Unhandled state see UserState for a list of states");
                        break;
                }
            }
        });


        // 'this' refers the the current active activity



        /*
        final IdentityManager identityManager = AWSProvider.getInstance().getIdentityManager();
        // Set up the callbacks to handle the authentication response
        identityManager.login(this, new DefaultSignInResultHandler() {
            @Override
            public void onSuccess(Activity activity, IdentityProvider identityProvider) {
                Toast.makeText(LoginActivity.this,
                        String.format("Logged in as %s", identityManager.getCachedUserID()),
                        Toast.LENGTH_LONG).show();
                // Go to the main activity
                final Intent intent = new Intent(activity, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
                activity.finish();
            }

            @Override
            public boolean onCancel(Activity activity) {
                return false;
            }
        });

        AWSMobileClient.getInstance().addUserStateListener(new UserStateListener() {
            @Override
            public void onUserStateChanged(UserStateDetails userStateDetails) {
                switch (userStateDetails.getUserState()){
                    case GUEST:
                        Log.i("userState", "user is in guest mode");
                        break;
                    case SIGNED_OUT:
                        Log.i("userState", "user is signed out");
                        break;
                    case SIGNED_IN:
                        Log.i("userState", "user is signed in");
                        break;
                    case SIGNED_OUT_USER_POOLS_TOKENS_INVALID:
                        Log.i("userState", "need to login again");
                        break;
                    case SIGNED_OUT_FEDERATED_TOKENS_INVALID:
                        Log.i("userState", "user logged in via federation, but currently needs new tokens");
                        break;
                    default:
                        Log.e("userState", "unsupported");
                }
            }
        });



        // Start the authentication UI
        AuthUIConfiguration config = new AuthUIConfiguration.Builder()
                .userPools(true)
                .build();
        SignInActivity.startSignInActivity(this, config);
        LoginActivity.this.finish();

         */
    }

    private void showSignIn(){
        try{
            //AWSMobileClient.getInstance().showSignIn(this);
            AWSMobileClient.getInstance().showSignIn(
                    this,
                    SignInUIOptions.builder()
                            .nextActivity(MainActivity.class)
                            .build(),
                    new Callback<UserStateDetails>() {
                        @Override
                        public void onResult(UserStateDetails result) {
                            Log.d(TAG, "onResult: " + result.getUserState());
                            switch (result.getUserState()){
                                case SIGNED_IN:
                                    Log.i("INIT", "logged in!");
                                    break;
                                case SIGNED_OUT:
                                    Log.i(TAG, "onResult: User did not choose to sign-in");
                                    break;
                                default:
                                    AWSMobileClient.getInstance().signOut();
                                    break;
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.e(TAG, "onError: ", e);
                        }
                    }
            );
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}
