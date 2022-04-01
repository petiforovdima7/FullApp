  package com.example.webviewappa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.onesignal.OSDeviceState;
import com.onesignal.OneSignal;

import java.util.Map;

  public class SplashActivity extends AppCompatActivity {
    private static final String ONESIGNAL_APP_ID = "3e75cb32-c63c-473e-a47e-0a1ca8cf983f";

        private static final String AF_DEV_KEY = "A3QsSUSmR9VDSbPzrQtcy3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //OneSignal

        OneSignal.initWithContext(SplashActivity.this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        //AppsFlyer
        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> map) {
//                System.out.println("Ok");
            }

            @Override
            public void onConversionDataFail(String s) {

            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {

            }

            @Override
            public void onAttributionFailure(String s) {
                System.out.println(s);
            }
        };

//        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, this);
//        AppsFlyerLib.getInstance().start(this);
//        AppsFlyerLib.getInstance().setDebugLog(true);



        OSDeviceState device = OneSignal.getDeviceState();
        String userId = device.getUserId();


        Thread splashScreenStarter = new Thread() {
            public void run() {
                try {
                    int delay = 0;
                    while (delay < 2000) {
                        sleep(150);
                        delay = delay + 100;
                    }

                    startActivity(new Intent(SplashActivity.this, AuthActivity.class));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }

        };

        if(!userId.equals("")) {
            splashScreenStarter.start();
        }

    }

}