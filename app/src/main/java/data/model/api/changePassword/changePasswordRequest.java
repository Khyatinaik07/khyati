package data.model.api.changePassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class changePasswordRequest {

    @SerializedName("customer_id")
    @Expose
    private String customerId;

    @SerializedName("old_password")
    @Expose
    private String oldPassword;

    @SerializedName("new_password")
    @Expose
    private String newPassword;

     public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
