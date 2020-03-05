package activity.home.servicepackagelayout3.viewpackagedetail;

import activity.basic.BaseViewModel;
import data.DataManager;
import utils.rx.SchedulerProvider;

public class ServicePackageDetailViewModel extends BaseViewModel<ServicePackageDetailNavigator> {
    public ServicePackageDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
