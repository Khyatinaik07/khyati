package activity.home.allsubservice;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class SubServiceAllModule {

    @Provides
    public SubServiceAllViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new SubServiceAllViewModel(dataManager,schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(SubServiceAllViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
