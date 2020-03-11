package activity.home.changePassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityChangePasswordBinding;

import java.util.Objects;

import javax.inject.Inject;

import activity.authentication.login.LoginActivity;
import activity.basic.BaseActivity;

import static utils.AppConstants.PASSWORD_LENGTH;

public class ChangePasswordActivity extends BaseActivity<ActivityChangePasswordBinding,ChangePasswordViewModel> implements ChangePasswordNevigator, View.OnClickListener {

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;
    ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_change_password);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);
        getViewModel().setNavigator(this);

        binding.change.submit.setOnClickListener(this);

        getSupportActionBar().setTitle(R.string.change_password);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public ChangePasswordViewModel getViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(ChangePasswordViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    public int getBindingVariable() {
        return BR.changePasswordViewModel;
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        if (binding.change.submit.equals(view))
        {
            validateAndChangePassword();
        }
    }

    private void validateAndChangePassword()
    {
        hideKeyboard();
        String oldPassword = Objects.requireNonNull(binding.change.oldPassword2.getText()).toString().trim();
        String newPassword = Objects.requireNonNull(binding.change.newPassword2.getText()).toString().trim();
        String confirmPassword = Objects.requireNonNull(binding.change.cpass2.getText()).toString().trim();
        if (TextUtils.isEmpty(oldPassword)){
            showMessage(R.string.enter_password, true);
            return;
        } else if (oldPassword.length() < PASSWORD_LENGTH){
            showMessage(R.string.enter_valid_password, true);
            return;
        } else if (TextUtils.isEmpty(newPassword)) {
            showMessage(R.string.enter_password, true);
            return;
        } else if (newPassword.length() < PASSWORD_LENGTH) {
            showMessage(R.string.enter_valid_password, true);
            return;
        } else if (TextUtils.isEmpty(confirmPassword)) {
            showMessage(R.string.enter_confirm_password, true);
            return;
        } else if (confirmPassword.length() < PASSWORD_LENGTH) {
            showMessage(R.string.enter_valid_password, true);
            return;
        } else if (!newPassword.equalsIgnoreCase(confirmPassword)) {
            showMessage(R.string.password_not_match, true);
            return;
        }
        if (isNetworkConnected(true))
            getViewModel().changePassword(Objects.requireNonNull(binding.change.oldPassword2.getText()).toString().trim(),Objects.requireNonNull(binding.change.newPassword2.getText()).toString().trim());
    }

    @Override
    public void onChangePasswordComplete() {
      //  Log.e("password change","password change successfully");
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}
