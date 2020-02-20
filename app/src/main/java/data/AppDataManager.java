package data;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import data.local.db.DbHelper;
import data.local.prefs.PreferencesHelper;
import data.model.api.BaseResponse;
import data.model.api.changePassword.changePasswordRequest;
import data.model.api.editprofile.EditProfileRequest;
import data.model.api.editprofile.EditProfileResponse;
import data.model.api.homepage.BannerData;
import data.model.api.homepage.BannerRespose;
import data.model.api.homepage.ServiceData;
import data.model.api.homepage.ServiceResponse;
import data.model.api.login.LoginRequest;
import data.model.api.login.LoginResponse;
import data.model.api.login.User;
import data.model.api.otpverify.UserLoginSuccessResponse;
import data.model.api.servicepackage2.ServicePackageResponse;
import data.model.api.servicepackage2.ServiceResult;
import data.model.api.signUp.RegisterRequest;
import data.remote.ApiHelper;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import utils.logger.AnalyticsLogger;

@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final DbHelper mDbHelper;

    private final PreferencesHelper mPreferencesHelper;
    private SharedPreferences preferences;
    Context context;

    @Inject
    AnalyticsLogger mAnalyticsLogger;

    @Inject
    public AppDataManager(DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public void saveBackgroundColor(int eventBackgroundColor) {
        mPreferencesHelper.saveBackgroundColor(eventBackgroundColor);
    }

    @Override
    public int getBackgroundColor() {
        return mPreferencesHelper.getBackgroundColor();
    }

    @Override
    public Single<UserLoginSuccessResponse> signUpEmailAndSaveData(RegisterRequest searchEventRequest) {
        return signUpUserEmail(searchEventRequest)
//                .flatMap((Function<UserLoginSuccessResponse, Single<UserLoginSuccessResponse>>) loginResponse -> getBucketDetail().map(bucketDetailResponse -> loginResponse))
                .flatMap((Function<UserLoginSuccessResponse, SingleSource<UserLoginSuccessResponse>>) loginResponse -> {
                    if (!loginResponse.isError() && loginResponse.getUser() != null) {
                        Log.e("data", "user data");
                        return saveUserDetailToDB(loginResponse.getUser()).map(userData -> loginResponse);
                    } else {
                        Log.e("error", "user data error");
                        Log.e("error", loginResponse.getMessage());

                    }
                    return Single.just(loginResponse);
                });
    }

    @Override
    public void saveEventColor(int eventColor) {
        mPreferencesHelper.saveEventColor(eventColor);
    }

    @Override
    public int getEventColor() {
        return mPreferencesHelper.getEventColor();
    }

    @Override
    public Single<UserLoginSuccessResponse> signUpUserEmail(RegisterRequest signUpRequest) {
        return mApiHelper.signUpUserEmail(signUpRequest);
    }

    @Override
    public Single<User> saveUserDetailToDB(User userData) {
        return Single.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                if (userData != null) {
                    saveUserProfileToDb(userData);
                    setUserID(userData.getCustomerId());
                    Log.e("user id data manager", String.valueOf(userData.getCustomerId()));

                    //  List<String> firebaseTopics = new ArrayList<>();
                    //  firebaseTopics.add(String.format(Locale.getDefault(), AppConstants.ATTENDEE_FIREBASE_TOPIC, userData.getUserId()));
                    // AppDataManager.this.saveFirebaseUserTopic(firebaseTopics);

                    // Subscribe to firebase topics for notifications
                    // NetworkUtils.registerToFirebaseTopics(AppDataManager.this.getFirebaseUserTopic());

                    // Add user id to firebase analytics
                    mAnalyticsLogger.setUserId(Integer.valueOf(userData.getCustomerId()));

                } else {
                    Log.e("empty", "cannot save empty data");
                    throw new IllegalArgumentException("Cannot save empty user data to database");
                }
                return userData;
            }
        });
    }

    @Override
    public Single<BaseResponse> changePassword(changePasswordRequest changePasswordRequest) {
        return mApiHelper.changePassword(changePasswordRequest);
    }

    @Override
    public Single<BannerRespose> fetchBannerDataandSave() {
        return getBannerData()
                .map(BannerRespose -> {
                    if (BannerRespose.isError() && BannerRespose.getBannerData() != null) {
                        Log.w("data","banner data get");
                        saveBannerDatatoDb(BannerRespose.getBannerData());
                    }
                    else {
                        Log.e("not get","banner data not get");
                    }
                return BannerRespose;

                });
    }

    @Override
    public Single<ServiceResponse> fetchServiceDataandSave(String id) {
        return getServiceData(id)
                .map(serviceResponse -> {
                    if (serviceResponse.isError() && serviceResponse.getServiceData() != null) {
                        saveServiceDatatoDb(serviceResponse.getServiceData());
                    }
                    else {
                        Log.e("not get","service data not get");
                    }
                    return serviceResponse;

                });
    }

    @Override
    public Single<ServicePackageResponse> fetchServicePackageandSave(String id) {
        return mApiHelper.getServicePackageData(id)
                .map(servicePackageResponse -> {
                    if (servicePackageResponse.isError() && servicePackageResponse.getResult() != null){
                        Log.w("success","service package get");
                        saveServicePackagetoDb(servicePackageResponse.getResult());
                    }
                    else
                    {
                        Log.e("error","service package data not get");
                    }
                    return servicePackageResponse;
                });
    }

    @Override
    public Single<LoginResponse> loginUser(LoginRequest loginRequest) {
        return mApiHelper.loginUser(loginRequest)
                .flatMap(new Function<LoginResponse, SingleSource<? extends LoginResponse>>() {
                    @Override
                    public SingleSource<LoginResponse> apply(LoginResponse loginResponse) throws Exception {
                        if (loginResponse.isError() && loginResponse.getUser() != null) {

                            return saveUserDetailToDB(loginResponse.getUser()).map(user -> loginResponse);
                        } else {
                            Log.e("error", "error");
                        }
                        return Single.just(loginResponse);
                    }
                });
    }

    @Override
    public Single<EditProfileResponse> userProfileUpdate(EditProfileRequest editProfileRequest) {
        return mApiHelper.userProfileUpdate(editProfileRequest).map(new Function<EditProfileResponse, EditProfileResponse>() {
            @Override
            public EditProfileResponse apply(EditProfileResponse editProfileResponse) {
                if (editProfileResponse.isError() && editProfileResponse.getUser() != null) {
                    Log.e("user", "user data");
                    saveUserProfileToDb(editProfileResponse.getUser());
                } else {
                    Log.e("error", editProfileResponse.getMessage());
                }
                return editProfileResponse;
            }
        });
    }

    @Override
    public Single<BannerRespose> getBannerData() {

        return mApiHelper.getBannerData();
    }

    @Override
    public Single<ServiceResponse> getServiceData(String id) {
        return mApiHelper.getServiceData(id);
    }

    @Override
    public Single<ServicePackageResponse> getServicePackageData(String id) {
        return mApiHelper.getServicePackageData(id);
    }


    @Override
    public Integer getUserID() {
        return mPreferencesHelper.getUserID();
    }

    @Override
    public LiveData<String> getUserDataObserver() {
        return mPreferencesHelper.getUserDataObserver();
    }

    @Override
    public void setUserID(Integer userID) {
        mPreferencesHelper.setUserID(userID);
    }

 /*   @Override
    public void saveFirebaseUserTopic(List<String> topic) {
        mPreferencesHelper.saveFirebaseUserTopic(topic);
    }

    @Override
    public List<String> getFirebaseUserTopic() {
        return mPreferencesHelper.getFirebaseUserTopic();
    }  */

    @Override
    public LiveData<User> getAttendeeProfileLive() {
        return mDbHelper.getAttendeeProfileLive();
    }

    @Override
    public LiveData<User> getUserProfileLive(int userid) {
        return mDbHelper.getUserProfileLive(userid);
    }

    @Override
    public void saveUserProfileToDb(User attendee) {
        mDbHelper.saveUserProfileToDb(attendee);
    }

    @Override
    public Completable clearAllData() {
        return mDbHelper.clearAllData().andThen(clearAllPrefs());
    }

    @Override
    public LiveData<List<BannerData>> getBannerDataLive() {
        return mDbHelper.getBannerDataLive();
    }

    @Override
    public void saveBannerDatatoDb(List<BannerData> bannerData) {
        mDbHelper.saveBannerDatatoDb(bannerData);
    }

    @Override
    public LiveData<List<ServiceData>> getServiceLive(int i,String id) {
        return mDbHelper.getServiceLive(i,id);
    }

    @Override
    public SingleSource<ServiceData> saveServiceDatatoDb(List<ServiceData> serviceData) {
        return mDbHelper.saveServiceDatatoDb(serviceData);
    }

    @Override
    public LiveData<List<ServiceData>> getAllServiceNameLive(int order,String id) {
        return mDbHelper.getAllServiceNameLive(order,id);
    }

    @Override
    public void saveServicePackagetoDb(List<ServiceResult> serviceResults) {
        mDbHelper.saveServicePackagetoDb(serviceResults);
    }

    @Override
    public LiveData<List<ServiceResult>> getServicePackageLive(String id) {
        return mDbHelper.getServicePackageLive(id);
    }

    @Override
    public Completable clearAllPrefs() {
        return mPreferencesHelper.clearAllPrefs();
    }
}
