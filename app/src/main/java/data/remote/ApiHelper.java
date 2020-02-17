package data.remote;

import data.model.api.BaseResponse;
import data.model.api.changePassword.changePasswordRequest;
import data.model.api.editprofile.EditProfileRequest;
import data.model.api.editprofile.EditProfileResponse;
import data.model.api.homepage.BannerRespose;
import data.model.api.homepage.ServiceResponse;
import data.model.api.login.LoginRequest;
import data.model.api.login.LoginResponse;
import data.model.api.otpverify.UserLoginSuccessResponse;
import data.model.api.signUp.RegisterRequest;
import io.reactivex.Single;

public interface ApiHelper {

    Single<UserLoginSuccessResponse> signUpUserEmail(RegisterRequest signUpRequest);

    Single<BaseResponse> changePassword(changePasswordRequest changePasswordRequest);

    Single<LoginResponse> loginUser(LoginRequest loginRequest);

    Single<EditProfileResponse> userProfileUpdate(EditProfileRequest editProfileRequest);

    Single<BannerRespose> getBannerData();

    Single<ServiceResponse> getServiceData(String id);
}
