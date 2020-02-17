package activity.home.allsubservice;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public  abstract class SubServiceAllProvider {

    @ContributesAndroidInjector(modules = SubServiceAllModule.class)
    abstract SubServiceAllActivity provideSubServiceAllActivity();

}
