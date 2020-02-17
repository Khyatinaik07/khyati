package activity.authentication.login;


import activity.basic.BaseNevigator;
import data.model.api.login.User;

public interface LoginNevigation extends BaseNevigator {

    void onLoginComplete(User user);

    void navigateDashboardScreen();

}
