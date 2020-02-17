package activity.home.editprofile;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityEditProfileBinding;

import java.util.Objects;

import javax.inject.Inject;

import activity.basic.BaseActivity;
import data.model.api.login.User;

public class EditProfileActivity extends BaseActivity<ActivityEditProfileBinding,EditProfileViewModel> implements EditProfileNevigator, View.OnClickListener {

    ActivityEditProfileBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    User muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        click();
    }

    @Override
    public EditProfileViewModel getViewModel() {
        return new ViewModelProvider(this,viewModelFactory).get(EditProfileViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    public int getBindingVariable() {
        return BR.editProfileViewModel;
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
        if (binding.edit.update.equals(view))
        {
            validateFieldandUpdate();
        }
    }

    private void click()
    {
        binding.edit.update.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getViewModel().getDataManager().getUserProfileLive(getViewModel().getDataManager().getUserID()).observe(this, getViewModel().profileObserver);
    }

    @Override
    public void onUserProfile(User user) {
        muser=user;
        binding.edit.setUser(user);
    }

    public void validateFieldandUpdate()
    {
        hideKeyboard();
        if (binding==null)
        {
            log("binding is null");
        }

        String firstname= Objects.requireNonNull(binding.edit.firstname.getText()).toString().trim();
        String lastname = Objects.requireNonNull(binding.edit.lastname.getText()).toString().trim();
        String email = Objects.requireNonNull(binding.edit.emailid.getText()).toString().trim();
        String address = Objects.requireNonNull(binding.edit.address.getText()).toString().trim();

        if (TextUtils.isEmpty(firstname))
        {
            showMessage(R.string.enter_your_fname,true);
            return;
        }
        else if (firstname.length()>40)
        {
            showMessage(R.string.enter_valid_fname,true);
            return;
        }
        else if (TextUtils.isEmpty(lastname))
        {
            showMessage(R.string.enter_your_lname,true);
            return;
        }
        else if (lastname.length()>40)
        {
            showMessage(R.string.enter_your_lname,true);
            return;
        }
        else if (TextUtils.isEmpty(email))
        {
            showMessage(R.string.enter_email,true);
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            showMessage(R.string.enter_valid_email,true);
            return;
        }
        else if (TextUtils.isEmpty(address))
        {
            showMessage(R.string.enter_address,true);
            return;
        }

        if (isNetworkConnected(true))
        {
            getViewModel().updateProfile(firstname,lastname,email,address);
        }
    }
}
