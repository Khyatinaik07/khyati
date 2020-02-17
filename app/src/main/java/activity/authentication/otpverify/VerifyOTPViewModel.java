package activity.authentication.otpverify;

import activity.basic.BaseViewModel;
import data.DataManager;
import utils.rx.SchedulerProvider;

public class VerifyOTPViewModel extends BaseViewModel<VerifyOTPNevigator> {

    public VerifyOTPViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
