package activity.home.breifservicesubtype;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class SubTypeServiceModule {

    @Provides
    public SubTypeServiceViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new SubTypeServiceViewModel(dataManager,schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(SubTypeServiceViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
