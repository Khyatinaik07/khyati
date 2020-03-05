package activity.home.servicepackagelayout3.viewpackagedetail;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityServicePackageDetailBinding;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import javax.inject.Inject;

import activity.basic.BaseActivity;
import utils.CommonUtils;

public class ServicePackageDetailActivity extends BaseActivity<ActivityServicePackageDetailBinding,ServicePackageDetailViewModel> implements ServicePackageDetailNavigator {

    ActivityServicePackageDetailBinding binding;
    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    Serializable amount,title,subtitle,icon,time,name,discount;
    int amt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);

        amount = getIntent().getSerializableExtra("amount");
        title = getIntent().getSerializableExtra("title");
        subtitle = getIntent().getSerializableExtra("subtitle");
        icon = getIntent().getSerializableExtra("icon");
        time = getIntent().getSerializableExtra("time");
        name = getIntent().getSerializableExtra("name");
        discount = getIntent().getSerializableExtra("discount");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name.toString());

        setData();
    }

    @Override
    public ServicePackageDetailViewModel getViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(ServicePackageDetailViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_package_detail;
    }

    @Override
    public int getBindingVariable() {
        return BR.servicePackageDetailViewModel;
    }

    private void setData()
    {
        binding.detail.title.setText(title.toString());
        String a = "\u20B9"+ amount;
        binding.detail.disamt.setText(a);
        binding.detail.disamt.setPaintFlags(binding.detail.disamt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.detail.time.setText(time.toString());
        Picasso.with(binding.detail.img.getContext()).load(icon.toString()).into(binding.detail.img);
        amt = amt + Integer.valueOf(amount.toString());
        double disamt = amt;
        disamt = disamt*(Double.valueOf(discount.toString())/100);
        double finalamt = 0;
        finalamt = amt - disamt;
        String d = "\u20B9" + CommonUtils.numberFormatter(finalamt);;
        binding.detail.amount.setText(d);
        binding.detail.subtitle.setText(Html.fromHtml(subtitle.toString()));
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
