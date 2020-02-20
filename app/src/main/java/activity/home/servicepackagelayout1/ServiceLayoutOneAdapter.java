package activity.home.servicepackagelayout1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.Rowservicepackagelayout1Binding;

import java.util.ArrayList;
import java.util.List;

import activity.home.servicepackagelayout2.ServicePackageImageAdapter;
import data.model.api.servicepackage2.ServiceResult;

public class ServiceLayoutOneAdapter extends RecyclerView.Adapter<ServiceLayoutOneAdapter.myview> {

    private List<ServiceResult> list;
    ServicePackageImageAdapter adapter;

    public ServiceLayoutOneAdapter(ArrayList<ServiceResult> serviceResults) {
        this.list = serviceResults;
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

        adapter = new ServicePackageImageAdapter(list.get(position).getImages());
        holder.binding.imgrv.setAdapter(adapter);

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
        }
    }

}
