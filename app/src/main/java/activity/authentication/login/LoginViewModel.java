package activity.authentication.login;

import android.util.Log;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.login.LoginRequest;
import data.model.api.login.LoginResponse;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import utils.rx.SchedulerProvider;

public class LoginViewModel extends BaseViewModel<LoginNevigation> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void doLogin(String mno, String password) {
        getNavigator().showLoading();


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhoneno(mno);
        loginRequest.setPssword(password);

        Disposable disposable =
                getDataManager()
                        .loginUser(loginRequest)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<LoginResponse>() {
                                       @Override
                                       public void accept(LoginResponse loginResponse) throws Exception {
                                           getNavigator().hideLoading();
                                           if (!loginResponse.getStatus())
                                           {
                                               getNavigator().showMessage(loginResponse.getMessage());
                                               //Log.e("code", loginResponse.getMessage());
                                           }
                                           else
                                           {
                                               getNavigator().showMessage(loginResponse.getMessage());
                                               getNavigator().onLoginComplete(loginResponse.getUser());
                                               //Log.e("success", "login successfull");
                                           }
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept(Throwable throwable) throws Exception {
                                           getNavigator().hideLoading();
                                           getNavigator().handleError(throwable);
                                           Log.e("error", throwable.toString());
                                       }
                                   }
                        );
        getCompositeDisposable().add(disposable);
    }
}
