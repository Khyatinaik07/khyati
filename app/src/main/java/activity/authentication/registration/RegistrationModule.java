package activity.authentication.registration;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class RegistrationModule {

    @Provides
    public RegistrationViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new RegistrationViewModel(dataManager, schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(RegistrationViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
