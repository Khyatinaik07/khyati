package activity.home.servicepackagelayout1.editpackage;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowpackagespecificationBinding;

import java.util.List;

import data.model.api.servicepackage2.Specification;
import utils.GlobalStore;

public class PackageSpecificationAdapter extends RecyclerView.Adapter<PackageSpecificationAdapter.myview> {

    private List<Specification> specifications;
    private String selectionType;
    private int lastSelectedPosition;

    public PackageSpecificationAdapter(List<Specification> specification, String selectionType, int p) {
        this.specifications = specification;
        this.selectionType = selectionType;
        this.lastSelectedPosition=p;
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
            if (Integer.valueOf(specification.getIsdefault()) == 1) {
                holder.binding.checkbox.setChecked(true);
                getAmount(Integer.valueOf(specification.getAmount()));
            }
        }

        if (selectionType.equalsIgnoreCase("radio")) {
            holder.binding.con1.setVisibility(View.VISIBLE);
            holder.binding.con2.setVisibility(View.GONE);
            //holder.binding.radio.setChecked(Integer.valueOf(specification.getIsdefault()) == 1);
            if (lastSelectedPosition == position) {
                holder.binding.radio.setChecked(true);
                Integer.valueOf(specifications.get(position).getAmount());
                Log.w("default radio amount",specifications.get(position).getAmount());
            } else {
                holder.binding.radio.setChecked(false);
            }
        }

        holder.binding.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    //Log.w("checked position",String.valueOf(position));
                    addAmount(Integer.valueOf(specifications.get(position).getAmount()));
                }
                else
                {
                   // Log.w("unchecked position",String.valueOf(position));
                    subAmount(Integer.valueOf(specifications.get(position).getAmount()));
                }
            }
        });

       holder.binding.radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (b)
               {
                   Integer.valueOf(specifications.get(position).getAmount());
                   Log.w("radio amount",specifications.get(position).getAmount());
               }
           }
       });
    }

    //for checkbox
    private void getAmount(int amount)
    {
        GlobalStore.amt = GlobalStore.amt + amount;
        Log.w("total",String.valueOf(GlobalStore.amt));
    }

    //for checkbox
    private void subAmount(int amount)
    {
        GlobalStore.amt = GlobalStore.amt - amount;
        Log.w("sub total",String.valueOf(GlobalStore.amt));
    }

    //for checkbox
    private void addAmount(int amount)
    {
        GlobalStore.amt = GlobalStore.amt + amount;
        Log.w("add total",String.valueOf(GlobalStore.amt));
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
            //itemView.setOnClickListener(this);
           // binding.con1.setOnClickListener(this);
            binding.radio.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            lastSelectedPosition = getAdapterPosition();
            notifyDataSetChanged();
        }

    }
}
