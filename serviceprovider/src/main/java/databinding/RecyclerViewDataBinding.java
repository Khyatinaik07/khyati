package databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import adapter.fragment.NewLeadFragmentAdapter;
import classes.NewLeadClass;

public class RecyclerViewDataBinding {

    /**
     * Binds the data to the {@link androidx.recyclerview.widget.RecyclerView.Adapter}, sets the
     * recycler view on the adapter, and performs some other recycler view initialization.
     *
     * @param recyclerView passed in automatically with the data binding
     * @param adapter      must be explicitly passed in
     * @param data         must be explicitly passed in
     */
    @BindingAdapter({"adapter", "data"})
    public void bind(RecyclerView recyclerView, NewLeadFragmentAdapter adapter, List<NewLeadClass> data) {
        recyclerView.setAdapter(adapter);
        adapter.updateData(data);
    }

}