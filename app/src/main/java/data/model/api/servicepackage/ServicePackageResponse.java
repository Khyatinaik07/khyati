package data.model.api.servicepackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import data.model.api.BaseResponse;

public class ServicePackageResponse extends BaseResponse {

    @SerializedName("result")
    @Expose
    private List<ServiceResult> result = null;

    public List<ServiceResult> getResult() {
        return result;
    }

    public void setResult(List<ServiceResult> result) {
        this.result = result;
    }

}
