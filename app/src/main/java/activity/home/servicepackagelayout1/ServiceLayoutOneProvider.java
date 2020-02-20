package activity.home.servicepackagelayout1;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServiceLayoutOneProvider {

    @ContributesAndroidInjector(modules = ServiceLayoutOneModule.class)
    abstract ServicePackageLayoutOneActivity provideServicePackageLayoutOneActivity();

}
