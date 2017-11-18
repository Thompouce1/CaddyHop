package fr.polytechnice.caddyhop.data;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by shafiq on 14/06/16.
 *
 * All function that want to use the SharedPreference should use this helper
 *
 * TODO: migrate all function using SharedPreference to use this helper
 */

@Singleton
public class PreferencesHelper {
    public static final String PREF_FILE_NAME = "caddyhop_pref_file";

    private final SharedPreferences mPref;

    @Inject
    public PreferencesHelper(Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }
}
