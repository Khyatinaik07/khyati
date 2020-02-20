package data.remote;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import data.model.api.BaseResponse;
import data.model.api.changePassword.changePasswordRequest;
import data.model.api.editprofile.EditProfileRequest;
import data.model.api.editprofile.EditProfileResponse;
import data.model.api.homepage.BannerRespose;
import data.model.api.homepage.ServiceResponse;
import data.model.api.login.LoginRequest;
import data.model.api.login.LoginResponse;
import data.model.api.otpverify.UserLoginSuccessResponse;
import data.model.api.servicepackage2.ServicePackageResponse;
import data.model.api.signUp.RegisterRequest;
import io.reactivex.Single;
import utils.CommonUtils;

import static data.remote.ApiEndPoints.ENDPOINT_BANNER_DATA;
import static data.remote.ApiEndPoints.ENDPOINT_CHANGE_PASSWORD;
import static data.remote.ApiEndPoints.ENDPOINT_EDIT_PROFILE;
import static data.remote.ApiEndPoints.ENDPOINT_LOGIN;
import static data.remote.ApiEndPoints.ENDPOINT_SERVICE_PACKAGE_DATA;
import static data.remote.ApiEndPoints.ENDPOINT_SIGN_UP;
import static data.remote.ApiEndPoints.ENDPONT_SERVICE_DATA;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public Single<UserLoginSuccessResponse> signUpUserEmail(RegisterRequest signUpRequest) {
        return Rx2AndroidNetworking
                .post(ENDPOINT_SIGN_UP)
                .addApplicationJsonBody(signUpRequest)
                .build()
                .getObjectSingle(UserLoginSuccessResponse.class);
    }

    @Override
    public Single<BaseResponse> changePassword(changePasswordRequest changePasswordRequest) {
        return Rx2AndroidNetworking
                .post(ENDPOINT_CHANGE_PASSWORD)
                .addApplicationJsonBody(changePasswordRequest)
                .build()
                .getObjectSingle(BaseResponse.class);
    }

    @Override
    public Single<LoginResponse> loginUser(LoginRequest loginRequest) {
        return Rx2AndroidNetworking
                .post(ENDPOINT_LOGIN)
                .addHeaders("Authorization", "Basic" + CommonUtils.toBase64(loginRequest.getPhoneno() + ":" + loginRequest.getPssword()))
                .addApplicationJsonBody(loginRequest)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<EditProfileResponse> userProfileUpdate(EditProfileRequest editProfileRequest) {
        return Rx2AndroidNetworking
                .post(ENDPOINT_EDIT_PROFILE)
                .addApplicationJsonBody(editProfileRequest)
                .build()
                .getObjectSingle(EditProfileResponse.class);
    }

    @Override
    public Single<BannerRespose> getBannerData() {
        return Rx2AndroidNetworking
                .get(ENDPOINT_BANNER_DATA)
                .build()
                .getObjectSingle(BannerRespose.class);
    }

    @Override
    public Single<ServiceResponse> getServiceData(String id) {
        return Rx2AndroidNetworking
                .post(ENDPONT_SERVICE_DATA)
                .addBodyParameter("parent_id",id)
                .build()
                .getObjectSingle(ServiceResponse.class);
    }

    @Override
    public Single<ServicePackageResponse> getServicePackageData(String id) {
        return Rx2AndroidNetworking
                .post(ENDPOINT_SERVICE_PACKAGE_DATA)
                .addBodyParameter("parent_id",id)
                .build()
                .getObjectSingle(ServicePackageResponse.class);
    }


}