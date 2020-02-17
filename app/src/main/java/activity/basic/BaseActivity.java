package activity.basic;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.example.onlineserviceportal.BuildConfig;
import com.example.onlineserviceportal.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import data.model.api.errors.GenericError;
import okhttp3.Response;
import utils.CommonUtils;
import utils.NetworkUtils;

import static utils.AppConstants.StatusCodes.AUTHORIZATION_FAILED;
import static utils.AppConstants.StatusCodes.FORBIDDEN;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity implements HasSupportFragmentInjector,BaseNevigator {

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    private V mViewModel;
    private T mViewDataBinding;
    private BaseActivity mActivity;
    private ProgressDialog mProgressDialog;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        doDependencyInjection();
        super.onCreate(savedInstanceState);

        doDataBinding();
        doDataBinding();

    }

    public void doDependencyInjection() {
        AndroidInjection.inject(this);
    }


    public abstract V getViewModel();

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract int getBindingVariable();

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    private void doDataBinding() {
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }



    protected void showMessage(int resId, Boolean showInSnackBar) {
        showMessage(getString(resId), showInSnackBar);
    }

    protected void showMessage(String message, Boolean showInSnackBar) {
        if (showInSnackBar) {

            Snackbar snackbar =
                    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();

            TextView textView = sbView.findViewById(R.id.snackbar_text);
            textView.setMaxLines(3);
            textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            snackbar.show();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    protected boolean isNetworkConnected(boolean showErrorMessage) {

        boolean isConnected = NetworkUtils.isNetworkConnected(getApplicationContext());

        if (!isConnected && showErrorMessage) {
            showMessage("No internet connection available!", true);
        }

        return isConnected;
    }

    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public void showKeyboard() {
        if (mActivity != null) {
            mActivity.showKeyboard();
        }
    }

    @Override
    public void handleError(Throwable throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
        hideLoading();
        if (throwable instanceof ANError) {
            ANError error = (ANError) throwable;

            Response networkResponse = error.getResponse() != null ? error.getResponse().networkResponse() : null;
            if (networkResponse == null || error.getErrorBody() == null) {
                showMessage(R.string.some_error, false);
            } else if (error.getErrorCode() == 0 && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
                // No internet available
                showMessage(R.string.connection_error, false);
            } else if (error.getErrorCode() == AUTHORIZATION_FAILED || error.getErrorCode() == FORBIDDEN) {
                showMessage(R.string.session_expired, false);
            }

            try {
                // Display error message from error response
                GenericError errorResponse = new Gson().fromJson(error.getErrorBody(), GenericError.class);
                if (errorResponse == null) {
                    showMessage(R.string.some_error, false);
                    return;
                }
                showMessage(errorResponse.getErrorMessage());
            } catch (JsonSyntaxException e) {
                showMessage(R.string.some_error, false);
            }

        } else {
            showMessage(throwable.getMessage());
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showMessage(String message) {
        showMessage(message, true);
    }

    @Override
    public void showMessage(int messageId) {
        showMessage(messageId, true);
    }

    public void log(String message) {
        if (BuildConfig.DEBUG)
            Log.e("EventRaft", String.valueOf(message));
    }
}
