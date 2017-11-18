package fr.polytechnice.caddyhop.data;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.polytechnice.caddyhop.models.User;
import fr.polytechnice.caddyhop.network.CaddyHopRetrofitService;
import rx.Observable;

/**
 * Created by shafiq on 13/06/16.
 *
 * A middleman between the API service and the UI using the service.
 * Ideally, a presenter should be present between the DataManager and the UI.
 */

@Singleton
public class DataManager {
    final String TAG = this.getClass().getSimpleName();

    User user;
    boolean isLoggedIn;

    private final CaddyHopRetrofitService mCaddyHopRetrofitService;

    @Inject
    public DataManager(CaddyHopRetrofitService caddyHopRetrofitService) {
        mCaddyHopRetrofitService = caddyHopRetrofitService;
    }

    public Observable<User> getUser(long id) {
        return mCaddyHopRetrofitService.getUserInfo(id);
    }

    public Observable<Long> loginAuth(String login, String mdp) {
        return mCaddyHopRetrofitService.loginAuth(login, mdp);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {

        Log.d(TAG, "setUser: " + user);
        this.user = user;
    }
}
