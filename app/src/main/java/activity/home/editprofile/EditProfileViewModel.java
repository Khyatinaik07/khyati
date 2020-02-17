package activity.home.editprofile;

import android.util.Log;

import androidx.lifecycle.Observer;

import activity.basic.BaseViewModel;
import data.DataManager;
import data.model.api.editprofile.EditProfileRequest;
import data.model.api.editprofile.EditProfileResponse;
import data.model.api.login.User;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import utils.rx.SchedulerProvider;

public class EditProfileViewModel extends BaseViewModel<EditProfileNevigator> {

    public EditProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public Observer<User> profileObserver = new Observer<User>() {
        @Override
        public void onChanged(User user) {
            getNavigator().onUserProfile(user);
        }
    };


    public void updateProfile(String firstname, String lastname, String email, String address) {
        getNavigator().showLoading();

        EditProfileRequest editProfileRequest = new EditProfileRequest();
        editProfileRequest.setCustomerId(String.valueOf(getDataManager().getUserID()));
        editProfileRequest.setFirstname(firstname);
        editProfileRequest.setLastname(lastname);
        editProfileRequest.setEmailid(email);
        editProfileRequest.setStreetAddress(address);

        Disposable disposable =
            getDataManager()
                .userProfileUpdate(editProfileRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<EditProfileResponse>() {
                               @Override
                               public void accept(EditProfileResponse baseResponse) throws Exception {
                                   getNavigator().hideLoading();
                                   if (!baseResponse.isError() || baseResponse.getUser() == null) {
                                       getNavigator().showMessage("Something went wrong");
                                       return;
                                   }
                                   getNavigator().showMessage("profile update successfully");
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   getNavigator().hideLoading();
                                   getNavigator().handleError(throwable);
                                   Log.e("error",throwable.toString());
                               }
                           }
                );

        getCompositeDisposable().add(disposable);
    }
}
