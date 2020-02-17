package activity.home.breifservicesubtype;

import android.util.Log;

import androidx.databinding.ObservableBoolean;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.homepage.ServiceResponse;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import utils.rx.SchedulerProvider;

public class SubTypeServiceViewModel extends BaseViewModel<SubTypeServiceNavigator> {

    private ObservableBoolean isServiceEmpty = new ObservableBoolean();

    public SubTypeServiceViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public ObservableBoolean getIsServiceEmpty() {
        return isServiceEmpty;
    }

    public void setIsServiceEmpty(Boolean isServiceEmpty) {
        this.isServiceEmpty.set(isServiceEmpty);
    }

    public void getService(String id)
    {
        getNavigator().showLoading();

        Disposable disposable =
                getDataManager()
                .fetchServiceDataandSave(id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ServiceResponse>() {
                    @Override
                    public void accept(ServiceResponse serviceResponse) throws Exception {
                        getNavigator().hideLoading();
                        Log.w("success", "success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getNavigator().hideLoading();
                        getNavigator().handleError(throwable);
                        Log.e("error","Error while fetch Sub type service"+throwable.toString());
                    }
                });
        getCompositeDisposable().add(disposable);
    }
}
