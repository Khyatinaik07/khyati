package activity.home.servicepackagelayout1;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.Rowservicepackagelayout1Binding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import activity.home.servicepackagelayout1.editpackage.EditPackageActivity;
import activity.home.servicepackagelayout2.ServicePackageImageAdapter;
import data.model.api.servicepackage2.ServicePackage;
import data.model.api.servicepackage2.ServiceResult;
import data.model.api.servicepackage2.Specification;
import utils.GlobalStore;

public class ServiceLayoutOneAdapter extends RecyclerView.Adapter<ServiceLayoutOneAdapter.myview> {

    private List<ServiceResult> list;
    ServicePackageImageAdapter adapter;
    ServicePackagePackageAdapter packageAdapter;
    private String icon,mtime,amount;

    public ServiceLayoutOneAdapter(ArrayList<ServiceResult> serviceResults,String icon) {
        this.list = serviceResults;
        this.icon=icon;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowservicepackagelayout1,parent,false);
        return new ServiceLayoutOneAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        ServiceResult data = list.get(position);
        holder.binding.setServiceResult(data);

        adapter = new ServicePackageImageAdapter(list.get(position).getImages(),icon);
        holder.binding.imgrv.setAdapter(adapter);

        if (list.get(position).getImages().size() == 0)
        {
            holder.binding.imgrv.setVisibility(View.GONE);
            holder.binding.img.setVisibility(View.VISIBLE);
            Picasso.with(holder.binding.img.getContext()).load(icon).into(holder.binding.img);
        }

        packageAdapter = new ServicePackagePackageAdapter(list.get(position).getPackages());
        holder.binding.rvspec.setAdapter(packageAdapter);

        List<ServicePackage> packages = list.get(position).getPackages();
        int amt = 0;
        int time = 0;
        for (int i = 0;i<packages.size();i++)
        {
            if (Integer.valueOf(packages.get(i).getServiceId()) == Integer.valueOf(data.getServiceId()))
            {
                List<Specification> specifications = packages.get(i).getSpecification();
                if (Integer.valueOf(packages.get(i).getPackageId()) == Integer.valueOf(specifications.get(i).getPackageId()))
                {
                    for (int j=0;j<specifications.size();j++)
                    {
                        if (Integer.valueOf(specifications.get(j).getIsdefault()) == 1)
                        {
                            amt = amt + Integer.valueOf(specifications.get(j).getAmount());
                            time = time + Integer.valueOf(specifications.get(j).getTime());
                        }
                    }
                    Log.w("amount",String.valueOf(amt));
                    ((ServicePackageLayoutOneActivity)holder.binding.amount.getContext()).setMainAmount("\u20B9"+amt);
                    ((ServicePackageLayoutOneActivity)holder.binding.amount.getContext()).setMainTime(time+"min");
                }
            }
        }

        if (data.getSubTitle().isEmpty())
        {
            holder.binding.subtitle.setVisibility(View.GONE);
        }

        amount = ((ServicePackageLayoutOneActivity)holder.binding.amount.getContext()).getMainAmount();
        mtime = ((ServicePackageLayoutOneActivity)holder.binding.amount.getContext()).getMainTime();
      //  Log.w("amount",String.valueOf(amount));
        holder.binding.amount.setText(String.valueOf(amount));
        holder.binding.time.setText(mtime);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<ServiceResult> serviceResults)
    {
        this.list= serviceResults;
        notifyDataSetChanged();
    }

    public class myview extends RecyclerView.ViewHolder{

        Rowservicepackagelayout1Binding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

            LinearLayoutManager l = new LinearLayoutManager(itemView.getContext());
            l.setOrientation(RecyclerView.HORIZONTAL);
            binding.imgrv.setLayoutManager(l);
            binding.imgrv.setNestedScrollingEnabled(false);

            LinearLayoutManager l2 = new LinearLayoutManager(itemView.getContext());
            binding.rvspec.setLayoutManager(l2);
            binding.rvspec.setNestedScrollingEnabled(false);

            binding.conedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), EditPackageActivity.class);
                    i.putExtra("serviceid",list.get(getLayoutPosition()).getParentId());
                    i.putExtra("icon",icon);
                    i.putExtra("id",list.get(getLayoutPosition()).getServiceId());
                    i.putExtra("position",getLayoutPosition());
                    i.putExtra("name",list.get(getLayoutPosition()).getName());
                    GlobalStore.amt = 0;
                    GlobalStore.discount = 0;
                    GlobalStore.finalamount = 0;
                    view.getContext().startActivity(i);
                }
            });
        }
    }
}