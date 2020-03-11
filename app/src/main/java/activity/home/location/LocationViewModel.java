package activity.home.location;

import activity.basic.BaseViewModel;
import data.DataManager;
import utils.rx.SchedulerProvider;

public class LocationViewModel extends BaseViewModel<LocationNavigator> {
    public LocationViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
