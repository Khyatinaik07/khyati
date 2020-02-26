package activity.home.servicepackagelayout1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowtotalamounttimeBinding;

import java.util.List;

import data.model.api.servicepackage2.Specification;
import utils.GlobalStore;

public class TotalAmountTimeAdapter extends RecyclerView.Adapter<TotalAmountTimeAdapter.myview> {

    private List<Specification> specifications;
    private int amount,time;

    public TotalAmountTimeAdapter(List<Specification> specification) {
        this.specifications = specification;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowtotalamounttime,parent,false);
        return new TotalAmountTimeAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        Specification specification = specifications.get(position);
        holder.binding.setSpecification(specification);

        if (Integer.valueOf(specifications.get(position).getIsdefault()) == 1)
        {
            GlobalStore.amt = GlobalStore.amt + Integer.valueOf(specifications.get(position).getAmount());
            time = time + Integer.valueOf(specifications.get(position).getTime());
        }

        holder.binding.amount.setText(String.valueOf(GlobalStore.amt));
        holder.binding.time.setText(String.valueOf(time));
    }

    @Override
    public int getItemCount() {
        return specifications.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowtotalamounttimeBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
