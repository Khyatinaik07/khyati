package activity.authentication.registration;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RegistrationActivityProvider {

    @ContributesAndroidInjector(modules = RegistrationModule.class)
    abstract RegistrationActivity provideRegistrationActivity();

}
