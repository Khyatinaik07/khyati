package activity.authentication.otpverify;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class VerifyOTPModule {

    @Provides
    public VerifyOTPViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new VerifyOTPViewModel(dataManager,schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(VerifyOTPViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}

