package activity.home.servicepackagelayout3.viewpackagedetail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServicePackageDetailProvider {

    @ContributesAndroidInjector(modules = ServicePackageDetailModule.class)
    abstract ServicePackageDetailActivity provideServicePackageDetailActivity();

}
