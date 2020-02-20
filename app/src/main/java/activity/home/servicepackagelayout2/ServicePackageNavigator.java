package activity.home.servicepackagelayout2;

import java.util.List;

import activity.basic.BaseNevigator;
import data.model.api.servicepackage2.ServiceResult;

public interface ServicePackageNavigator extends BaseNevigator {

    void onServicePackageSuccessfull(List<ServiceResult> serviceResults);

}
