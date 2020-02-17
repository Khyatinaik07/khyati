package activity.home.editprofile;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class EditProfileProvider {

    @ContributesAndroidInjector(modules = EditProfileModule.class)
    abstract EditProfileActivity provideEditProfileActivity();

}
