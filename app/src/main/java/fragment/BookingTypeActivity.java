package fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityBookingTypeBinding;
import com.google.android.material.tabs.TabLayout;

import activity.home.homepagetab.HomePageTabActivity;
import adapter.activity.BookingTypeAdapter;

public class BookingTypeActivity extends Fragment {

    ActivityBookingTypeBinding binding;
    Context context;

    public BookingTypeActivity(Context context) {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_booking_type,container,false);
        binding.booking.tablayout.addTab(binding.booking.tablayout.newTab().setText("Ongoing"));
        binding.booking.tablayout.addTab(binding.booking.tablayout.newTab().setText("History"));

        final BookingTypeAdapter adapter = new BookingTypeAdapter(BookingTypeActivity.this,getChildFragmentManager(),binding.booking.tablayout.getTabCount());
        binding.booking.viewPager.setAdapter(adapter);
        binding.booking.viewPager.setCurrentItem(0);
        binding.booking.tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        binding.booking.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.booking.tablayout));

        binding.booking.tablayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.booking.viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=(HomePageTabActivity)getActivity();

    }

}
