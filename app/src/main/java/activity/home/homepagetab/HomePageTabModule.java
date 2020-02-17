package activity.home.homepagetab;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class HomePageTabModule {

    @Provides
    public HomePageTabViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new HomePageTabViewModel(dataManager, schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(HomePageTabViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
