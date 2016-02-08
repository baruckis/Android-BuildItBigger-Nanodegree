package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View mClickedView;
    InterstitialAd mInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        final MainActivity mainActivity = (MainActivity) getActivity();


        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

                requestNewInterstitial();

                switch (mClickedView.getId()) {
                    case R.id.android_library_button:
                        mainActivity.launchJokeActivity(null);
                        break;
                    case R.id.android_gce_button:
                        mainActivity.runAsyncTask(null);
                        break;
                }
            }
        });

        requestNewInterstitial();


        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // All emulators
                .addTestDevice("A141C838968B2B54DAE7794022DC3778")
                .build();
        mAdView.loadAd(adRequest);


        Button androidLibraryButton = (Button) root.findViewById(R.id.android_library_button);
        androidLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mClickedView = view;
                    mInterstitialAd.show();
                } else {
                    mainActivity.launchJokeActivity(null);
                }
            }
        });

        Button androidGceButton = (Button) root.findViewById(R.id.android_gce_button);
        androidGceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mClickedView = view;
                    mInterstitialAd.show();
                } else {
                    mainActivity.runAsyncTask(null);
                }
            }
        });


        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("A141C838968B2B54DAE7794022DC3778")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}