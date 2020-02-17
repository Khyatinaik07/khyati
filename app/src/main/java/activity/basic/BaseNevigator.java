package activity.basic;

import androidx.annotation.StringRes;

public interface BaseNevigator {

    void handleError(Throwable throwable);

    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void showMessage(@StringRes int messageId);

}
