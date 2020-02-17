package activity.home.homepagetab;

import activity.basic.BaseViewModel;
import data.DataManager;
import utils.rx.SchedulerProvider;

public class HomePageTabViewModel extends BaseViewModel<HomePageTabNevigator> {

    public HomePageTabViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
