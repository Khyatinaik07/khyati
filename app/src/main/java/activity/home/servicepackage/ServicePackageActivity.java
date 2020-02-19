package activity.home.servicepackage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityServicePackageBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import activity.basic.BaseActivity;
import activity.home.MembershipActivity;
import data.model.api.servicepackage.ServiceResult;

public class ServicePackageActivity extends BaseActivity<ActivityServicePackageBinding,ServicePackageViewModel> implements ServicePackageNavigator {

    ActivityServicePackageBinding binding;
    ServicePackageAdapter adapter;
    Serializable i,id,icon;
    String sid;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);
        binding =getViewDataBinding();
        setSupportActionBar(binding.toolbar);

        onClick();

        i = getIntent().getSerializableExtra("name");
        id = getIntent().getSerializableExtra("id");
        icon = getIntent().getSerializableExtra("icon");
        sid = String.valueOf(id);
        getSupportActionBar().setTitle(i.toString());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setAdapter();

        getViewModel().setServicePackage(sid);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getViewModel().getDataManager().getServicePackageLive(sid).observe(this,getViewModel().listObserver);
    }

    @Override
    public ServicePackageViewModel getViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(ServicePackageViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_package;
    }

    @Override
    public int getBindingVariable() {
        return BR.servicePackageViewModel;
    }

    private void onClick()
    {
        binding.service.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), MembershipActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"select one package",Toast.LENGTH_LONG).show();
            }
        });
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
        LinearLayoutManager l = new LinearLayoutManager(this);
        l.setOrientation(RecyclerView.VERTICAL);
        binding.service.rv.setLayoutManager(l);
        adapter = new ServicePackageAdapter(new ArrayList<>(),icon.toString());
        binding.service.rv.setAdapter(adapter);
    }

    @Override
    public void onServicePackageSuccessfull(List<ServiceResult> serviceResults) {
        getViewModel().setIsEmpty(serviceResults.isEmpty());
        Log.e("empty","service package data empty"+serviceResults.isEmpty());
        adapter.setList2(serviceResults);
    }
}
