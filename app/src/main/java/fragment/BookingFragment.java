package fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.ActivityBookingFragmentBinding;

import activity.home.homepagetab.HomePageTabActivity;

public class BookingFragment extends Fragment {

    ActivityBookingFragmentBinding binding;
    Activity activity;

    public BookingFragment(Context context) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.activity_booking_fragment,container,false);
        binding.book.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, BookingTypeActivity.class);
                activity.startActivity(i);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (HomePageTabActivity)getContext();
    }

}
