package data.remote;

import javax.inject.Inject;

import data.local.prefs.PreferencesHelper;

public class ApiHeader {

    private PreferencesHelper preferencesHelper;

    @Inject
    public ApiHeader(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
    }

}
