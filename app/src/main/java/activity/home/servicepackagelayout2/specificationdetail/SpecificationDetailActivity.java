package activity.home.servicepackagelayout2.specificationdetail;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivitySpecificationDetailBinding;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import javax.inject.Inject;

import activity.basic.BaseActivity;

public class SpecificationDetailActivity extends BaseActivity<ActivitySpecificationDetailBinding,SpecificationDetailViewModel> implements SpecificationDetailNavigator {

    ActivitySpecificationDetailBinding binding;
    Serializable perosn,subtitle,time,amount,icon;

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

        perosn = getIntent().getSerializableExtra("person");
        time = getIntent().getSerializableExtra("time");
        amount = getIntent().getSerializableExtra("amount");
        subtitle = getIntent().getSerializableExtra("subtitle");
        icon = getIntent().getSerializableExtra("icon");

        setData();
    }

    @Override
    public SpecificationDetailViewModel getViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(SpecificationDetailViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_specification_detail;
    }

    @Override
    public int getBindingVariable() {
        return BR.specificationDetailViewModel;
    }

    public void setData()
    {
        binding.spdetail.person.setText(perosn.toString());
        binding.spdetail.time.setText(time.toString());
        binding.spdetail.amount.setText(amount.toString());
        binding.spdetail.subtitle.setText(Html.fromHtml(subtitle.toString()));

        Picasso.with(this).load(icon.toString()).into(binding.spdetail.titleimage);
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
