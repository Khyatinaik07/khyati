package activity.home.servicepackagelayout2;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowspecificationBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import activity.home.servicepackagelayout2.specificationdetail.SpecificationDetailActivity;
import data.model.api.servicepackage2.ServiceResult;
import data.model.api.servicepackage2.Specification;
import utils.GlobalStore;

public class SpecificationAdapter extends RecyclerView.Adapter<SpecificationAdapter.specmyview>  {

    private List<Specification> serviceResults;
    private String icon;
    private ServiceResult sr;

    public SpecificationAdapter(ServiceResult serviceResult, List<Specification> serviceResults, String icon) {
        this.serviceResults= serviceResults;
        this.icon=icon;
        this.sr=serviceResult;
    }

    @NonNull
    @Override
    public specmyview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowspecification,parent,false);
        return new SpecificationAdapter.specmyview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull specmyview holder, int position) {

        Specification specification = serviceResults.get(position);
        holder.binding.setServiceResult(specification);

        Picasso.with(holder.binding.img.getContext()).load(icon).into(holder.binding.img);
        holder.binding.subtitle.setText(Html.fromHtml(sr.getSubTitle()));
        Picasso.with(holder.binding.img2.getContext()).load(icon).into(holder.binding.img2);

        if (position==0)
        {
            holder.binding.img2.setVisibility(View.GONE);
        }

        if (position > 0)
        {
            holder.binding.subtitle.setVisibility(View.GONE);
            holder.binding.img.setVisibility(View.GONE);
            holder.binding.img2.setVisibility(View.VISIBLE);
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
                    if (number > 1)
                    {
                        Toast.makeText(holder.binding.plus.getContext(),"can't select more than 1",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        ((ServicePackageActivity)holder.binding.plus.getContext()).setBottomLayoutVisibility(true);
                        getAmount(holder.binding.plus.getContext(),Integer.valueOf(serviceResults.get(position).getAmount()),
                                Integer.valueOf(serviceResults.get(position).getDiscount()));
                        holder.binding.number.setText(String.valueOf(number));
                        Log.w("position",String.valueOf(position)+holder.binding.plus.getTag().toString());
                    }
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
                    minusAmount(holder.binding.minus.getContext(),Integer.valueOf(serviceResults.get(position).getAmount()),
                            Integer.valueOf(serviceResults.get(position).getDiscount()));
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

        ((ServicePackageActivity)position).setDiscount(discount);
        ((ServicePackageActivity)position).setDiscountText(GlobalStore.amt);
        ((ServicePackageActivity) position).setAmountText(GlobalStore.amt - GlobalStore.finalamount);
    }

    public void minusAmount(Context position, int amount, double discount)
    {
        GlobalStore.amt = GlobalStore.amt - amount;
        double amt = amount;
        amt = amt*(discount/100);
        GlobalStore.finalamount = GlobalStore.finalamount - amt;

        ((ServicePackageActivity)position).setDiscount(discount);
        ((ServicePackageActivity)position).setDiscountText(GlobalStore.amt);
        ((ServicePackageActivity) position).setAmountText(GlobalStore.amt - GlobalStore.finalamount);
        if (GlobalStore.amt == 0)
        {
            ((ServicePackageActivity)position).setBottomLayoutVisibility(false);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return serviceResults.size();
    }

    public class specmyview extends RecyclerView.ViewHolder {

        RowspecificationBinding binding;

        public specmyview(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);

            binding.conViewDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), SpecificationDetailActivity.class);
                    i.putExtra("person",binding.person.getText());
                    i.putExtra("amount",binding.price.getText());
                    i.putExtra("time",binding.time.getText());
                    i.putExtra("subtitle",sr.getSubTitle());
                    i.putExtra("icon",icon);
                    view.getContext().startActivity(i);
                }
            });
        }
    }
}