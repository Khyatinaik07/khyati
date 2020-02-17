package activity.authentication.login;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LoginProvider {

    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity provideLoginActivity();

}
