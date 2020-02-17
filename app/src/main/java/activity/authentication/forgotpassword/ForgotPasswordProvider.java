package activity.authentication.forgotpassword;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ForgotPasswordProvider {

    @ContributesAndroidInjector(modules = ForgotPaswordModule.class)
    abstract ForgotPasswordActivity provideForgotPasswordActivity();

}
