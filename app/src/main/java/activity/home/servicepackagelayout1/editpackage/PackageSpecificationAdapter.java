package activity.home.servicepackagelayout1.editpackage;

import android.content.Context;
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
        this.lastSelectedPosition = p;
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

        //check type
        if (selectionType.equalsIgnoreCase("checkbox")) {
            holder.binding.con1.setVisibility(View.GONE);
            holder.binding.con2.setVisibility(View.VISIBLE);
            if (Integer.valueOf(specification.getIsdefault()) == 1) {
                holder.binding.checkbox.setChecked(true);
                getAmount(holder.binding.checkbox.getContext(), Integer.valueOf(specification.getAmount()), Integer.valueOf(specification.getDiscount()));
            }
        }

        //check type
        if (selectionType.equalsIgnoreCase("radio")) {
            holder.binding.con1.setVisibility(View.VISIBLE);
            holder.binding.con2.setVisibility(View.GONE);
            //holder.binding.radio.setChecked(Integer.valueOf(specification.getIsdefault()) == 1);
            if (lastSelectedPosition == position) {
                holder.binding.radio.setChecked(true);
                Integer.valueOf(specifications.get(position).getAmount());

                int amt = Integer.valueOf(specifications.get(position).getAmount());
                double dis = Double.valueOf(specifications.get(position).getDiscount());
                double famt = amt * (dis/100);
                ((EditPackageActivity) holder.binding.radio.getContext()).setDiscountText(Integer.valueOf(specifications.get(position).getAmount()));
                ((EditPackageActivity) holder.binding.radio.getContext()).setAmountText(famt);
            } else {
                holder.binding.radio.setChecked(false);
            }
        }

        //checkbox
        holder.binding.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    addAmount(holder.binding.checkbox.getContext(), Integer.valueOf(specifications.get(position).getAmount()), Integer.valueOf(specification.getDiscount()));
                } else {
                    subAmount(holder.binding.checkbox.getContext(), Integer.valueOf(specifications.get(position).getAmount()), Integer.valueOf(specification.getDiscount()));
                }
            }
        });

        //radio button
        holder.binding.radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ((EditPackageActivity) holder.binding.radio.getContext()).setAmountText(Integer.valueOf(specifications.get(position).getAmount()));
                    ((EditPackageActivity) holder.binding.radio.getContext()).setDiscountText(
                            Integer.valueOf(specifications.get(position).getAmount())*
                                    (Integer.valueOf(specifications.get(position).getDiscount())/100));
                }
            }
        });
    }

    //for checkbox
    public void getAmount(Context position, int amount, double discount) {
        GlobalStore.amt = GlobalStore.amt + amount;
        double amt = amount;
        amt = amt*(discount/100);
        GlobalStore.finalamount = GlobalStore.finalamount + amt;

        ((EditPackageActivity)position).setDiscountText(GlobalStore.amt);
        ((EditPackageActivity) position).setAmountText(GlobalStore.amt - GlobalStore.finalamount);

    }

    //for checkbox
    public void subAmount(Context position, int amount, double discount) {
        GlobalStore.amt = GlobalStore.amt - amount;
        double amt = amount;
        amt = amt * (discount/100);
        GlobalStore.finalamount = GlobalStore.finalamount - amt;

        ((EditPackageActivity)position).setDiscountText(GlobalStore.amt);
        ((EditPackageActivity) position).setAmountText(GlobalStore.amt - GlobalStore.finalamount);
    }

    //for checkbox
    public void addAmount(Context position, int amount, double discount) {
        GlobalStore.amt = GlobalStore.amt + amount;
        double amt = amount;
        amt = amt * (discount/100);
        GlobalStore.finalamount = GlobalStore.finalamount + amt;

        ((EditPackageActivity)position).setDiscountText(GlobalStore.amt);
        ((EditPackageActivity) position).setAmountText(GlobalStore.amt - GlobalStore.finalamount);
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
