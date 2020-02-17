package activity.home;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.example.serviceprovider.R;
import com.example.serviceprovider.databinding.ActivityHomePageTabBinding;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import activity.basic.BaseApp;
import adapter.activity.TabAdapter;

public class HomePageTabActivity extends BaseApp {

    ActivityHomePageTabBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home_page_tab);
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().hide();
        binding.home.equalNavigationBar.setVerticalFadingEdgeEnabled(true);

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
       // Log.w("count child", String.valueOf(binding.home.equalNavigationBar.getChildCount()));
        binding.home.viewPager.setAdapter(adapter);

    }

}
