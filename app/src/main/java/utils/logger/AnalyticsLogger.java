package utils.logger;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class AnalyticsLogger {

    private static final String TAG_USER_ID = "user_id";
    private static final String ANALYTIC_TAG_SIGNUP = "sign_up";
    private static final String ANALYTIC_TAG_LOGIN = "login";

    private FirebaseAnalytics mFirebaseAnalytics;

    public AnalyticsLogger(Context context) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public void logSignupEvent(int userId) {
        Bundle params = new Bundle();
        params.putInt(TAG_USER_ID, userId);
        mFirebaseAnalytics.logEvent(ANALYTIC_TAG_SIGNUP, params);
    }

    /**
     * Assign user id to analytics
     *
     * @param userId
     */
    public void setUserId(int userId) {
        mFirebaseAnalytics.setUserId(String.valueOf(userId));
    }

    public void LoginEvent(int userId)
    {
        Bundle bundle =new Bundle();
        bundle.putInt(TAG_USER_ID,userId);
        mFirebaseAnalytics.logEvent(ANALYTIC_TAG_LOGIN,bundle);
    }

    public void logScreenName(String screenName, Activity activity) {
        mFirebaseAnalytics.setCurrentScreen(activity, screenName, null);
    }

}
