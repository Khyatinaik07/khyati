package activity.home.preferedservice;

import android.util.Log;

import androidx.lifecycle.Observer;

import java.util.List;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.homepage.ServiceData;
import data.model.api.homepage.ServiceResponse;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import utils.rx.SchedulerProvider;

public class PreferedServiceViewModel extends BaseViewModel<PreferedServiceNavigator> {

    public PreferedServiceViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public Observer<List<ServiceData>> serviceObserver = new Observer<List<ServiceData>>() {
        @Override
        public void onChanged(List<ServiceData> serviceData) {
            getNavigator().onSeviceFetch(serviceData);
        }
    };

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
                        Log.w("success", "prefered service success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getNavigator().hideLoading();
                        getNavigator().handleError(throwable);
                        Log.w("error","error in prefered service");
                    }
                });

        getCompositeDisposable().add(disposable);
    }
}
