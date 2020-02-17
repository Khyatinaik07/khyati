package activity.home.breifservicesubtype;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivitySubTypeServiceBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import activity.basic.BaseActivity;
import data.model.api.homepage.ServiceData;

public class SubTypeServiceActivity extends BaseActivity<ActivitySubTypeServiceBinding, SubTypeServiceViewModel> implements SubTypeServiceNavigator {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    ActivitySubTypeServiceBinding binding;
    String id,name;
    SubTypeServiceAdapter adapter = new SubTypeServiceAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);

        Serializable i = getIntent().getSerializableExtra("id");
        Serializable i1 = getIntent().getSerializableExtra("name");
        id= String.valueOf(i);
        name = String.valueOf(i1);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle(i1.toString());

        setAdapter();

        getViewModel().getService(id);

        getViewModel().getDataManager().getServiceLive(0,id).observe(this, new Observer<List<ServiceData>>() {
            @Override
            public void onChanged(List<ServiceData> serviceData) {
                getViewModel().setIsServiceEmpty(serviceData.isEmpty());
                Log.w("emptyyyyy","data empty"+serviceData.isEmpty());
                adapter.setData(serviceData);
            }
        });
    }

    @Override
    public SubTypeServiceViewModel getViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(SubTypeServiceViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sub_type_service;
    }

    @Override
    public int getBindingVariable() {
        return BR.subTypeServiceViewModel;
    }

    private void setAdapter() {
        LinearLayoutManager l = new LinearLayoutManager(this);
        binding.sub.rv.setLayoutManager(l);
        binding.sub.rv.setAdapter(adapter);
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
}