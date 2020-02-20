package activity.home.servicepackagelayout2.specificationdetail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SpecificationDetailProvider {

    @ContributesAndroidInjector(modules = SpecificationDetailModule.class)
    abstract SpecificationDetailActivity provideSpecificationDetailActivity();

}
