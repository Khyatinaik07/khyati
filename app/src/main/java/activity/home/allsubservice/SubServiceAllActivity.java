package activity.home.allsubservice;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivitySubServiceAllBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import activity.basic.BaseActivity;
import data.model.api.homepage.ServiceData;

public class SubServiceAllActivity extends BaseActivity<ActivitySubServiceAllBinding, SubServiceAllViewModel> implements SubServiceAllNavigator {

    ActivitySubServiceAllBinding binding;
    AllServiceAdapter adapter;
    Integer position;
    String mid;

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("All Services");

        Serializable i = getIntent().getSerializableExtra("tbcount");
        Serializable id = getIntent().getSerializableExtra("id");
        Log.w("position", String.valueOf(i));
        position = Integer.valueOf(i.toString());
        mid = String.valueOf(id.toString());

        //binding.sub.tabLayout.setScrollPosition(position, 0, true);

        adapter = new AllServiceAdapter(getSupportFragmentManager(), getLifecycle(),mid);
        binding.sub.viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.sub.viewPager.setAdapter(adapter);

        binding.sub.viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.sub.viewPager.setCurrentItem(position);
            }
        },100);
      //  binding.sub.viewPager.setCurrentItem(position);

        getViewModel().getAllServiceNameLive(0,"0").observe(this, new Observer<List<ServiceData>>() {
            @Override
            public void onChanged(List<ServiceData> serviceData) {
                getViewModel().processAllServiceName(serviceData,"0");
                getViewModel().setIsDataEmpty(serviceData.isEmpty());
            }
        });

    }

    @Override
    public SubServiceAllViewModel getViewModel() {
        return new ViewModelProvider(this, mViewModelFactory).get(SubServiceAllViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sub_service_all;
    }

    @Override
    public int getBindingVariable() {
        return BR.subAllServiceViewModel;
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

    @Override
    public void onServiceNameAvailable(List<ServiceData> serviceData,String id) {

        adapter.setSessionData(serviceData);

        Log.w("data available", "Size : " + serviceData.size());

        new TabLayoutMediator(binding.sub.tabLayout, binding.sub.viewPager,
                (tab, position1) -> {
                    String tabName = serviceData.get(position1).getName();
                    tab.setText(tabName);
                }).attach();

    }
}