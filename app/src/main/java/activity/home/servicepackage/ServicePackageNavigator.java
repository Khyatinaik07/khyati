package activity.home.servicepackage;

import java.util.List;

import activity.basic.BaseNevigator;
import data.model.api.servicepackage.ServiceResult;

public interface ServicePackageNavigator extends BaseNevigator {

    void onServicePackageSuccessfull(List<ServiceResult> serviceResults);

}
