package activity.home.servicepackagelayout1;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class ServiceLayoutOneModule {

    @Provides
    public ServiceLayoutOneViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new ServiceLayoutOneViewModel(dataManager,schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(ServiceLayoutOneViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
