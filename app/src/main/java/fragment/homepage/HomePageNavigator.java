package fragment.homepage;

import java.util.List;

import activity.basic.BaseNevigator;
import data.model.api.homepage.BannerData;
import data.model.api.homepage.ServiceData;

public interface HomePageNavigator extends BaseNevigator {

    void onBannerFetchSuccessfully(List<BannerData> bannerData);

    void onServiceFetchSuccessfully(List<ServiceData> serviceData);

    void onBriefServiceFetchSuccessfully(List<ServiceData> serviceData);

}
