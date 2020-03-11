package activity.home.location;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class LocationModule {

    @Provides
    public LocationViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new LocationViewModel(dataManager, schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(LocationViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
