package activity.authentication.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityLoginBinding;

import javax.inject.Inject;

import activity.authentication.forgotpassword.ForgotPasswordActivity;
import activity.authentication.registration.RegistrationActivity;
import activity.basic.BaseActivity;
import activity.home.homepagetab.HomePageTabActivity;
import data.model.api.login.User;
import utils.logger.AnalyticsLogger;

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> implements LoginNevigation,View.OnClickListener {

    ActivityLoginBinding binding;
    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    @Inject
    AnalyticsLogger analyticsLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);
        getViewModel().setNavigator(this);

        getSupportActionBar().hide();
        click();

    }

    @Override
    public LoginViewModel getViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(LoginViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public int getBindingVariable() {
        return BR.loginViewModel;
    }


    private void click()
    {
        binding.login.sub.setOnClickListener(this);
        binding.login.fp.setOnClickListener(this);
        binding.login.reg.setOnClickListener(this);
    }

    private void validateFieldandLogin()
    {
        hideKeyboard();
        if (binding == null) {
            log("mViewBinding is null");
            return;
        }

        String mno =binding.login.mno2.getText().toString().trim();
        if (!mno.isEmpty() && mno.length() != 10)
        {
            showMessage(R.string.enter_valid_phone,true);
            return;
        }

        String password = binding.login.pass2.getText().toString().trim();
        if (!password.isEmpty() && password.length() < 4)
        {
            showMessage(R.string.enter_valid_password,true);
            return;
        }

        if (isNetworkConnected(true))
        {
            getViewModel().doLogin(mno,password);
        }
    }


    @Override
    public void onClick(View view) {
        if (binding.login.reg.equals(view))
        {
            Intent i =new Intent(getApplicationContext(), RegistrationActivity.class);
            startActivity(i);
        }
        if (binding.login.fp.equals(view))
        {
            Intent i = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
            startActivity(i);
        }
        if (binding.login.sub.equals(view))
        {
            validateFieldandLogin();
        }
    }

    @Override
    public void onLoginComplete(User user) {
        analyticsLogger.LoginEvent(Integer.valueOf(user.getCustomerId()));
        Log.e("login","use logged in");
        navigateDashboardScreen();
    }

    @Override
    public void navigateDashboardScreen() {
        Intent i = new Intent(getApplicationContext(),HomePageTabActivity.class);
        finish();
        startActivity(i);
    }
}
