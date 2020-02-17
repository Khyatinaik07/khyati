package data.local.prefs;

import androidx.lifecycle.LiveData;

import io.reactivex.Completable;

public interface PreferencesHelper {

    void saveBackgroundColor(int eventBackgroundColor);

    int getBackgroundColor();

    void saveEventColor(int eventColor);

    int getEventColor();

    //  List<String> getFirebaseUserTopic();

    // void saveFirebaseUserTopic(List<String> topic);

    void setUserID(Integer userID);

    Integer getUserID();

    LiveData<String> getUserDataObserver();

    Completable clearAllPrefs();
}
