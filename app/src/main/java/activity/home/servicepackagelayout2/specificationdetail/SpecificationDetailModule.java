package activity.home.servicepackagelayout2.specificationdetail;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class SpecificationDetailModule {

    @Provides
    public SpecificationDetailViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new SpecificationDetailViewModel(dataManager,schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(SpecificationDetailViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
