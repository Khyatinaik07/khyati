package fragment.subtypeallservice;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SubTypeAllServiceProvider {

    @ContributesAndroidInjector(modules = SubTypeAllServiceModule.class)
    abstract SubTypeAllServiceFragment provideSubTypeAllServiceFragment();

}
