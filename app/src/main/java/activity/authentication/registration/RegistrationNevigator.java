package activity.authentication.registration;

import activity.basic.BaseNevigator;
import data.model.api.login.User;

public interface RegistrationNevigator extends BaseNevigator {

    void onRegistrationComplete(User userData);

}
