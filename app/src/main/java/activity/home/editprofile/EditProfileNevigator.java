package activity.home.editprofile;

import activity.basic.BaseNevigator;
import data.model.api.login.User;

public interface EditProfileNevigator extends BaseNevigator {

    void onUserProfile(User user);

}
