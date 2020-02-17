package fragment.myprofile;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MyProfileProvider {

    @ContributesAndroidInjector(modules = MyProfileModule.class)
    abstract MyProfileFragment provideMyProfileFragment();

}
