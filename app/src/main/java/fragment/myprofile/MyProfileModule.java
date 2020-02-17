package fragment.myprofile;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import di.FragmentScope;
import utils.rx.SchedulerProvider;

@Module
public class MyProfileModule {

    @Provides
    public MyProfileViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new MyProfileViewModel(dataManager, schedulerProvider);
    }

    @Provides
    @FragmentScope
    public ViewModelProvider.Factory getViewModelFactory(MyProfileViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
