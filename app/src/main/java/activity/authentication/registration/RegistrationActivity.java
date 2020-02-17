package activity.authentication.registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityRegistrationBinding;

import java.util.Objects;

import javax.inject.Inject;

import activity.authentication.otpverify.VerifyOTPActivity;
import activity.basic.BaseActivity;
import data.model.api.login.User;
import utils.AppConstants;
import utils.logger.AnalyticsLogger;

import static utils.AppConstants.PASSWORD_LENGTH;

public class RegistrationActivity extends BaseActivity<ActivityRegistrationBinding, RegistrationViewModel> implements RegistrationNevigator,View.OnClickListener {

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    ActivityRegistrationBinding binding;
    NavController navController;

    @Inject
    AnalyticsLogger mAnalyticsLogger;

    @Override
    public int getBindingVariable() {
        return com.example.onlineserviceportal.BR.registrationViewModel;

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_registration;
    }

    @Override
    public RegistrationViewModel getViewModel() {
        return new ViewModelProvider(this, mViewModelFactory).get(RegistrationViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_registration);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);
        getViewModel().setNavigator(this);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setClickListener();
    }

    private void setClickListener() {
        binding.registration.sub.setOnClickListener(this);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void validateFieldsAndSignIn() {
        hideKeyboard();
        if (binding == null) {
            log("mViewBinding is null");
            return;
        }

        String firstName = Objects.requireNonNull(binding.registration.fname2.getText()).toString().trim();
        String lastName = Objects.requireNonNull(binding.registration.lname2.getText()).toString().trim();
        String emailId = Objects.requireNonNull(binding.registration.email2.getText()).toString().trim();
        String mno = Objects.requireNonNull(binding.registration.mobile2.getText()).toString().trim();
        String code = Objects.requireNonNull(binding.registration.code2.getText().toString().trim());
        String password = Objects.requireNonNull(binding.registration.pass2.getText()).toString().trim();
        String confirmPassword = Objects.requireNonNull(binding.registration.cpass2.getText()).toString().trim();
        if (TextUtils.isEmpty(firstName)) {
            showMessage(R.string.enter_your_fname, true);
            return;
        } else if (firstName.length() > 40) {
            showMessage(R.string.enter_valid_fname, true);
            return;
        } else if (TextUtils.isEmpty(lastName)) {
            showMessage(R.string.enter_your_lname, true);
            return;
        } else if (lastName.length() > 40) {
            showMessage(R.string.enter_valid_lname, true);
            return;
        } else if (TextUtils.isEmpty(emailId)) {
            showMessage(R.string.enter_email, true);
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            showMessage(R.string.enter_valid_email, true);
            return;
        } else if (TextUtils.isEmpty(mno)) {
            showMessage(R.string.enter_phone,true);
            return;
        } else if (mno.length() != 10) {
            showMessage(R.string.enter_valid_phone,true);
            return;
        } else if (TextUtils.isEmpty(password)) {
            showMessage(R.string.enter_password, true);
            return;
        } else if (password.length() < PASSWORD_LENGTH) {
            showMessage(R.string.enter_valid_password, true);
            return;
        } else if (TextUtils.isEmpty(confirmPassword)) {
            showMessage(R.string.enter_confirm_password, true);
            return;
        } else if (confirmPassword.length() < PASSWORD_LENGTH) {
            showMessage(R.string.enter_valid_password, true);
            return;
        } else if (!password.equalsIgnoreCase(confirmPassword)) {
            showMessage(R.string.password_not_match, true);
            return;
        }

        if (isNetworkConnected(true)) {
            getViewModel().SignUp(firstName, lastName, emailId, mno,password,code);
        }
        else
        {
            Log.w("error","error");
        }
    }

    @Override
    public void onClick(View view) {
        if (binding.registration.sub.equals(view)) {
            validateFieldsAndSignIn();
        }
    }

    @Override
    public void onRegistrationComplete(User userData) {
        // Log login event data
        mAnalyticsLogger.logSignupEvent(Integer.valueOf(userData.getCustomerId()));
        Log.e("registration","registration complete");
        Intent i = new Intent(getApplicationContext(),VerifyOTPActivity.class);
        i.putExtra(AppConstants.Extras.PHONE_OR_EMAIL,binding.registration.mobile2.getText().toString().trim());
        i.putExtra(AppConstants.Extras.COUNTRY_CODE,binding.registration.code2.getText().toString().trim());
        startActivity(i);
    }

}