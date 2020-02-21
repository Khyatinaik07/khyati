package di.builder;

import activity.authentication.forgotpassword.ForgotPasswordActivity;
import activity.authentication.forgotpassword.ForgotPasswordProvider;
import activity.authentication.forgotpassword.ForgotPaswordModule;
import activity.authentication.login.LoginActivity;
import activity.authentication.login.LoginModule;
import activity.authentication.login.LoginProvider;
import activity.authentication.otpverify.VerifyOTPActivity;
import activity.authentication.otpverify.VerifyOTPModule;
import activity.authentication.otpverify.VerifyOTProvider;
import activity.authentication.registration.RegistrationActivity;
import activity.authentication.registration.RegistrationActivityProvider;
import activity.authentication.registration.RegistrationModule;
import activity.basic.MainActivity;
import activity.basic.MainActivityModule;
import activity.home.allsubservice.SubServiceAllActivity;
import activity.home.allsubservice.SubServiceAllModule;
import activity.home.allsubservice.SubServiceAllProvider;
import activity.home.breifservicesubtype.SubTypeServiceActivity;
import activity.home.breifservicesubtype.SubTypeServiceModule;
import activity.home.breifservicesubtype.SubTypeServiceProvider;
import activity.home.changePassword.ChangePasswordActivity;
import activity.home.changePassword.ChangePasswordProvider;
import activity.home.changePassword.changePasswordModule;
import activity.home.editprofile.EditProfileActivity;
import activity.home.editprofile.EditProfileModule;
import activity.home.editprofile.EditProfileProvider;
import activity.home.homepagetab.HomePageTabActivity;
import activity.home.homepagetab.HomePageTabModule;
import activity.home.homepagetab.HomePageTabProvider;
import activity.home.preferedservice.PreferedServiceModule;
import activity.home.preferedservice.PreferedServiceProvider;
import activity.home.preferedservice.PrefferedServiceActivity;
import activity.home.servicepackagelayout1.ServiceLayoutOneModule;
import activity.home.servicepackagelayout1.ServiceLayoutOneProvider;
import activity.home.servicepackagelayout1.ServicePackageLayoutOneActivity;
import activity.home.servicepackagelayout1.editpackage.EditPackageActivity;
import activity.home.servicepackagelayout1.editpackage.EditPackageModule;
import activity.home.servicepackagelayout1.editpackage.EditPackageProvider;
import activity.home.servicepackagelayout2.ServicePackageActivity;
import activity.home.servicepackagelayout2.ServicePackageModule;
import activity.home.servicepackagelayout2.ServicePackageProvider;
import activity.home.servicepackagelayout2.specificationdetail.SpecificationDetailActivity;
import activity.home.servicepackagelayout2.specificationdetail.SpecificationDetailModule;
import activity.home.servicepackagelayout2.specificationdetail.SpecificationDetailProvider;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import fragment.homepage.HomePageFragment;
import fragment.homepage.HomePageModule;
import fragment.homepage.HomePageProvider;
import fragment.myprofile.MyProfileFragment;
import fragment.myprofile.MyProfileModule;
import fragment.myprofile.MyProfileProvider;
import fragment.subtypeallservice.SubTypeAllServiceFragment;
import fragment.subtypeallservice.SubTypeAllServiceModule;
import fragment.subtypeallservice.SubTypeAllServiceProvider;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {RegistrationModule.class, RegistrationActivityProvider.class
    })
    abstract RegistrationActivity bindRegistrationActivity();

    @ContributesAndroidInjector(modules = {changePasswordModule.class, ChangePasswordProvider.class})
    abstract ChangePasswordActivity bindChangePasswordActivity();

    @ContributesAndroidInjector(modules = {VerifyOTPModule.class, VerifyOTProvider.class})
    abstract VerifyOTPActivity bindVerifyOTPActivity();

    @ContributesAndroidInjector(modules = {LoginModule.class, LoginProvider.class})
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {ForgotPaswordModule.class, ForgotPasswordProvider.class})
    abstract ForgotPasswordActivity bindForgotPasswordActivity();

    @ContributesAndroidInjector(modules = {MyProfileModule.class, MyProfileProvider.class})
    abstract MyProfileFragment bindMyProfileFragment();

    @ContributesAndroidInjector(modules = {HomePageTabModule.class, HomePageTabProvider.class})
    abstract HomePageTabActivity bindHomePageTabActivity();

    @ContributesAndroidInjector(modules = {EditProfileModule.class, EditProfileProvider.class})
    abstract EditProfileActivity bindEditProfileActivity();

    @ContributesAndroidInjector(modules = {HomePageModule.class, HomePageProvider.class})
    abstract HomePageFragment bindHomePageFragment();

    @ContributesAndroidInjector(modules = {SubTypeAllServiceModule.class, SubTypeAllServiceProvider.class})
    abstract SubTypeAllServiceFragment bindSubTypeAllServiceFragment();

    @ContributesAndroidInjector(modules = {SubServiceAllProvider.class, SubServiceAllModule.class})
    abstract SubServiceAllActivity bindSubServiceAllActivity();

    @ContributesAndroidInjector(modules = {SubTypeServiceProvider.class, SubTypeServiceModule.class})
    abstract SubTypeServiceActivity bindSubTypeServiceActivity();

    @ContributesAndroidInjector(modules = {PreferedServiceModule.class , PreferedServiceProvider.class})
    abstract PrefferedServiceActivity bindPrefferedServiceActivity();

    @ContributesAndroidInjector(modules = {ServicePackageModule.class , ServicePackageProvider.class})
    abstract ServicePackageActivity bindServicePackageActivity();

    @ContributesAndroidInjector(modules = {SpecificationDetailModule.class , SpecificationDetailProvider.class})
    abstract SpecificationDetailActivity bindSpecificationDetailActivity();

    @ContributesAndroidInjector(modules = {ServiceLayoutOneModule.class, ServiceLayoutOneProvider.class})
    abstract ServicePackageLayoutOneActivity bindServicePackageLayoutOneActivity();

    @ContributesAndroidInjector(modules = {EditPackageModule.class , EditPackageProvider.class})
    abstract EditPackageActivity bindEditPackageActivity();
}
