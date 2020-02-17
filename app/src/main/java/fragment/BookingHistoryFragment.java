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
import com.example.onlineserviceportal.databinding.BookinghistoryFragmentBinding;

import java.util.ArrayList;

import activity.home.homepagetab.HomePageTabActivity;
import adapter.fragment.BookingHistoryAdapter;
import classes.BookingHistoryClass;

public class BookingHistoryFragment extends Fragment {

    Context context;
    BookinghistoryFragmentBinding binding;
    ArrayList<BookingHistoryClass> list1 = new ArrayList<>();
    BookingHistoryAdapter adapter;
    BookingHistoryClass bookingHistoryClass;

    public BookingHistoryFragment(Context context) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=(HomePageTabActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bookinghistory_fragment,container,false);
        setData();
        setdapter();
        click();
        return binding.getRoot();
    }

    private void setData()
    {
        list1.clear();
        bookingHistoryClass = new BookingHistoryClass();
        bookingHistoryClass.setService("Home Paint");
        bookingHistoryClass.setDate("23");
        bookingHistoryClass.setMonth("Oct");
        bookingHistoryClass.setYear("2019");
        bookingHistoryClass.setTime("9AM - 11AM");
        bookingHistoryClass.setStatus("Cancel");
        bookingHistoryClass.setrs("\u20B9 599");
        bookingHistoryClass.setName("John");
        list1.add(bookingHistoryClass);

        bookingHistoryClass = new BookingHistoryClass();
        bookingHistoryClass.setService("Interior Design");
        bookingHistoryClass.setDate("24");
        bookingHistoryClass.setMonth("Oct");
        bookingHistoryClass.setYear("2019");
        bookingHistoryClass.setTime("1PM - 3PM");
        bookingHistoryClass.setStatus("Completed");
        bookingHistoryClass.setrs("\u20B9 699");
        bookingHistoryClass.setName("Daizy");
        list1.add(bookingHistoryClass);


    }

    private void setdapter()
    {
        LinearLayoutManager l = new LinearLayoutManager(context);
        binding.historyrv.setLayoutManager(l);

        adapter = new BookingHistoryAdapter(context,list1);
        binding.historyrv.setAdapter(adapter);
    }

    private void click()
    {
        binding.frame.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.frame.getRoot().setVisibility(View.GONE);
                binding.historyrv.setVisibility(View.VISIBLE);
            }
        });
    }
}