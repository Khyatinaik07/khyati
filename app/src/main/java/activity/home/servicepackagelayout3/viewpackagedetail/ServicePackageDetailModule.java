package activity.home.servicepackagelayout3.viewpackagedetail;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class ServicePackageDetailModule {

    @Provides
    public ServicePackageDetailViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new ServicePackageDetailViewModel(dataManager,schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(ServicePackageDetailViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
