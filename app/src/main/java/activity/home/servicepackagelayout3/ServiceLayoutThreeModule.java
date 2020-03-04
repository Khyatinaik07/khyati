package activity.home.servicepackagelayout3;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class ServiceLayoutThreeModule {

    @Provides
    public ServiceLayoutThreeViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new ServiceLayoutThreeViewModel(dataManager,schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(ServiceLayoutThreeViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
