package activity.home.preferedservice;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class PreferedServiceModule {

    @Provides
    public PreferedServiceViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new PreferedServiceViewModel(dataManager,schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(PreferedServiceViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
