package activity.home.preferedservice;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityPrefferedServiceBinding;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import activity.basic.BaseActivity;
import data.model.api.homepage.ServiceData;

public class PrefferedServiceActivity extends BaseActivity<ActivityPrefferedServiceBinding,PreferedServiceViewModel> implements PreferedServiceNavigator {

    ActivityPrefferedServiceBinding binding;
    PrefferedServiceAdapter adapter = new PrefferedServiceAdapter(new ArrayList<>());
    Serializable name,image,id,title;

    @Inject
    ViewModelProvider.Factory mViewModelProviderFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);
        binding = getViewDataBinding();
        setSupportActionBar(binding.toolbar);

        setAdapter();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = getIntent().getSerializableExtra("name");
        image = getIntent().getSerializableExtra("image");
        id = getIntent().getSerializableExtra("id");
        title = getIntent().getSerializableExtra("title");

        Picasso.with(getApplicationContext()).load(image.toString()).into(binding.img);
        getSupportActionBar().setTitle(name.toString());

        binding.prefer.title.setText(title.toString());

        if (isNetworkConnected(true))
        {
            getViewModel().getService(id.toString());
        }
    }

    @Override
    public PreferedServiceViewModel getViewModel() {
        return new ViewModelProvider(this,mViewModelProviderFactory).get(PreferedServiceViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getViewModel().getDataManager().getServiceLive(0,id.toString()).observe(this,getViewModel().serviceObserver);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_preffered_service;
    }

    @Override
    public int getBindingVariable() {
        return BR.preferedServiceViewModel;
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
        binding.prefer.rv.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        binding.prefer.rv.setAdapter(adapter);
    }

    @Override
    public void onSeviceFetch(List<ServiceData> list) {
        if (list.isEmpty())
        {
            binding.prefer.rv.setVisibility(View.GONE);
            binding.prefer.text.setVisibility(View.VISIBLE);
        }
        else {
            binding.prefer.rv.setVisibility(View.VISIBLE);
            binding.prefer.text.setVisibility(View.GONE);
        }
        getViewModel().setIsEmpty(list.isEmpty());
        adapter.setData(list);
    }
}