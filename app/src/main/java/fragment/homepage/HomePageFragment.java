package fragment.homepage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityHomePageFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import activity.basic.BaseFragment;
import data.model.api.homepage.BannerData;
import data.model.api.homepage.ServiceData;
import di.FragmentScope;
import utils.AppConstants;
import utils.widget.OnSearchQueryInputListener;

public class HomePageFragment extends BaseFragment<ActivityHomePageFragmentBinding,HomePageViewModel> implements HomePageNavigator {

    @Inject
    @FragmentScope
    protected ViewModelProvider.Factory mViewModelFactory;

    private ActivityHomePageFragmentBinding binding;

    private BannerAdapter adapter = new BannerAdapter(getContext(),new ArrayList<>());

    private ServiceAdapter serviceAdapter = new ServiceAdapter(new ArrayList<>());

    private BriefServiceAdapter briefServiceAdapter = new BriefServiceAdapter(new ArrayList<>());

    public HomePageFragment(Context context)
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        getmViewModel().setNavigator(this);
        binding = getViewDataBinding();

        setAdapter();

        getmViewModel().setOrderBy(0);
        getmViewModel().getDataManager().getAllSearviceNameFromDB(getmViewModel().orderBy.get(),"0","").observe(this, getmViewModel().serviceObserver);
        getmViewModel().setOrderBy(1);
        getmViewModel().getDataManager().getAllSearviceNameFromDB(getmViewModel().orderBy.get(),"0","").observe(this,getmViewModel().briefServiceObserver);

        binding.homeFragment.searchView.setOnQueryInputListener(new OnSearchQueryInputListener() {
            @Override
            public void onTextEntered(CharSequence query) {
                getmViewModel().getDataManager().getAllSearviceNameFromDB(0,"0","%" + query + "%").observe(getActivity(), getmViewModel().serviceObserver);
                getmViewModel().getDataManager().getAllSearviceNameFromDB(1,"0","%" + query + "%").observe(getActivity(),getmViewModel().briefServiceObserver);
            }

            @Override
            public void onSearchCancelClick() {
                onStart();
            }
        });

        return binding.getRoot();
    }

    @Override
    protected String getScreenName() {
        return AppConstants.ScreenName.HOME_PAGE;
    }

    @Override
    public int getBindingVariable() {
        return BR.homePageViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_page_fragment;
    }

    @Override
    public HomePageViewModel getmViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(HomePageViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getmViewModel().getDataManager().getBannerDataLive().observe(this, getmViewModel().bannerObserver);

        getmViewModel().setOrderBy(0);
        getmViewModel().getDataManager().getServiceLive(getmViewModel().orderBy.get(),"0").observe(this,getmViewModel().serviceObserver);

        getmViewModel().setOrderBy(1);
        getmViewModel().getDataManager().getServiceLive(getmViewModel().orderBy.get(),"0").observe(this,getmViewModel().briefServiceObserver);
    }

    private void setAdapter()
    {
        ViewCompat.setNestedScrollingEnabled(binding.homeFragment.rv,false);
        ViewCompat.setNestedScrollingEnabled(binding.homeFragment.rv2,false);
        ViewCompat.setNestedScrollingEnabled(binding.homeFragment.rv3,false);
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        l.setOrientation(RecyclerView.HORIZONTAL);
        binding.homeFragment.rv.setLayoutManager(l);
        binding.homeFragment.rv.setAdapter(adapter);
        if (isNetworkConnected(true))
        {
            getmViewModel().getBannerData();
        }

        //our service
        binding.homeFragment.rv2.setLayoutManager(new GridLayoutManager(getContext(),3));
        binding.homeFragment.rv2.setAdapter(serviceAdapter);

        //service
        LinearLayoutManager l3 = new LinearLayoutManager(getContext());
        binding.homeFragment.rv3.setLayoutManager(l3);
        binding.homeFragment.rv3.setAdapter(briefServiceAdapter);
        if (isNetworkConnected(true))
        {
            getmViewModel().getServiceData();
        }
    }

    @Override
    public void onBannerFetchSuccessfully(List<BannerData> bannerData) {
        getmViewModel().setIsDataEmpty(bannerData.isEmpty());
        adapter.setData(bannerData);
    }

    @Override
    public void onServiceFetchSuccessfully(List<ServiceData> serviceData) {
        getmViewModel().setIsDataEmpty(serviceData.isEmpty());
        serviceAdapter.setData(serviceData);
    }

    @Override
    public void onBriefServiceFetchSuccessfully(List<ServiceData> serviceData) {
        getmViewModel().setIsDataEmpty(serviceData.isEmpty());
        briefServiceAdapter.setData(serviceData);
    }
}
