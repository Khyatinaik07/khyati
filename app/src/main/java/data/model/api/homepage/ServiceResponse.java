package data.model.api.homepage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import data.model.api.BaseResponse;

public class ServiceResponse extends BaseResponse {

    @SerializedName("result")
    @Expose
    private List<ServiceData> serviceData;

    public List<ServiceData> getServiceData() {
        return serviceData;
    }

    public void setServiceData(List<ServiceData> result) {
        this.serviceData = result;
    }

}