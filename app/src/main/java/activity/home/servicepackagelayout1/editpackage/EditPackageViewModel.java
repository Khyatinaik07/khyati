package activity.home.servicepackagelayout1.editpackage;

import activity.basic.BaseViewModel;
import data.DataManager;
import utils.rx.SchedulerProvider;

public class EditPackageViewModel extends BaseViewModel<EditPackageNavigator> {

    public EditPackageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
