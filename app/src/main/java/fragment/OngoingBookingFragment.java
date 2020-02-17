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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.OngoingbookingFragmentBinding;

import java.util.ArrayList;

import activity.home.homepagetab.HomePageTabActivity;
import adapter.fragment.OnGoingBookingAdapter;
import classes.OnGoingBookingClass;

public class OngoingBookingFragment extends Fragment {

    OngoingbookingFragmentBinding binding;
    Context context;
    ArrayList<OnGoingBookingClass> list1 = new ArrayList<>();
    OnGoingBookingAdapter adapter;
    OnGoingBookingClass onGoingBookingClass;

    public OngoingBookingFragment(Context context)
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.ongoingbooking_fragment,container,false);
        setAdapter();
        setData();
        click();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (HomePageTabActivity)getActivity();
    }

    private void setData()
    {
        list1.clear();
        onGoingBookingClass = new OnGoingBookingClass();
        onGoingBookingClass.setService("Home Paint");
        onGoingBookingClass.setDate("23");
        onGoingBookingClass.setMonth("Oct");
        onGoingBookingClass.setYear("2019");
        onGoingBookingClass.setTime("9AM - 11AM");
        onGoingBookingClass.setStatus("Pending");
        onGoingBookingClass.setrs("\u20B9 599");
        onGoingBookingClass.setName("John");
        list1.add(onGoingBookingClass);
        adapter.notifyDataSetChanged();

        onGoingBookingClass = new OnGoingBookingClass();
        onGoingBookingClass.setService("Interior Design");
        onGoingBookingClass.setDate("24");
        onGoingBookingClass.setMonth("Oct");
        onGoingBookingClass.setYear("2019");
        onGoingBookingClass.setTime("1PM - 3PM");
        onGoingBookingClass.setStatus("Processing");
        onGoingBookingClass.setrs("\u20B9 699");
        onGoingBookingClass.setName("Daizy");
        list1.add(onGoingBookingClass);
        adapter.notifyDataSetChanged();
    }

    private void setAdapter()
    {
        LinearLayoutManager l = new LinearLayoutManager(context);
        binding.ongoingrv.setLayoutManager(l);

        adapter = new OnGoingBookingAdapter(context,list1);
        binding.ongoingrv.setAdapter(adapter);
    }

    private void click()
    {
        binding.frame.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ongoingrv.setVisibility(View.VISIBLE);
                binding.frame.getRoot().setVisibility(View.GONE);
            }
        });
    }
}