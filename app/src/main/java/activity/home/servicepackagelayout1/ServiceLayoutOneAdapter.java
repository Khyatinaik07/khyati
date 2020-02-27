package activity.home.servicepackagelayout1;

import android.content.Intent;
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
import data.model.api.servicepackage2.ServiceResult;
import utils.GlobalStore;

public class ServiceLayoutOneAdapter extends RecyclerView.Adapter<ServiceLayoutOneAdapter.myview> {

    private List<ServiceResult> list;
    ServicePackageImageAdapter adapter;
    ServicePackagePackageAdapter packageAdapter;
    private String icon;
    int amount;

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

        if (data.getSubTitle().isEmpty())
        {
            holder.binding.subtitle.setVisibility(View.GONE);
        }

        amount = ((ServicePackageLayoutOneActivity)holder.binding.amount.getContext()).getMainAmount();
        holder.binding.amount.setText(String.valueOf(amount));
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