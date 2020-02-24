package activity.home.servicepackagelayout1.editpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineserviceportal.R;
import com.example.onlineserviceportal.databinding.RowpackageBinding;

import java.util.List;

import data.model.api.servicepackage2.ServicePackage;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.myview> {

    private List<ServicePackage> packages;
    public PackageSpecificationAdapter adapter;

    public PackageAdapter(List<ServicePackage> packages) {

        this.packages=packages;

    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowpackage,parent, false);
        return new PackageAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, int position) {
        ServicePackage servicePackage = packages.get(position);
        holder.binding.setPackages(servicePackage);

        adapter = new PackageSpecificationAdapter(packages.get(position).getSpecification(),packages.get(position).getSelectionType());
        holder.binding.specrv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    public class myview extends RecyclerView.ViewHolder{

        RowpackageBinding binding;

        public myview(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

            LinearLayoutManager l =new LinearLayoutManager(itemView.getContext());
            binding.specrv.setLayoutManager(l);
        }
    }

}
