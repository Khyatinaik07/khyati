package activity.home.servicepackagelayout1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowspecdetailBinding;

import java.util.List;

import data.model.api.servicepackage2.Specification;

public class SpectificationDetailAdapter extends RecyclerView.Adapter<SpectificationDetailAdapter.myview> {

    private List<Specification> specifications;

    public SpectificationDetailAdapter(List<Specification> specification) {
        this.specifications = specification;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowspecdetail,parent,false);
        return new SpectificationDetailAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        Specification specification = specifications.get(position);
        holder.binding.setSpecification(specification);

    }

    @Override
    public int getItemCount() {
        return specifications.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowspecdetailBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
