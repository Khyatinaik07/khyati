package activity.home.servicepackagelayout2;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServicePackageProvider {

    @ContributesAndroidInjector(modules = ServicePackageModule.class)
    abstract ServicePackageActivity provideServicePackageActivity();

}
