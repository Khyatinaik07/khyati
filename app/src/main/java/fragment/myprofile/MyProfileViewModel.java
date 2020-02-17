package fragment.myprofile;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.login.GenericProfileInterface;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import utils.rx.SchedulerProvider;

public class MyProfileViewModel extends BaseViewModel<MyProfileNevigator> implements Observer<GenericProfileInterface> {

    MediatorLiveData<GenericProfileInterface> liveDataMerger = new MediatorLiveData<>();

    public MyProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void logout()
    {
        Disposable disposable =
                getDataManager().clearAllData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                               @Override
                               public void run() throws Exception {
                                   getNavigator().hideLoading();
                                   getNavigator().onLogoutSuccess();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   getNavigator().hideLoading();
                                   getNavigator().handleError(throwable);
                                   getNavigator().onLogoutSuccess();
                               }
                           }
                );
        getCompositeDisposable().add(disposable);
    }

    void getProfileDataLive(int userid) {
            liveDataMerger.addSource(getDataManager().getUserProfileLive(userid), this);

    }

    @Override
    public void onChanged(GenericProfileInterface genericProfileInterface) {
        liveDataMerger.setValue(genericProfileInterface);
    }
}
