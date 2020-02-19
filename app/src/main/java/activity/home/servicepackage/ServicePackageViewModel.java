package activity.home.servicepackage;

import android.util.Log;

import androidx.lifecycle.Observer;

import java.util.List;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.servicepackage.ServicePackageResponse;
import data.model.api.servicepackage.ServiceResult;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import utils.rx.SchedulerProvider;

public class ServicePackageViewModel extends BaseViewModel<ServicePackageNavigator> {

    public ServicePackageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public Observer<List<ServiceResult>> listObserver = new Observer<List<ServiceResult>>() {
        @Override
        public void onChanged(List<ServiceResult> serviceResults) {
            getNavigator().onServicePackageSuccessfull(serviceResults);
        }
    };

    public void setServicePackage(String id)
    {
        getNavigator().showLoading();

        Disposable disposable =
                getDataManager()
                .fetchServicePackageandSave(id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ServicePackageResponse>() {
                    @Override
                    public void accept(ServicePackageResponse servicePackageResponse) throws Exception {
                        getNavigator().hideLoading();
                        Log.w("success", "service package data success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getNavigator().hideLoading();
                        getNavigator().handleError(throwable);
                        Log.e("error","error while getting service package");
                    }
                });

        getCompositeDisposable().add(disposable);
    }
}
