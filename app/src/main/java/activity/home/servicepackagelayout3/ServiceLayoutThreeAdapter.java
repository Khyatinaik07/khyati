package activity.home.servicepackagelayout3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowservicelayoutthreeBinding;

import java.util.ArrayList;
import java.util.List;

import data.model.api.servicepackage2.ServiceResult;

public class ServiceLayoutThreeAdapter extends RecyclerView.Adapter<ServiceLayoutThreeAdapter.myview>{

    private List<ServiceResult> list;
    private String icon;
    SpecificationDetailAdapter adapter;

    public ServiceLayoutThreeAdapter(ArrayList<ServiceResult> list, String icon) {
        this.list = list;
        this.icon = icon;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowservicelayoutthree,parent,false);
        return new ServiceLayoutThreeAdapter.myview(view);
    }

    public void setData(List<ServiceResult> serviceResults)
    {
        this.list = serviceResults;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {

        adapter = new SpecificationDetailAdapter(list.get(position).getSpecification(),icon);
        holder.binding.rv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowservicelayoutthreeBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

            LinearLayoutManager l = new LinearLayoutManager(itemView.getContext());
            binding.rv.setLayoutManager(l);

        }
    }
}
