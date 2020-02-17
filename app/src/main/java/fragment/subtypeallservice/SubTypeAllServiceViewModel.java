package fragment.subtypeallservice;

import android.util.Log;

import androidx.databinding.ObservableBoolean;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.homepage.ServiceResponse;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import utils.rx.SchedulerProvider;

public class SubTypeAllServiceViewModel extends BaseViewModel<SubTypeAllServiceNavigator> {

    private ObservableBoolean isServiceDataEmpty = new ObservableBoolean();

    public ObservableBoolean getIsServiceDataEmpty()
    {
        return isServiceDataEmpty;
    }

    public void setIsServiceDataEmpty(Boolean isServiceDataEmpty) {
        this.isServiceDataEmpty.set(isServiceDataEmpty);
    }

    public SubTypeAllServiceViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
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
                        Log.w("success","success get service data");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getNavigator().hideLoading();
                        getNavigator().handleError(throwable);
                        Log.e("error","error while fetch service"+id);
                    }
                });

        getCompositeDisposable().add(disposable);

    }


}
