package data.model.api.homepage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import data.model.api.BaseResponse;

public class BannerRespose extends BaseResponse {

    @SerializedName("result")
    @Expose
    private List<BannerData> bannerData;

    public List<BannerData> getBannerData()
    {
        return bannerData;
    }
    public void setBannerData(List<BannerData> bannerData)
    {
        this.bannerData=bannerData;
    }


}
