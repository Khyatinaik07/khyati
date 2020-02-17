package data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import androidx.lifecycle.LiveData;

import javax.inject.Inject;

import data.SharedPreferenceUserData.SharedPreferenceUserDataObserver;
import di.PreferenceInfo;
import io.reactivex.Completable;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_EVENT_BACKGROUND_COLOR = "pref_event_background_color";
    private static final String PREF_EVENT_COLOR = "pref_event_color";
    private final SharedPreferences mPrefs;
    private static final String PREF_FIREBASE_USER_TOPIC = "pref_firebase_user_topic";
    private static final String PREF_USER_ID = "pref_attendee_id";
    private static final String PREF_ACCESS_TOKEN = "pref_access_token";

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void saveBackgroundColor(int eventBackgroundColor) {

    }

    @Override
    public int getBackgroundColor() {
        return mPrefs.getInt(PREF_EVENT_BACKGROUND_COLOR, Color.WHITE);
    }

    @Override
    public void saveEventColor(int eventColor) {
        mPrefs.edit().putInt(PREF_EVENT_COLOR, eventColor).apply();
    }

    @Override
    public int getEventColor() {
        return mPrefs.getInt(PREF_EVENT_COLOR, Color.parseColor("#0078ff"));
    }

 /*   @Override
    public List<String> getFirebaseUserTopic() {
        String json = mPrefs.getString(PREF_FIREBASE_USER_TOPIC, "[]");
        TypeToken<List<String>> token = new TypeToken<List<String>>() {
        };
        return new Gson().fromJson(json, token.getType());
    }  */

  /*  @Override
    public void saveFirebaseUserTopic(List<String> topic) {
        String json = new Gson().toJson(topic);
        mPrefs.edit().putString(PREF_FIREBASE_USER_TOPIC, json).apply();
    }  */

    @Override
    public void setUserID(Integer userID) {
        mPrefs.edit().putInt(PREF_USER_ID, userID).apply();
    }

    @Override
    public Integer getUserID() {
        return mPrefs.getInt(PREF_USER_ID,0);
    }

    @Override
    public LiveData<String> getUserDataObserver() {
        return new SharedPreferenceUserDataObserver(mPrefs,PREF_USER_ID,"");
    }

    @Override
    public Completable clearAllPrefs() {
        return Completable.fromAction(() -> mPrefs.edit().clear().apply());
    }

 /*   @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_ACCESS_TOKEN, accessToken).apply();
    }  */
}
