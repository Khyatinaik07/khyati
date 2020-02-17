package activity.authentication.forgotpassword;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class ForgotPaswordModule {

    @Provides
    public ForgotPasswordViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new ForgotPasswordViewModel(dataManager, schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(ForgotPasswordViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
