package activity.basic;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;

import com.example.onlineserviceportal.BuildConfig;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasBroadcastReceiverInjector;
import di.component.DaggerAppComponent;
import okhttp3.OkHttpClient;

public class OnlineServiceApp extends Application implements HasActivityInjector, HasBroadcastReceiverInjector {

    private static OnlineServiceApp mInstance;

    public static synchronized OnlineServiceApp getInstance() {
        return mInstance;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return broadcastReceiverDispatchingAndroidInjector;
    }

    @Inject
    OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance= this;

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

    }

}
