package activity.home.changePassword;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.BaseResponse;
import data.model.api.changePassword.changePasswordRequest;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import utils.rx.SchedulerProvider;

public class ChangePasswordViewModel extends BaseViewModel<ChangePasswordNevigator> {

    public ChangePasswordViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void changePassword(String oldPass,String newPass ) {
        getNavigator().showLoading();
        changePasswordRequest changePasswordRequest = new changePasswordRequest();
        changePasswordRequest.setCustomerId(String.valueOf(getDataManager().getUserID()));
        changePasswordRequest.setOldPassword(oldPass);
        changePasswordRequest.setNewPassword(newPass);
        Disposable disposable =
                getDataManager()
                        .changePassword(changePasswordRequest)
                        .delay(300, TimeUnit.MILLISECONDS)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<BaseResponse>() {
                            @Override
                            public void accept(BaseResponse baseResponse) throws Exception {
                                getNavigator().hideLoading();
                                getNavigator().showMessage(baseResponse.getMessage());
                                getNavigator().onChangePasswordComplete();
                                Log.e("success","Success");
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getNavigator().hideLoading();
                                getNavigator().handleError(throwable);
                            }
                        });
        getCompositeDisposable().add(disposable);
    }
}
