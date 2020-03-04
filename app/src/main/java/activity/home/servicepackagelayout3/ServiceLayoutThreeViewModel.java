package activity.home.servicepackagelayout3;

import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.Observer;

import java.util.List;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.servicepackage2.ServicePackageResponse;
import data.model.api.servicepackage2.ServiceResult;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import utils.rx.SchedulerProvider;

public class ServiceLayoutThreeViewModel extends BaseViewModel<ServiceLayoutThreeNavigator> {

    private ObservableBoolean isServiceEmpty = new ObservableBoolean();

    public ServiceLayoutThreeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public Observer<List<ServiceResult>> listObserver = new Observer<List<ServiceResult>>() {
        @Override
        public void onChanged(List<ServiceResult> serviceResults) {
            Log.w("empty",String.valueOf(serviceResults.isEmpty()));
            getNavigator().onServicePackageSuccessfull(serviceResults);
        }
    };

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
