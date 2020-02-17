package data.model.api.login;

import com.google.gson.annotations.SerializedName;

import data.model.api.BaseResponse;

public class LoginResponse extends BaseResponse {

    @SerializedName("result")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User data) {
        this.user = data;
    }

}
