package activity.home.preferedservice;

import java.util.List;

import activity.basic.BaseNevigator;
import data.model.api.homepage.ServiceData;

public interface PreferedServiceNavigator extends BaseNevigator {

    void onSeviceFetch(List<ServiceData> list);

}
