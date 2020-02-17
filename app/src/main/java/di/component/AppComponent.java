package di.component;

import android.app.Application;

import javax.inject.Singleton;

import activity.basic.OnlineServiceApp;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import di.builder.ActivityBuilder;
import di.module.AppModule;
import di.module.ReceiverModule;
import di.module.ServiceModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilder.class, ReceiverModule.class, ServiceModule.class})
public interface AppComponent {

    void inject(OnlineServiceApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

}
