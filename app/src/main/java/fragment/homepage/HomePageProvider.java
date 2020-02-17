package fragment.homepage;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomePageProvider {

    @ContributesAndroidInjector(modules = HomePageModule.class)
    abstract HomePageFragment provideHomePageFragment();

}
