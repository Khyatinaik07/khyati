package data.model.api.otpverify;
import com.google.gson.annotations.SerializedName;

import data.model.api.BaseResponse;
import data.model.api.login.User;

public class UserLoginSuccessResponse extends BaseResponse {

    @SerializedName("result")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User data) {
        this.user = data;
    }
}
