package activity.authentication.otpverify;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class VerifyOTProvider {

    @ContributesAndroidInjector(modules = VerifyOTPModule.class)
    abstract VerifyOTPActivity provideVerifyOTPActivity();

}
