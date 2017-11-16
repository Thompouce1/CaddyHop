package fr.polytechnice.caddyhop.injection.component;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Component;
import fr.polytechnice.caddyhop.activities.CreateAccountActivity;
import fr.polytechnice.caddyhop.activities.LoginActivity;
import fr.polytechnice.caddyhop.data.DataManager;
import fr.polytechnice.caddyhop.injection.module.ApplicationModule;

/**
 * Created by shafiq on 14/06/16.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(LoginActivity loginActivity);
    void inject(CreateAccountActivity createAccountActivity);

    DataManager dataManager();
}
