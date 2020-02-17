package activity.basic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.onlineserviceportal.R;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import utils.NetworkUtils;
import utils.logger.AnalyticsLogger;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment
        implements BaseNevigator, Toolbar.OnMenuItemClickListener {
    private BaseActivity mactivity;
    private View rootView;
    private T mViewDataBinding;
    private V mViewModel;

    @Inject
    protected AnalyticsLogger mAnalyticsLogger;

    protected abstract String getScreenName();

    public abstract int getBindingVariable();

    public abstract @LayoutRes
    int getLayoutId();

    public abstract V getmViewModel();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mactivity = (BaseActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        mViewModel = getmViewModel();

        // Log screen name event
        mAnalyticsLogger.logScreenName(getScreenName(), requireActivity());
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        rootView = mViewDataBinding.getRoot();
        return rootView;
    }

    @Override
    public void onDetach() {
        mactivity = null;
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            getmViewModel().setIsSearchVisible(!getmViewModel().getIsSearchVisible().get());
        }
        return true;
    }

    public BaseActivity getBaseActivity() {
        return mactivity;
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public void hideKeyboard() {
        if (mactivity != null) {
            mactivity.hideKeyboard();
        }
    }

    public void showKeyboard() {
        if (mactivity != null) {
            mactivity.showKeyboard();
        }
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }

    protected void showMessage(int resId, Boolean showInSnackBar) {
        if (getBaseActivity() != null) getBaseActivity().showMessage(resId, showInSnackBar);
    }

    protected void log(String message) {
        if (getBaseActivity() != null) getBaseActivity().log(message);
    }

    protected void showMessage(String message, Boolean showInSnackBar) {
        if (getBaseActivity() != null) getBaseActivity().showMessage(message, showInSnackBar);
    }

    @Override
    public void handleError(Throwable throwable) {
        if (getBaseActivity() != null) {
            getBaseActivity().handleError(throwable);
        }
    }

    protected boolean isNetworkConnected(boolean showErrorMessage) {

        boolean isConnected = NetworkUtils.isNetworkConnected(getContext());

        if (!isConnected && showErrorMessage) {
            showMessage("No internet connection available!", true);
        }

        return isConnected;
    }

    @Override
    public void showLoading() {
        if (getBaseActivity() != null) getBaseActivity().showLoading();
    }

    @Override
    public void hideLoading() {
        if (getBaseActivity() != null) getBaseActivity().hideLoading();
    }

    @Override
    public void showMessage(String message) {
        if (getBaseActivity() != null) getBaseActivity().showMessage(message);
    }

    @Override
    public void showMessage(int messageId) {
        if (getBaseActivity() != null) getBaseActivity().showMessage(messageId);
    }
}
