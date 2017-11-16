package fr.polytechnice.caddyhop.injection.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.polytechnice.caddyhop.network.CaddyHopRetrofitService;

/**
 * Created by shafiq on 14/06/16.
 */

@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication (){
        return mApplication;
    }

    @Provides
    @Singleton
    CaddyHopRetrofitService provideCHService() {
        return CaddyHopRetrofitService.Creator.newCHService();
    }
}
