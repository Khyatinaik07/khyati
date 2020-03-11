package activity.home.location;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LocationProvider {

    @ContributesAndroidInjector(modules = LocationModule.class)
    abstract LocationActivity provideLocationActivity();

}
