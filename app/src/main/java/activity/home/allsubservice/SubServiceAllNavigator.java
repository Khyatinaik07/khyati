package activity.home.allsubservice;

import java.util.List;

import activity.basic.BaseNevigator;
import data.model.api.homepage.ServiceData;

public interface SubServiceAllNavigator extends BaseNevigator {

    void onServiceNameAvailable(List<ServiceData> serviceData, String id);

}
