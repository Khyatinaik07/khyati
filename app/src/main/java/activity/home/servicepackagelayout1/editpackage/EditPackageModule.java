package activity.home.servicepackagelayout1.editpackage;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class EditPackageModule {

    @Provides
    public EditPackageViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new EditPackageViewModel(dataManager,schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(EditPackageViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
