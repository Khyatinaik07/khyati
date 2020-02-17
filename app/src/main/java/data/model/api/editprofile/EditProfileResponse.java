package data.model.api.editprofile;

import com.google.gson.annotations.SerializedName;

import data.model.api.BaseResponse;
import data.model.api.login.User;

public class EditProfileResponse extends BaseResponse {

    @SerializedName("result")
    private User user;

    public User getUser(){
        return user;
    }

    public void setUser(User user)
    {
        this.user=user;
    }

}
