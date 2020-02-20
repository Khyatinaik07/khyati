package activity.home.servicepackagelayout2;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class ServicePackageModule {

    @Provides
    public ServicePackageViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new ServicePackageViewModel(dataManager, schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(ServicePackageViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
