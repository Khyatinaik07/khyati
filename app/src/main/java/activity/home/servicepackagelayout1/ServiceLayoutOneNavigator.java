package activity.home.servicepackagelayout1;

import java.util.List;

import activity.basic.BaseNevigator;
import dagger.Module;
import data.model.api.servicepackage2.ServiceResult;

@Module
public interface ServiceLayoutOneNavigator extends BaseNevigator {

    void onServicePackageSuccessfull(List<ServiceResult> serviceResults);

}
