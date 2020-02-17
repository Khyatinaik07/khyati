package fragment;

import android.app.Activity;
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

import com.example.serviceprovider.R;
import com.example.serviceprovider.databinding.NewLeadFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import activity.home.HomePageTabActivity;
import classes.NewLeadClass;
import viewModel.NewLeadAdapterViewModel;

public class NewLeadFragment extends Fragment {

    Context context;
    Activity activity ;
    NewLeadAdapterViewModel data;
    NewLeadFragmentBinding binding;
    //NewLeadFragmentAdapter adapter;
    NewLeadClass newLeadClass;
    List<NewLeadClass> list = new ArrayList<>();

    public NewLeadFragment(Context context) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (HomePageTabActivity)getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.new_lead_fragment,container,false);
        data = new NewLeadAdapterViewModel();
        binding.setViewModel(data);
        //setData();
        data.setUp();
        setAdapter();
        //setData();
        return binding.getRoot();
    }

    private void setAdapter()
    {
        LinearLayoutManager l = new LinearLayoutManager(context);
        binding.rv.setLayoutManager(l);
       /* adapter = new NewLeadFragmentAdapter();
        binding.rv.setAdapter(adapter);*/
    }

    private void setData()
    {
        list.clear();
        newLeadClass = new NewLeadClass();
        newLeadClass.setName("Jash Desai");
        newLeadClass.setService("Party Decoration");
        newLeadClass.setDate("06 Nov 2019");
        newLeadClass.setTime("9AM - 11AM");
        newLeadClass.setStatus("Pending");
        list.add(newLeadClass);

        newLeadClass = new NewLeadClass();
        newLeadClass.setName("Krishna Desai");
        newLeadClass.setService("Party Decoration");
        newLeadClass.setDate("07 Nov 2019");
        newLeadClass.setTime("3PM - 5PM");
        newLeadClass.setStatus("Completed");
        list.add(newLeadClass);

        newLeadClass = new NewLeadClass();
        newLeadClass.setName("Amar Desai");
        newLeadClass.setService("Party Decoration");
        newLeadClass.setDate("10 Nov 2019");
        newLeadClass.setTime("7PM - 9PM");
        newLeadClass.setStatus("Processing");
        list.add(newLeadClass);
    }

}
