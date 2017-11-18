package fr.polytechnice.caddyhop.injection.module;

import dagger.Module;
import dagger.Provides;
import fr.polytechnice.caddyhop.data.DataManager;
import fr.polytechnice.caddyhop.network.CaddyHopRetrofitService;

/**
 * Created by shafiq on 14/06/16.
 */

@Module
public class DataManagerModule {

    @Provides
    DataManager provideDataManager() {
        return new DataManager(CaddyHopRetrofitService.Creator.newCHService());
    }
}
