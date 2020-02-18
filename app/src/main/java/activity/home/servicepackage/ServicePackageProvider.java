package activity.home.servicepackage;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServicePackageProvider {

    @ContributesAndroidInjector(modules = ServicePackageModule.class)
    abstract ServicePackageActivity provideServicePackageActivity();

}
