package activity.home.servicepackagelayout1.editpackage;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class EditPackageProvider {

    @ContributesAndroidInjector(modules = EditPackageModule.class)
    abstract EditPackageActivity provideEditPackageActivity();

}
