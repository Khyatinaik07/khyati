package activity.home.changePassword;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class changePasswordModule {

    @Provides
    public ChangePasswordViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new ChangePasswordViewModel(dataManager, schedulerProvider);
    }

    @Provides

    public ViewModelProvider.Factory getViewModelFactory(ChangePasswordViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
