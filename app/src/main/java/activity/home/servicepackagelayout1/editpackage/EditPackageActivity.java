package activity.home.servicepackagelayout1.editpackage;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityEditPackageBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import activity.basic.BaseActivity;
import activity.home.servicepackagelayout2.ServicePackageImageAdapter;
import data.model.api.servicepackage2.ServiceResult;

public class EditPackageActivity extends BaseActivity<ActivityEditPackageBinding,EditPackageViewModel> implements EditPackageNavigator {

    ActivityEditPackageBinding binding;
    Serializable serviceid,icon,position;
    EditPackageAdapter adapter;
    ServicePackageImageAdapter imageAdapter;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        serviceid = getIntent().getSerializableExtra("serviceid");
        icon = getIntent().getSerializableExtra("icon");
        position = getIntent().getSerializableExtra("position");

        setAdapter();

        getViewModel().getDataManager().getServicePackageLive(serviceid.toString()).observe(this, new Observer<List<ServiceResult>>() {
            @Override
            public void onChanged(List<ServiceResult> serviceResults) {
                getViewModel().setIsEmpty(serviceResults.isEmpty());
                adapter.setServiceResults(serviceResults);
                imageAdapter = new ServicePackageImageAdapter(serviceResults.get(Integer.valueOf(position.toString())).getImages(),icon.toString());
                binding.edit.imgrv.setAdapter(imageAdapter);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public EditPackageViewModel getViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(EditPackageViewModel.class);
    }

    private void setAdapter()
    {
        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        binding.edit.rv.setLayoutManager(l);
        adapter = new EditPackageAdapter(new ArrayList<>(),icon.toString());
        binding.edit.rv.setAdapter(adapter);

        LinearLayoutManager l2 = new LinearLayoutManager(getApplicationContext());
        l2.setOrientation(RecyclerView.HORIZONTAL);
        binding.edit.imgrv.setLayoutManager(l2);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_package;
    }

    @Override
    public int getBindingVariable() {
        return BR.editPackageViewModel;
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
}
