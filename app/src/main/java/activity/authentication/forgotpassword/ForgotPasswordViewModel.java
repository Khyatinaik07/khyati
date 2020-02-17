package activity.authentication.forgotpassword;

import activity.basic.BaseViewModel;
import data.DataManager;
import utils.rx.SchedulerProvider;

public class ForgotPasswordViewModel extends BaseViewModel<ForgotPasswordNevigtor> {

    public ForgotPasswordViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

}
