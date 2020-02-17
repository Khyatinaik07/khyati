package activity.home.breifservicesubtype;

import activity.home.allsubservice.SubServiceAllModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SubTypeServiceProvider {

    @ContributesAndroidInjector(modules = SubServiceAllModule.class)
    abstract SubTypeServiceActivity provideSubTypeServiceActivity();

}
