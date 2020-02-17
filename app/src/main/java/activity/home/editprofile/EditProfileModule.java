package activity.home.editprofile;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class EditProfileModule {

    @Provides
    public EditProfileViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new EditProfileViewModel(dataManager,schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(EditProfileViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }

}
