package fragment.myprofile;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityMyProfileFragmentBinding;

import javax.inject.Inject;

import activity.authentication.login.LoginActivity;
import activity.basic.BaseFragment;
import activity.home.changePassword.ChangePasswordActivity;
import activity.home.editprofile.EditProfileActivity;
import activity.home.homepagetab.HomePageTabActivity;
import data.model.api.login.GenericProfileInterface;
import di.FragmentScope;
import utils.AppConstants;

public class MyProfileFragment extends BaseFragment<ActivityMyProfileFragmentBinding, MyProfileViewModel> implements MyProfileNevigator,View.OnClickListener {

    ActivityMyProfileFragmentBinding binding;
    Activity context;
    GenericProfileInterface muser;
    private int mUserID = 0;

    @Inject
    @FragmentScope
    protected ViewModelProvider.Factory mViewModelFactory;

    public MyProfileFragment(Context context) {

    }

    @Override
    protected String getScreenName() {
        return AppConstants.ScreenName.MY_PROFILE;
    }

    @Override
    public int getBindingVariable() {
        return BR.myProfileViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_profile_fragment;
    }

    @Override
    public MyProfileViewModel getmViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(MyProfileViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.mUserID = getmViewModel().getDataManager().getUserID();
      //  Log.e("id",getmViewModel().getDataManager().getUserID().toString());
        getmViewModel().setNavigator(this);
        binding = getViewDataBinding();
        click();

        getmViewModel().getProfileDataLive(mUserID);
        getmViewModel().liveDataMerger.observe(getViewLifecycleOwner(), new Observer<GenericProfileInterface>() {
            @Override
            public void onChanged(GenericProfileInterface profileInterface) {
                if (profileInterface != null) {
                    onUserProfile(profileInterface);
                }
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (HomePageTabActivity)getContext();
    }

    private void click()
    {
        binding.fab.setOnClickListener(this);
        binding.profile.relative6.setOnClickListener(this);
        binding.profile.logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (binding.fab.equals(view)){
            Intent i = new Intent(view.getContext(), EditProfileActivity.class);
            context.startActivity(i);
        }
        if (binding.profile.relative6.equals(view))
        {
            Intent i = new Intent(view.getContext(), ChangePasswordActivity.class);
            context.startActivity(i);
        }
        if (binding.profile.logout.equals(view))
        {
            getmViewModel().logout();
        }
    }

    @Override
    public void onLogoutSuccess()    {
        clearDataAndNavigateToLogin();
    }

    @Override
    public void onUserProfile(GenericProfileInterface user) {
        muser=user;
        binding.profile.setUser(user);
        binding.setUser(user);
    }

    public void clearDataAndNavigateToLogin() {
        // Clear all notification
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

        // Start splash
        final Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        // Finish current activity
        getActivity().finish();
    }
}
