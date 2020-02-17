package fragment.homepage;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import di.FragmentScope;
import utils.rx.SchedulerProvider;

@Module
public class HomePageModule {

    @Provides
    public HomePageViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new HomePageViewModel(dataManager, schedulerProvider);
    }

    @Provides
    @FragmentScope
    public ViewModelProvider.Factory getViewModelFactory(HomePageViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
