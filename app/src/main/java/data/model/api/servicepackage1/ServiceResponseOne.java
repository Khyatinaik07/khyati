package data.model.api.servicepackage1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import data.model.api.BaseResponse;

public class ServiceResponseOne extends BaseResponse {

    @SerializedName("result")
    @Expose
    private List<ServiceResultOne> result = null;

    public List<ServiceResultOne> getResult() {
        return result;
    }

    public void setResult(List<ServiceResultOne> result) {
        this.result = result;
    }

}

