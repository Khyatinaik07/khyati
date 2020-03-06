package fragment.subtypeallservice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.SubtypeallservicefragmentactivityBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import activity.basic.BaseFragment;
import data.model.api.homepage.ServiceData;
import di.FragmentScope;
import utils.AppConstants;

public class SubTypeAllServiceFragment extends BaseFragment<SubtypeallservicefragmentactivityBinding, SubTypeAllServiceViewModel> implements SubTypeAllServiceNavigator {

    @Inject
    @FragmentScope
    ViewModelProvider.Factory mviewModelFactory;

    private SubtypeallservicefragmentactivityBinding binding;
    private AllTypeServiceFragmentAdapter adapter;
    private String name,image,id;

    public static Fragment getInstance(String name, String image,String id) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.NAME, name);
        bundle.putString(AppConstants.IMAGE, image);
        bundle.putString(AppConstants.ID,id);
        SubTypeAllServiceFragment subTypeAllServiceFragment = new SubTypeAllServiceFragment();
        subTypeAllServiceFragment.setArguments(bundle);
        return subTypeAllServiceFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        getmViewModel().setNavigator(this);
        binding = getViewDataBinding();
        setAdapter();

        if (getArguments() != null)
        {
            name = getArguments().getString(AppConstants.NAME,"");
            image = getArguments().getString(AppConstants.IMAGE,"");
            id = getArguments().getString(AppConstants.ID,"");
        }

        if (isNetworkConnected(true))
        {
            getmViewModel().getService(id);
        }

        getmViewModel().getDataManager().getServiceLive(0,id).observe(getViewLifecycleOwner(), new Observer<List<ServiceData>>() {
            @Override
            public void onChanged(List<ServiceData> serviceData) {
                getmViewModel().setIsServiceDataEmpty(serviceData.isEmpty());
                Log.w("emptyyyyy","data empty"+serviceData.isEmpty());
                adapter.setData(serviceData);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected String getScreenName() {
        return AppConstants.ScreenName.SUB_TYPE_SERVICE;
    }

    @Override
    public int getBindingVariable() {
        return BR.subTypeAllServiceViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.subtypeallservicefragmentactivity;
    }

    @Override
    public SubTypeAllServiceViewModel getmViewModel() {
        return new ViewModelProvider(this, mviewModelFactory).get(SubTypeAllServiceViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //context = (SubServiceAllActivity) getActivity();
        adapter = new AllTypeServiceFragmentAdapter(new ArrayList<>());;
        adapter.setHasStableIds(true);
    }

    private void setAdapter() {
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        binding.rv.setLayoutManager(l);
        binding.rv.setAdapter(adapter);
    }

}
