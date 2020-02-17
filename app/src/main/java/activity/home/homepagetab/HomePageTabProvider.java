package activity.home.homepagetab;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomePageTabProvider {

    @ContributesAndroidInjector(modules = HomePageTabModule.class)
    abstract HomePageTabActivity provideHomePageTabActivity();

}
