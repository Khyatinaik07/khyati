package activity.home.servicepackagelayout3;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowspecificationdetailBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import activity.home.servicepackagelayout3.viewpackagedetail.ServicePackageDetailActivity;
import data.model.api.servicepackage2.Specification;
import utils.GlobalStore;

public class SpecificationDetailAdapter extends RecyclerView.Adapter<SpecificationDetailAdapter.myview>{

    private List<Specification> specifications;
    private String icon,name;
    public ImageAdapter imageAdapter;

    public SpecificationDetailAdapter(List<Specification> specification, String icon, String name) {
        this.specifications = specification;
        this.icon = icon;
        this.name= name;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowspecificationdetail,parent,false);
        return new SpecificationDetailAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        Specification specification = specifications.get(position);
        holder.binding.setServiceResult(specification);
        holder.binding.subtitle.setText(Html.fromHtml(specification.getSpecification()));

        if (specifications.get(position).getImages().size() == 0)
        {
            holder.binding.imgrv.setVisibility(View.GONE);
            Picasso.with(holder.binding.img.getContext()).load(icon).into(holder.binding.img);
        }
        else
        {
            holder.binding.imgrv.setVisibility(View.VISIBLE);
            holder.binding.img.setVisibility(View.GONE);
            imageAdapter = new ImageAdapter(specifications.get(position).getImages());
            holder.binding.imgrv.setAdapter(imageAdapter);
        }

        if (specifications.size() == 0)
        {
            ((ServicePackageLayoutThreeActivity)holder.binding.imgrv.getContext()).isSpecificationAvailable(true);
        }
        else
        {
            ((ServicePackageLayoutThreeActivity)holder.binding.imgrv.getContext()).isSpecificationAvailable(false);
        }

        holder.binding.plus.setTag(position);
        holder.binding.plus.setFocusable(true);

        holder.binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == Integer.valueOf(holder.binding.plus.getTag().toString()))
                {
                    int number = Integer.valueOf(holder.binding.number.getText().toString());
                    number = number + 1;
                        ((ServicePackageLayoutThreeActivity)holder.binding.plus.getContext()).setBottomLayoutVisibility(true);
                        getAmount(holder.binding.plus.getContext(),Integer.valueOf(specifications.get(position).getAmount()),
                                Integer.valueOf(specifications.get(position).getDiscount()));
                        holder.binding.number.setText(String.valueOf(number));
                        Log.w("position",String.valueOf(position)+holder.binding.plus.getTag().toString());

                }
            }
        });

        holder.binding.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == Integer.valueOf(holder.binding.plus.getTag().toString()))
                {
                    int number = Integer.valueOf(holder.binding.number.getText().toString());
                    number = number - 1;
                    if (number<0)
                    {
                        number = 0;
                        holder.binding.number.setText(String.valueOf(number));
                    }
                    holder.binding.number.setText(String.valueOf(number));
                    minusAmount(holder.binding.minus.getContext(),Integer.valueOf(specifications.get(position).getAmount()),
                            Integer.valueOf(specifications.get(position).getDiscount()));
                    Log.w("position",String.valueOf(position));
                }
            }
        });
    }

    public void getAmount(Context position, int amount, double discount) {
        GlobalStore.amt = GlobalStore.amt + amount;
        double amt = amount;
        amt = amt*(discount/100);
        GlobalStore.finalamount = GlobalStore.finalamount + amt;

        ((ServicePackageLayoutThreeActivity)position).setDiscount(discount);
        ((ServicePackageLayoutThreeActivity)position).setDiscountText(GlobalStore.amt);
        ((ServicePackageLayoutThreeActivity) position).setAmountText(GlobalStore.amt - GlobalStore.finalamount);
    }

    public void minusAmount(Context position, int amount, double discount)
    {
        GlobalStore.amt = GlobalStore.amt - amount;
        double amt = amount;
        amt = amt*(discount/100);
        GlobalStore.finalamount = GlobalStore.finalamount - amt;

        ((ServicePackageLayoutThreeActivity)position).setDiscount(discount);
        ((ServicePackageLayoutThreeActivity)position).setDiscountText(GlobalStore.amt);
        ((ServicePackageLayoutThreeActivity) position).setAmountText(GlobalStore.amt - GlobalStore.finalamount);
        if (GlobalStore.amt == 0)
        {
            ((ServicePackageLayoutThreeActivity)position).setBottomLayoutVisibility(false);
        }
    }

    @Override
    public int getItemCount() {
        return specifications.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowspecificationdetailBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

            LinearLayoutManager l = new LinearLayoutManager(itemView.getContext());
            l.setOrientation(RecyclerView.HORIZONTAL);
            binding.imgrv.setLayoutManager(l);
            binding.conViewDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(), ServicePackageDetailActivity.class);
                    i.putExtra("amount",specifications.get(getLayoutPosition()).getAmount());
                    i.putExtra("title",specifications.get(getLayoutPosition()).getTitle());
                    i.putExtra("subtitle",specifications.get(getLayoutPosition()).getSpecification());
                    i.putExtra("time",binding.time.getText());
                    i.putExtra("icon",icon);
                    i.putExtra("name",name);
                    i.putExtra("discount",specifications.get(getLayoutPosition()).getDiscount());
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}
