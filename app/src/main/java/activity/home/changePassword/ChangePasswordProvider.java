package activity.home.changePassword;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ChangePasswordProvider {

    @ContributesAndroidInjector(modules = changePasswordModule.class)
    abstract ChangePasswordActivity provideChangePasswordActivity();

}
