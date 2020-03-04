package activity.home.servicepackagelayout3;

import java.util.List;

import activity.basic.BaseNevigator;
import data.model.api.servicepackage2.ServiceResult;

public interface ServiceLayoutThreeNavigator extends BaseNevigator {

    void onServicePackageSuccessfull(List<ServiceResult> serviceResults);
}
