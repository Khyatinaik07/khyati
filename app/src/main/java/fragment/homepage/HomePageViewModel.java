package fragment.homepage;

import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.Observer;

import java.util.List;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.homepage.BannerData;
import data.model.api.homepage.BannerRespose;
import data.model.api.homepage.ServiceData;
import data.model.api.homepage.ServiceResponse;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import utils.rx.SchedulerProvider;

public class HomePageViewModel extends BaseViewModel<HomePageNavigator> {

    ObservableInt orderBy = new ObservableInt();
    ObservableBoolean isDataEmpty = new ObservableBoolean();

    public HomePageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public Observer<List<BannerData>> bannerObserver = new Observer<List<BannerData>>() {
        @Override
        public void onChanged(List<BannerData> bannerData) {
            getNavigator().onBannerFetchSuccessfully(bannerData);
        }
    };

    public Observer<List<ServiceData>> serviceObserver = new Observer<List<ServiceData>>() {
        @Override
        public void onChanged(List<ServiceData> serviceData) {
            getNavigator().onServiceFetchSuccessfully(serviceData);
        }
    };

    public Observer<List<ServiceData>> briefServiceObserver = new Observer<List<ServiceData>>() {
        @Override
        public void onChanged(List<ServiceData> serviceData) {
            getNavigator().onBriefServiceFetchSuccessfully(serviceData);
        }
    };

    public void getBannerData() {
        getNavigator().showLoading();

        Disposable disposable =
                getDataManager()
                        .fetchBannerDataandSave()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<BannerRespose>() {
                            @Override
                            public void accept(BannerRespose bannerRespose) throws Exception {
                                getNavigator().hideLoading();
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

    public void getServiceData() {
        getNavigator().showLoading();

        Disposable disposable =
                getDataManager()
                        .fetchServiceDataandSave("0")
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
                                Log.e("error", "error while get service");
                            }
                        });
        getCompositeDisposable().add(disposable);
    }

    private ObservableInt getOrderBy(){
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy.set(orderBy);
    }

    public void setIsDataEmpty(boolean isDataEmpty) {
        this.isDataEmpty.set(isDataEmpty);
    }

}
