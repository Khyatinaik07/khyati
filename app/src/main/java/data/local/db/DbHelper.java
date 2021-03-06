package data.local.db;

import androidx.lifecycle.LiveData;

import java.util.List;

import data.model.api.homepage.BannerData;
import data.model.api.homepage.ServiceData;
import data.model.api.login.User;
import data.model.api.servicepackage2.ServiceResult;
import io.reactivex.Completable;
import io.reactivex.SingleSource;

public interface DbHelper {

    LiveData<User> getAttendeeProfileLive();

    LiveData<User> getUserProfileLive(int userid);

    void saveUserProfileToDb(User attendee);

    Completable clearAllData();

    LiveData<List<BannerData>> getBannerDataLive();

    void saveBannerDatatoDb(List<BannerData> bannerData);

    LiveData<List<ServiceData>> getServiceLive(int i,String id);

    SingleSource<ServiceData> saveServiceDatatoDb(List<ServiceData> serviceData);

    LiveData<List<ServiceData>> getAllServiceNameLive(int order,String id);

    LiveData<List<ServiceData>> getAllSearviceNameFromDB(int orderBy,String id,String input);

    void saveServicePackagetoDb(List<ServiceResult> serviceResults);

    LiveData<List<ServiceResult>> getServicePackageLive(String id);

    LiveData<List<ServiceResult>> getServiceNameLive(String id);

    LiveData<List<ServiceResult>> getSpecification(String id);

}
