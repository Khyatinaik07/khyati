package viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;

import adapter.fragment.NewLeadFragmentAdapter;
import classes.NewLeadClass;

public class NewLeadAdapterViewModel extends BaseObservable {

    private static final String TAG = "NewLeadAdapterViewModel";
    private NewLeadFragmentAdapter adapter;
    private List<NewLeadClass> data;
    NewLeadClass newLeadClass;

    public NewLeadAdapterViewModel() {
        data = new ArrayList<>();
        adapter = new NewLeadFragmentAdapter();
    }

    @Bindable
    public List<NewLeadClass> getData() {
        return this.data;
    }

    @Bindable
    public NewLeadFragmentAdapter getAdapter() {
        return this.adapter;
    }

    public void setUp()
    {
        newLeadClass = new NewLeadClass();
        newLeadClass.setName("Jash");
        newLeadClass.setStatus("Pending");
        newLeadClass.setTime("9:00AM - 11:00AM");
        newLeadClass.setDate("06 Nov 2019");
        newLeadClass.setService("Party Decoration");
        data.add(newLeadClass);

        newLeadClass = new NewLeadClass();
        newLeadClass.setName("Krishna");
        newLeadClass.setStatus("Pending");
        newLeadClass.setTime("1:00PM - 3:00PM");
        newLeadClass.setDate("06 Nov 2019");
        newLeadClass.setService("Party Decoration");
        data.add(newLeadClass);

        newLeadClass = new NewLeadClass();
        newLeadClass.setName("Jash");
        newLeadClass.setStatus("Pending");
        newLeadClass.setTime("9:00AM - 11:00AM");
        newLeadClass.setDate("06 Nov 2019");
        newLeadClass.setService("Party Decoration");
        data.add(newLeadClass);

        notifyPropertyChanged(BR.name);
        notifyPropertyChanged(BR.status);
        notifyPropertyChanged(BR.time);
        notifyPropertyChanged(BR.date);
        notifyPropertyChanged(BR.service);
    }

}