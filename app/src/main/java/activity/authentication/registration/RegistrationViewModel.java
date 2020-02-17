package activity.authentication.registration;

import android.util.Log;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.otpverify.UserLoginSuccessResponse;
import data.model.api.signUp.RegisterRequest;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import utils.rx.SchedulerProvider;

public class RegistrationViewModel extends BaseViewModel<RegistrationNevigator> {

    public RegistrationViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        super(dataManager,schedulerProvider);
    }

    void SignUp(String fname,String lname,String email,String mno,String password,String code)
    {
        getNavigator().showLoading();

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstname(fname);
        registerRequest.setLastname(lname);
        registerRequest.setEmailid(email);
        registerRequest.setMobilenumber(mno);
        registerRequest.setPassword(password);
        registerRequest.setCountryCode(code);
        registerRequest.setFcmid("0011");

        Disposable disposable = getDataManager()
            .signUpEmailAndSaveData(registerRequest)
            .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
            .subscribe(new Consumer<UserLoginSuccessResponse>() {
                @Override
                public void accept(UserLoginSuccessResponse userLoginSuccessResponse) throws Exception {
                    getNavigator().hideLoading();
                    if (userLoginSuccessResponse.isError()) {

                        getNavigator().showMessage(userLoginSuccessResponse.getMessage());
                    }
                    else
                    {
                        Log.e("no error","no error");
                    }
                    getNavigator().onRegistrationComplete(userLoginSuccessResponse.getUser());
                    Log.e("success","logged in");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.e("Email Signup ", "Error while signup", throwable);
                    getNavigator().hideLoading();
                    getNavigator().handleError(throwable);
                }
            });
        getCompositeDisposable().add(disposable);
    }
}
