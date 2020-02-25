package activity.home.servicepackagelayout1.editpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RoweditservicepackageBinding;

import java.util.List;

import data.model.api.servicepackage2.ServiceResult;

public class EditPackageAdapter  extends RecyclerView.Adapter<EditPackageAdapter.myview>{

    private List<ServiceResult> serviceResults;
    String icon;
    PackageAdapter adapter;

    public EditPackageAdapter(List<ServiceResult> serviceResults, String icon) {
        this.serviceResults = serviceResults;
        this.icon = icon;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.roweditservicepackage,parent, false);
        return new EditPackageAdapter.myview(view);
    }

    public void setServiceResults(List<ServiceResult> serviceResults)
    {
        this.serviceResults = serviceResults;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        ServiceResult serviceResult = serviceResults.get(position);
        holder.binding.setServiceResult(serviceResult);

        adapter = new PackageAdapter(serviceResults.get(position).getPackages());
        holder.binding.packages.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return serviceResults.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RoweditservicepackageBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);

            LinearLayoutManager l = new LinearLayoutManager(itemView.getContext());
            binding.packages.setLayoutManager(l);
            binding.packages.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
    }
}