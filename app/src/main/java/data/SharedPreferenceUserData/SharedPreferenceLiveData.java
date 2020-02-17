package data.SharedPreferenceUserData;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

abstract class SharedPreferenceLiveData<T> extends LiveData<T> {

    SharedPreferences preferences;
    private String key;
    private T value;

    public SharedPreferenceLiveData(SharedPreferences preferences, String key, T value) {
        this.preferences = preferences;
        this.key = key;
        this.value = value;
    }

    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (SharedPreferenceLiveData.this.key.equals(key)) {
                setValue(getValueFromPreference(key, value));
            }
        }
    };

    abstract T getValueFromPreference(String key, T value);

    @Override
    protected void onActive() {
        super.onActive();
        setValue(getValueFromPreference(key, value));
        preferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    @Override
    protected void onInactive() {
        preferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
        super.onInactive();
    }
}
