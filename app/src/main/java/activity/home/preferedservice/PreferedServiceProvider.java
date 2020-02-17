package activity.home.preferedservice;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PreferedServiceProvider {

    @ContributesAndroidInjector(modules = PreferedServiceModule.class)
    abstract PrefferedServiceActivity providePrefferedServiceActivity();

}
