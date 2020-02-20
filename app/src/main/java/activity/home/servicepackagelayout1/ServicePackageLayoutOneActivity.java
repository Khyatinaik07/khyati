package activity.home.servicepackagelayout1;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityServicePackageLayout1Binding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import activity.basic.BaseActivity;
import data.model.api.servicepackage2.ServiceResult;

public class ServicePackageLayoutOneActivity extends BaseActivity<ActivityServicePackageLayout1Binding, ServiceLayoutOneViewModel> implements ServiceLayoutOneNavigator {

    ActivityServicePackageLayout1Binding binding;
    Serializable id,name;
    ServiceLayoutOneAdapter adapter;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);

        setAdapter();

        id = getIntent().getSerializableExtra("id");
        name = getIntent().getSerializableExtra("name");

        getSupportActionBar().setTitle(name.toString());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getViewModel().getService(id.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        getViewModel().getDataManager().getServicePackageLive(id.toString()).observe(this,getViewModel().listObserver);
    }

    @Override
    public ServiceLayoutOneViewModel getViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(ServiceLayoutOneViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_package_layout1;
    }

    @Override
    public int getBindingVariable() {
        return BR.serviceLayout1ViewModel;
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

    private void setAdapter()
    {
        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        binding.service.rv.setLayoutManager(l);
        adapter = new ServiceLayoutOneAdapter(new ArrayList<>());
        binding.service.rv.setAdapter(adapter);
    }

    @Override
    public void onServicePackageSuccessfull(List<ServiceResult> serviceResults) {
        getViewModel().setIsEmpty(serviceResults.isEmpty());
        adapter.setData(serviceResults);
    }
}