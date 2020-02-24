package activity.home.servicepackagelayout1.editpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowpackagespecificationBinding;

import java.util.List;

import data.model.api.servicepackage2.Specification;

public class PackageSpecificationAdapter extends RecyclerView.Adapter<PackageSpecificationAdapter.myview> {

    private List<Specification> specifications;
    private String selectionType;
    private int lastSelectedPosition = -1;
    Boolean b = false;

    public PackageSpecificationAdapter(List<Specification> specification, String selectionType) {
        this.specifications = specification;
        this.selectionType = selectionType;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowpackagespecification, parent, false);
        return new PackageSpecificationAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        Specification specification = specifications.get(position);
        holder.binding.setSpecifications(specification);

        if (selectionType.equalsIgnoreCase("checkbox")) {
            holder.binding.con1.setVisibility(View.GONE);
            holder.binding.con2.setVisibility(View.VISIBLE);
        }
        if (selectionType.equalsIgnoreCase("radio")) {
            holder.binding.con1.setVisibility(View.VISIBLE);
            holder.binding.con2.setVisibility(View.GONE);
        }
        if (Integer.valueOf(specification.getIsdefault()) == 1) {
            holder.binding.checkbox.setChecked(true);
            holder.binding.radio.setChecked(true);
        }
        if (lastSelectedPosition == position) {
            holder.binding.radio.setChecked(true);
        } else {
            holder.binding.radio.setChecked(false);
        }

    }

    public void setBoolean(Boolean b) {
        this.b = b;
    }

    public Boolean getBoolean() {
        return b;
    }

    @Override
    public int getItemCount() {
        return specifications.size();
    }

    public class myview extends RecyclerView.ViewHolder implements View.OnClickListener {

        RowpackagespecificationBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
            binding.con1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            lastSelectedPosition = getAdapterPosition();
            notifyDataSetChanged();
        }
    }
}
