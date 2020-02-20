package activity.home.servicepackagelayout2.specificationdetail;

import activity.basic.BaseViewModel;
import data.DataManager;
import utils.rx.SchedulerProvider;

public class SpecificationDetailViewModel extends BaseViewModel<SpecificationDetailNavigator> {
    public SpecificationDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
