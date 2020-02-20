package data;

import data.local.db.DbHelper;
import data.local.prefs.PreferencesHelper;
import data.model.api.BaseResponse;
import data.model.api.changePassword.changePasswordRequest;
import data.model.api.homepage.BannerRespose;
import data.model.api.homepage.ServiceResponse;
import data.model.api.login.User;
import data.model.api.otpverify.UserLoginSuccessResponse;
import data.model.api.servicepackage2.ServicePackageResponse;
import data.model.api.signUp.RegisterRequest;
import data.remote.ApiHelper;
import io.reactivex.Single;

public interface DataManager extends DbHelper , PreferencesHelper,ApiHelper {

    Single<UserLoginSuccessResponse> signUpEmailAndSaveData(RegisterRequest searchEventRequest);

    Single<User> saveUserDetailToDB(User userData);

    Single<BaseResponse>changePassword(changePasswordRequest changePasswordRequest);

    Single<BannerRespose> fetchBannerDataandSave();

    Single<ServiceResponse> fetchServiceDataandSave(String id);

    Single<ServicePackageResponse> fetchServicePackageandSave(String id);

}
