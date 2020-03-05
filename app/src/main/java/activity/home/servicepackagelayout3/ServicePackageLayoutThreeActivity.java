package activity.home.servicepackagelayout3;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityServicePackageLayoutThreeBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import activity.basic.BaseActivity;
import data.model.api.servicepackage2.ServiceResult;
import utils.CommonUtils;

public class ServicePackageLayoutThreeActivity extends BaseActivity<ActivityServicePackageLayoutThreeBinding,ServiceLayoutThreeViewModel> implements ServiceLayoutThreeNavigator {

    ActivityServicePackageLayoutThreeBinding binding;
    Serializable id,name,icon;
    ServiceLayoutThreeAdapter adapter;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);

        id = getIntent().getSerializableExtra("id");
        name = getIntent().getSerializableExtra("name");
        icon = getIntent().getSerializableExtra("icon");

        getSupportActionBar().setTitle(name.toString());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setAdapter();

        getViewModel().getService(String.valueOf(id));

    }

    @Override
    protected void onStart() {
        super.onStart();
        getViewModel().getDataManager().getSpecification(id.toString()).observe(this,getViewModel().listObserver);
    }

    private void setAdapter()
    {
        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        binding.three.rv.setLayoutManager(l);
        adapter = new ServiceLayoutThreeAdapter(new ArrayList<>(),icon.toString(),name.toString());
        binding.three.rv.setAdapter(adapter);
    }

    @Override
    public ServiceLayoutThreeViewModel getViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(ServiceLayoutThreeViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_package_layout_three;
    }

    @Override
    public int getBindingVariable() {
        return BR.serviceLayoutThreeViewModel;
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

    public void isSpecificationAvailable(Boolean b)
    {
        if (b)
        {
            binding.three.rv.setVisibility(View.GONE);
            binding.three.text.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.three.rv.setVisibility(View.VISIBLE);
            binding.three.text.setVisibility(View.GONE);
        }
    }

    public void setBottomLayoutVisibility(Boolean b) {
        if (b) {
            binding.three.bottom.con.setVisibility(View.VISIBLE);
        } else {
            binding.three.bottom.con.setVisibility(View.GONE);
        }
    }

    public void setAmountText(double amount) {
        String text = '\u20B9' + CommonUtils.numberFormatter(amount);
        binding.three.bottom.amt.setText(text);
    }

    public void setDiscountText(int discount) {
        String discountamt = '\u20B9' + String.valueOf(discount);
        binding.three.bottom.discamt.setText(discountamt);
        binding.three.bottom.discamt.setPaintFlags(binding.three.bottom.discamt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
    public void setDiscount(double discountTxt)
    {
        if (discountTxt == 0)
        {
            binding.three.bottom.discamt.setVisibility(View.GONE);
        }
        else
        {
            binding.three.bottom.discamt.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onServicePackageSuccessfull(List<ServiceResult> serviceResults) {
        //getViewModel().setIsEmpty(serviceResults.isEmpty());
        adapter.setData(serviceResults);
        Log.e("service", "onServicePackageSuccessful: " + serviceResults.size() + "==" + getViewModel().getIsServiceEmpty().get());
    }
}