package fr.polytechnice.caddyhop.injection.component;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Component;
import fr.polytechnice.caddyhop.injection.module.DataManagerModule;

/**
 * Created by shafiq on 14/06/16.
 */
@Singleton
@Component(modules={DataManagerModule.class})
public interface DataManagerComponent {
    void inject(Activity activity);
}
