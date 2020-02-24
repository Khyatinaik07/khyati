package data.local.db;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import data.model.api.homepage.BannerData;
import data.model.api.homepage.ServiceData;
import data.model.api.login.User;
import data.model.api.servicepackage2.ServiceResult;
import io.reactivex.Completable;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

   @Override
    public LiveData<User> getAttendeeProfileLive() {
        return  mAppDatabase.serviceProviderDao().getAttendeeProfileLive();
    }

    @Override
    public LiveData<User> getUserProfileLive(int userid) {
        return  mAppDatabase.serviceProviderDao().getUserProfileLive(userid);
    }

    @Override
    public void saveUserProfileToDb(User attendee) {
        mAppDatabase.serviceProviderDao().saveCustomerProfile(attendee);
    }

    @Override
    public Completable clearAllData() {
        return Completable
                .fromAction(new Action() {
                    @Override
                    public void run() {
                        mAppDatabase.clearAllTables();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public LiveData<List<BannerData>> getBannerDataLive() {
        return mAppDatabase.serviceProviderDao().getBaneerData();
    }

    @Override
    public void saveBannerDatatoDb(List<BannerData> bannerData) {
        mAppDatabase.serviceProviderDao().saveBannerData(bannerData);
    }

    @Override
    public LiveData<List<ServiceData>> getServiceLive(int i,String id) {
        return mAppDatabase.serviceProviderDao().getServiceLive(i,id);
    }

    @Override
    public SingleSource<ServiceData> saveServiceDatatoDb(List<ServiceData> serviceData) {
        mAppDatabase.serviceProviderDao().saveServiceData(serviceData);
        return null;
    }

    @Override
    public LiveData<List<ServiceData>> getAllServiceNameLive(int order,String id) {
        return mAppDatabase.serviceProviderDao().getAllServiceNameLive(order,id);
    }

    @Override
    public void saveServicePackagetoDb(List<ServiceResult> serviceResults) {
        mAppDatabase.serviceProviderDao().saveServicePackageData(serviceResults);
    }

    @Override
    public LiveData<List<ServiceResult>> getServicePackageLive(String id) {
        return mAppDatabase.serviceProviderDao().getServicePackageLive(id);
    }

    @Override
    public LiveData<List<ServiceResult>> getServiceNameLive(String id) {
        return mAppDatabase.serviceProviderDao().getServiceNameLive(id);
    }

}