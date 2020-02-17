package fragment.myprofile;

import activity.basic.BaseNevigator;
import data.model.api.login.GenericProfileInterface;

public interface MyProfileNevigator extends BaseNevigator {

    void onLogoutSuccess();

    void onUserProfile(GenericProfileInterface user);

}
