package activity.authentication.forgotpassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityForgotPasswordBinding;

import java.util.Objects;

import javax.inject.Inject;

import activity.authentication.otpverify.VerifyOTPActivity;
import activity.basic.BaseActivity;
import utils.AppConstants;
import utils.logger.AnalyticsLogger;

import static com.mostafaaryan.transitionalimageview.utils.Utils.log;

public class ForgotPasswordActivity extends BaseActivity<ActivityForgotPasswordBinding,ForgotPasswordViewModel> implements ForgotPasswordNevigtor, View.OnClickListener {

    ActivityForgotPasswordBinding binding;

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Inject
    AnalyticsLogger analyticsLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.fp.submit.setOnClickListener(this);

    }

    @Override
    public ForgotPasswordViewModel getViewModel() {
        return new ViewModelProvider(this,viewModelFactory).get(ForgotPasswordViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forgot_password;
    }

    @Override
    public int getBindingVariable() {
        return com.example.onlineserviceportal.BR.forgotPasswordViewModel;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        if (binding.fp.submit.equals(view))
        {
            validatandForgotPassword();
        }
    }

    private void validatandForgotPassword()
    {
        hideKeyboard();
        if (binding == null) {
            log("mViewBinding is null");
            return;
        }
        String phone = Objects.requireNonNull(binding.fp.email2.getText()).toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showMessage(R.string.enter_valid_phone, true);
            return;
        } else if (phone.length() != 10) {
            showMessage(R.string.enter_valid_phone, true);
            return;
        }
        navigateToOtpScreen();
    }

    @Override
    public void navigateToOtpScreen() {
        Intent i = new Intent(getApplicationContext(), VerifyOTPActivity.class);
        i.putExtra(AppConstants.Extras.OTPScreen_type,AppConstants.OTPScreenType.FORGOTPASSWORD);
        i.putExtra(AppConstants.Extras.COUNTRY_CODE,binding.fp.code2.getText().toString().trim());
        i.putExtra(AppConstants.Extras.PHONE_OR_EMAIL,binding.fp.email2.getText().toString().trim());
        startActivity(i);
    }
}
