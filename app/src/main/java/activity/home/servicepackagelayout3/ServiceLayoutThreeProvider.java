package activity.home.servicepackagelayout3;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServiceLayoutThreeProvider {

    @ContributesAndroidInjector(modules = ServiceLayoutThreeModule.class)
    abstract ServicePackageLayoutThreeActivity provideServicePackageLayoutThreeActivity();
}
