package data.SharedPreferenceUserData;

import android.content.SharedPreferences;

public class SharedPreferenceUserDataObserver extends SharedPreferenceLiveData<String> {

    public SharedPreferenceUserDataObserver(SharedPreferences preferences, String key, String value) {
        super(preferences, key, value);
    }

    @Override
    String getValueFromPreference(String key, String value) {
        return preferences.getString(key, value);
    }
}
