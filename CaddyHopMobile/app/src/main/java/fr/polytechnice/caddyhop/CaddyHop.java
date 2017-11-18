package fr.polytechnice.caddyhop;

import android.app.Application;

import fr.polytechnice.caddyhop.injection.component.ApplicationComponent;
import fr.polytechnice.caddyhop.injection.component.DaggerApplicationComponent;
import fr.polytechnice.caddyhop.injection.module.ApplicationModule;

/**
 * Created by shafiq on 14/06/16.
 */
public class CaddyHop extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                                .applicationModule(new ApplicationModule(this))
                                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
