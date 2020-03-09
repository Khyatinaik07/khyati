package activity.home.homepagetab;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.onlineserviceportal.BR;
import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityHomePageTabBinding;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import javax.inject.Inject;

import activity.basic.BaseActivity;

public class HomePageTabActivity extends BaseActivity<ActivityHomePageTabBinding,HomePageTabViewModel> implements HomePageTabNevigator {

    ActivityHomePageTabBinding binding;
    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_home_page_tab);
        binding = getViewDataBinding();
        getViewModel().setNavigator(this);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(binding.toolbar);
        binding.home.equalNavigationBar.setVerticalFadingEdgeEnabled(true);
        getSupportActionBar().hide();

        binding.home.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.home.equalNavigationBar.setCurrentActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.home.equalNavigationBar.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                binding.home.viewPager.setCurrentItem(position, true);
            }
        });

        final TabAdapter adapter =new TabAdapter(this,getSupportFragmentManager(),binding.home.equalNavigationBar.getChildCount());
        //Log.w("count child", String.valueOf(binding.home.equalNavigationBar.getChildCount()));
        binding.home.viewPager.setAdapter(adapter);

    }

    @Override
    public HomePageTabViewModel getViewModel() {
        return new ViewModelProvider(this,mViewModelFactory).get(HomePageTabViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_page_tab;
    }

    @Override
    public int getBindingVariable() {
        return BR.homePageTabViewModel;
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