package data.model.api;

import com.google.gson.annotations.SerializedName;

import static utils.AppConstants.StatusCodes.STATUS_FALSE;
import static utils.AppConstants.StatusCodes.STATUS_TRUE;

public class BaseResponse {

    @SerializedName("status")
    private Boolean status = null;

    @SerializedName("message")
    private String message = "";

    public boolean isError() {
        return (status == STATUS_FALSE || status == STATUS_TRUE);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean code) {
        this.status = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
