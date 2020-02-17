package fragment.subtypeallservice;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import di.FragmentScope;
import utils.rx.SchedulerProvider;

@Module
public class SubTypeAllServiceModule {

    @Provides
    public SubTypeAllServiceViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new SubTypeAllServiceViewModel(dataManager,schedulerProvider);
    }

    @Provides
    @FragmentScope
    public ViewModelProvider.Factory getViewModelFactory(SubTypeAllServiceViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
