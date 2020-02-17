package activity.authentication.login;

import androidx.lifecycle.ViewModelProvider;

import activity.basic.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import data.DataManager;
import utils.rx.SchedulerProvider;

@Module
public class LoginModule {

    @Provides
    public LoginViewModel getViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        return new LoginViewModel(dataManager, schedulerProvider);
    }

    @Provides
    public ViewModelProvider.Factory getViewModelFactory(LoginViewModel loginViewModel)
    {
        return new ViewModelProviderFactory<>(loginViewModel);
    }

}
