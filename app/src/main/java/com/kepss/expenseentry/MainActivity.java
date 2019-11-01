package com.kepss.expenseentry;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.kepss.expenseentry.databinding.ActivityMainBinding;
import com.kepss.expenseentry.service.JobSchedulerService;
import com.kepss.expenseentry.viewModel.ViewModelActivityMain;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private static final String TAG = "MainActivity";
    JobScheduler mJobScheduler;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ViewModelActivityMain viewModelActivityMain = new ViewModelActivityMain(this);
        activityMainBinding.setViewModel(viewModelActivityMain);
//
        mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(1,new ComponentName(getPackageName(), JobSchedulerService.class.getName()));
        builder.setPeriodic(3000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setRequiresCharging(true);
//
        if (mJobScheduler.schedule(builder.build()) <= 0) {
            Toast.makeText(this, "onCreate: Some error while scheduling the job", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onCreate: Some error while scheduling the job");
        }
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-8620265603910499/9818644533");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
//                Toast.makeText(MainActivity.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
//                Toast.makeText(MainActivity.this, "onAdFailedToLoad " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
//                Toast.makeText(MainActivity.this, "onAdOpened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
//                Toast.makeText(MainActivity.this, "onAdClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
//                Toast.makeText(MainActivity.this, "onAdLeftApplication", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
//                Toast.makeText(MainActivity.this, "onAdClosed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
