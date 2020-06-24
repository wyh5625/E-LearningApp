package hkucs.example.e_learningapp;

import androidx.multidex.MultiDexApplication;

public class Application extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the AWS Provider
        //AWSProvider.initialize(getApplicationContext());

        //registerActivityLifecycleCallbacks(new ActivityLifeCycle());
    }
}